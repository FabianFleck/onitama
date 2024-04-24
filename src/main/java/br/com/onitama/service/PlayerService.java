package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.PartEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.entity.UserEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.repository.PlayerRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repository;
    private final PartService partService;
    private final BattleService battleService;
    private final UserService userService;

    public PlayerService(PlayerRepository repository, @Lazy PartService partService, BattleService battleService, UserService userService) {
        this.repository = repository;
        this.partService = partService;
        this.battleService = battleService;
        this.userService = userService;
    }

    public PlayerEntity createPlayerWithParts(String username, ColorEnum color, int startLine) {
        PlayerEntity player = new PlayerEntity();
        UserEntity user = userService.findByUsername(username);
        player.setUser(user);
        player.setColor(color);
        List<PartEntity> parts = partService.initializePartsForPlayer(player, startLine);
        player.setParts(parts);

        player = repository.save(player); // Salva o player e as parts devido ao CascadeType.ALL
        return player;
    }

    public PlayerEntity findById(Long playerId) {
        return repository.findById(playerId)
                .orElseThrow(() -> new UnprocessableEntityException("Player não encontrado"));
    }

    public PlayerEntity findOpponent(PlayerEntity player) {
        BattleEntity battle = battleService.findByPlayer(player);  // Supõe-se um método para encontrar a batalha por jogador
        if (battle.getPlayer1().getId().equals(player.getId())) {
            return battle.getPlayer2();
        } else {
            return battle.getPlayer1();
        }
    }

    public PlayerEntity save(PlayerEntity player) {
        return repository.save(player);
    }
}
