package br.com.onitama.service;

import br.com.onitama.model.entity.PartEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {

    private final PlayerRepository repository;

    private final PartService partService;

    public PlayerService(PlayerRepository repository, PartService partService) {
        this.repository = repository;
        this.partService = partService;
    }

    public PlayerEntity createPlayerWithParts(String name, ColorEnum color, int startLine) {
        PlayerEntity player = new PlayerEntity();
        player.setName(name);
        player.setColor(color);
        List<PartEntity> parts = partService.initializePartsForPlayer(player, startLine);
        player.setParts(parts);

        player = repository.save(player); // Salva o player e as parts devido ao CascadeType.ALL
        return player;
    }

    public PlayerEntity findById(Long playerId) {
        return repository.findById(playerId).orElse(null);
    }
}
