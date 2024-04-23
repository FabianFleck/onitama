package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.repository.BattleRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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

    public BattleEntity createBattle(String username) {
        PlayerEntity player = playerService.createPlayerWithParts(username, RED, 5);

        BattleEntity battle = new BattleEntity();
        battle.setPlayer1(player);

        BattleEntity save = repository.save(battle);

        return save;
    }

    public BattleEntity joinBattle(String battleId, String username) {
        BattleEntity battle = findById(battleId);

        if (battle.getPlayer1() != null && username.equals(battle.getPlayer1().getUser().getUsername())) {
            throw new UnprocessableEntityException("Você já está nessa batalha.");
        }

        if (battle.getPlayer2() != null) {
            throw new UnprocessableEntityException("Essa batalha já está preenchida com os dois players.");
        }

        PlayerEntity player2 = playerService.createPlayerWithParts(username, BLUE, 1);
        battle.setPlayer2(player2);

        return repository.save(battle);
    }

    public BattleEntity findById(String battleId) {
        return repository.findById(battleId)
                .orElseThrow(() -> new UnprocessableEntityException("Battle not found"));
    }

    public BattleEntity save(BattleEntity battle) {
        return repository.save(battle);
    }

    public BattleEntity findByPlayer(PlayerEntity player) {
        return repository.findByPlayer1OrPlayer2(player, player);
    }
}
