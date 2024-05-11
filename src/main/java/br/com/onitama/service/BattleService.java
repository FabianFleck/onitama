package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.response.BattleSimpleResponse;
import br.com.onitama.repository.BattleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static br.com.onitama.model.enumeration.BattleResultEnum.OPEN;
import static br.com.onitama.model.enumeration.ColorEnum.BLUE;
import static br.com.onitama.model.enumeration.ColorEnum.RED;

@Service
public class BattleService {

    private final PlayerService playerService;
    private final BattleRepository repository;
    private final Map<String, SseEmitter> battleSimpleEmitters = new ConcurrentHashMap<>();

    public BattleService(@Lazy PlayerService playerService, BattleRepository repository) {
        this.playerService = playerService;
        this.repository = repository;
    }

    @Transactional
    public BattleEntity createBattle(String username, ColorEnum color) {
        BattleEntity newBattle = new BattleEntity();

        PlayerEntity player = playerService.createPlayerWithParts(username, color, getStartLine(color));
        if (color == RED) {
            newBattle.setPlayer1(player);
        } else {
            newBattle.setPlayer2(player);
        }

        newBattle.setResult(OPEN);
        BattleEntity battle = repository.save(newBattle);
        player.setBattle(battle);
        playerService.save(player);

        notifyBattleUpdates(new BattleSimpleResponse(
                battle.getId(),
                battle.getPlayer1(),
                battle.getPlayer2(),
                battle.getResult()), username);

        return battle;
    }

    @Transactional
    public BattleEntity joinBattle(String battleId, String username) {
        BattleEntity battle = findById(battleId);

        if ((battle.getPlayer1() != null && username.equals(battle.getPlayer1().getUser().getUsername()))
                || battle.getPlayer2() != null && username.equals(battle.getPlayer2().getUser().getUsername())) {
            throw new UnprocessableEntityException("Você já está nessa batalha.");
        }

        if (battle.getPlayer1() != null && battle.getPlayer2() != null) {
            throw new UnprocessableEntityException("Essa batalha já está preenchida com os dois players.");
        }

        ColorEnum colorOpponent = findColorOpponent(battle);
        ColorEnum color = colorOpponent == BLUE ? RED : BLUE;

        PlayerEntity player = playerService.createPlayerWithParts(username, color, getStartLine(color));

        if (battle.getPlayer1() == null) {
            battle.setPlayer1(player);
        } else {
            battle.setPlayer2(player);
        }

        BattleEntity battleSave = repository.save(battle);
        player.setBattle(battleSave);
        playerService.save(player);

        notifyBattleUpdates(new BattleSimpleResponse(
                battle.getId(),
                battle.getPlayer1(),
                battle.getPlayer2(),
                battle.getResult()), username);

        return battleSave;
    }

    public BattleEntity findById(String battleId) {
        return repository.findById(battleId)
                .orElseThrow(() -> new UnprocessableEntityException("Batalha não encontrada."));
    }

    public BattleEntity save(BattleEntity battle) {
        return repository.save(battle);
    }

    public BattleEntity findByPlayer(PlayerEntity player) {
        return repository.findByPlayer1OrPlayer2(player, player);
    }

    public List<BattleSimpleResponse> findAllByUserUsername(String username) {
        return playerService.findByUsername(username)
                .stream().map(player -> player.getBattle())
                .map(battle ->
                        new BattleSimpleResponse(
                                battle.getId(),
                                battle.getPlayer1(),
                                battle.getPlayer2(),
                                battle.getResult()))
                .toList();
    }

    private int getStartLine(ColorEnum color) {
        return color == RED ? 1 : 5;
    }

    private ColorEnum findColorOpponent(BattleEntity battle) {
        return battle.getPlayer1() != null
                ? battle.getPlayer1().getColor()
                : battle.getPlayer2().getColor();
    }

    public BattleEntity nextPlayer(String battleId) {
        BattleEntity battle = findById(battleId);
        battle.nextPlayer();
        return save(battle);
    }

    public BattleEntity findByPlayerId(Long playerId) {
        return repository.findByPlayerId(playerId);
    }

    public void notifyBattleUpdates(BattleSimpleResponse updatedBattle, String username) {
        if (updatedBattle.getPlayerOne() != null) {
            sendBattleUpdate(updatedBattle, updatedBattle.getPlayerOne());
        }

        if (updatedBattle.getPlayerTwo() != null) {
            sendBattleUpdate(updatedBattle, updatedBattle.getPlayerTwo());
        }
    }

    private void sendBattleUpdate(BattleSimpleResponse battle, String playerUsername) {
        SseEmitter emitter = this.battleSimpleEmitters.get(playerUsername);
        if (emitter != null) {
            try {
                System.out.println("Enviando atualização para: " + playerUsername);
                emitter.send(SseEmitter.event().name("message").data(battle));
            } catch (IOException e) {
                System.out.println("Erro ao enviar evento SSE: " + e.getMessage());
                emitter.completeWithError(e);
                this.battleSimpleEmitters.remove(playerUsername);
            }
        } else {
            System.out.println("Nenhum emitter encontrado para: " + playerUsername);
        }
    }

    public SseEmitter streamBattles(String username) {
        final Long TIMEOUT = 180_000L; // Timeout de 3 minutos, ajuste conforme necessário
        SseEmitter emitter = new SseEmitter(TIMEOUT);
        System.out.println("Estabelecendo conexão para o username: " + username);

        battleSimpleEmitters.put(username, emitter);

        emitter.onCompletion(() -> {
            battleSimpleEmitters.remove(username);
            System.out.println("Conexão SSE completada para: " + username);
        });

        emitter.onTimeout(() -> {
            emitter.complete();
            battleSimpleEmitters.remove(username);
            System.out.println("Conexão SSE com timeout para: " + username);
        });

        return emitter;
    }
}
