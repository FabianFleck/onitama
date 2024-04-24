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

import java.util.List;

import static br.com.onitama.model.enumeration.ColorEnum.BLUE;
import static br.com.onitama.model.enumeration.ColorEnum.RED;

@Service
public class BattleService {

    private final PlayerService playerService;
    private final BattleRepository repository;

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

        BattleEntity battle = repository.save(newBattle);
        player.setBattle(battle);
        playerService.save(player);

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
                .stream().map(player -> new BattleSimpleResponse(player.getBattle().getId()))
                .toList();
    }

    private int getStartLine(ColorEnum color) {
        return color == RED ? 5 : 1;
    }

    private ColorEnum findColorOpponent(BattleEntity battle) {
        return battle.getPlayer1() != null
                ? battle.getPlayer1().getColor()
                : battle.getPlayer2().getColor();
    }
}
