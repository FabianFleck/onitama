package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
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
    public BattleEntity createBattle(String username) {
        BattleEntity newBattle = new BattleEntity();
        repository.save(newBattle);

        PlayerEntity player = playerService.createPlayerWithParts(username, RED, 5);
        newBattle.setPlayer1(player);

        BattleEntity battle = repository.save(newBattle);
        player.setBattle(newBattle);
        playerService.save(player);

        return battle;
    }

    @Transactional
    public BattleEntity joinBattle(String battleId, String username) {
        BattleEntity newBattle = findById(battleId);

        if (newBattle.getPlayer1() != null && username.equals(newBattle.getPlayer1().getUser().getUsername())) {
            throw new UnprocessableEntityException("Você já está nessa batalha.");
        }

        if (newBattle.getPlayer2() != null) {
            throw new UnprocessableEntityException("Essa batalha já está preenchida com os dois players.");
        }

        PlayerEntity player2 = playerService.createPlayerWithParts(username, BLUE, 1);
        newBattle.setPlayer1(player2);

        BattleEntity battle = repository.save(newBattle);
        player2.setBattle(newBattle);
        playerService.save(player2);

        return battle;
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

    public List<BattleEntity> findAllByUserUsername(String username) {
        return repository.findByUsername(username);
    }
}
