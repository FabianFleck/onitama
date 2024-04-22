package br.com.onitama.service;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.repository.BattleRepository;
import org.springframework.stereotype.Service;

import static br.com.onitama.model.enumeration.ColorEnum.BLUE;
import static br.com.onitama.model.enumeration.ColorEnum.RED;

@Service
public class BattleService {

    private final PlayerService playerService;
    private final BattleRepository repository;

    public BattleService(PlayerService playerService, BattleRepository repository) {
        this.playerService = playerService;
        this.repository = repository;
    }

    public BattleEntity createBattle(String name) {
        PlayerEntity player = playerService.createPlayerWithParts(name, RED, 5);

        BattleEntity battle = new BattleEntity();
        battle.setPlayer1(player);

        BattleEntity save = repository.save(battle);

        return save;
    }

    public BattleEntity joinBattle(String battleId, String name) {
        BattleEntity battle = repository.findById(battleId)
                .orElseThrow(() -> new RuntimeException("Battle not found"));

        if (battle.getPlayer2() != null) {
            throw new IllegalStateException("Player2 is already set for this battle.");
        }

        PlayerEntity player2 = playerService.createPlayerWithParts(name, BLUE, 1);
        battle.setPlayer2(player2);

        return repository.save(battle);
    }

    public BattleEntity findById(String battleId) {
        return repository.findById(battleId).orElseThrow(() -> new RuntimeException("Battle not found"));
    }

    public BattleEntity save(BattleEntity battle) {
        return repository.save(battle);
    }
}
