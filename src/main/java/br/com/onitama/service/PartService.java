package br.com.onitama.service;

import br.com.onitama.model.entity.PartEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.entity.PositionPart;
import br.com.onitama.model.enumeration.PartTypeEnum;
import br.com.onitama.repository.PartRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

import static br.com.onitama.model.enumeration.PartTypeEnum.DISCIPLE;

@Service
public class PartService {

    private final PlayerService playerService;
    private final PartRepository repository;

    public PartService(PlayerService playerService, PartRepository repository) {
        this.playerService = playerService;
        this.repository = repository;
    }

    @Transactional
    public void captureOpponentPartAtPosition(PlayerEntity player, PositionPart newPosition) {
        // Encontrar o oponente
        PlayerEntity opponent = playerService.findOpponent(player);

        // Encontrar a peça do oponente na posição especificada
        PartEntity opponentPart = repository.findByPlayerAndPosition(opponent, newPosition);

        // Se a peça existir, deletá-la
        if (opponentPart != null) {
            repository.delete(opponentPart);
            repository.flush();
        }
    }

    public List<PartEntity> initializePartsForPlayer(PlayerEntity player, int startLine) {
        List<PartEntity> parts = new ArrayList<>();
        for (int column = 1; column <= 5; column++) {
            PartEntity part = new PartEntity();
            part.setPlayer(player);
            part.setPosition(new PositionPart(startLine, column));
            part.setType(DISCIPLE);
            parts.add(part);
        }
        parts.get(2).setType(PartTypeEnum.MASTER);
        return parts;
    }

    public PartEntity findPartAtPosition(List<PartEntity> parts, PositionPart targetPosition) {
        for (PartEntity part : parts) {
            if (part.getPosition().equals(targetPosition)) {
                return part;
            }
        }
        return null;  // Retorna null se nenhuma peça for encontrada na posição especificada
    }

    public PartEntity save(PartEntity part) {
        return repository.save(part);
    }
}
