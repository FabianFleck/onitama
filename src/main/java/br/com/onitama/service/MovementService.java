package br.com.onitama.service;

import br.com.onitama.model.Position;
import br.com.onitama.model.entity.*;
import br.com.onitama.model.enumeration.ColorEnum;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {

    private final CardService cardService;
    private final PlayerService playerService;
    private final PartService partService;

    public MovementService(CardService cardService, PlayerService playerService, PartService partService) {
        this.cardService = cardService;
        this.playerService = playerService;
        this.partService = partService;
    }

    public List<Position> getPossibleMoves(int line, int column, Long playerId, Long cardId) {
        final Position positionActual = new Position(line, column);
        PlayerEntity player = playerService.findById(playerId);
        CardEntity card = cardService.findById(cardId);

        return calculatePossibleMoves(positionActual, card, player);
    }

    public List<Position> calculatePossibleMoves(Position currentPosition, CardEntity card, PlayerEntity player) {
        List<Position> possibleMoves = new ArrayList<>();
        int lineDirection = player.getColor() == ColorEnum.RED ? 1 : -1;

        for (PositionEntity move : card.getPositions()) {
            int newLine = currentPosition.getLine() + (move.getLine() * lineDirection);
            int newColumn = currentPosition.getColumn() + move.getColumn();

            if (isValidPosition(newLine, newColumn) && !isPositionOccupied(newLine, newColumn, player.getParts())) {
                possibleMoves.add(new Position(newLine, newColumn));
            }
        }
        return possibleMoves;
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 1 && line <= 5 && column >= 1 && column <= 5;
    }

    private boolean isPositionOccupied(int newLine, int newColumn, List<PartEntity> parts) {
        for (PartEntity part : parts) {
            PositionPart position = part.getPosition();
            if (position.getLine() == newLine && position.getColumn() == newColumn) {
                return true;
            }
        }
        return false;
    }

    @Transactional
    public PositionPart move(int line, int column, int lineNew, int columnNew, Long playerId, Long cardId) {
        CardEntity usedCard = cardService.findById(cardId);
        Position newPosition = new Position(lineNew, columnNew);
        PositionPart newPositionPart = new PositionPart(lineNew, columnNew);
        PlayerEntity player = playerService.findById(playerId);
        Position currentPosition = new Position(line, column);
        PositionPart currentPositionPart = new PositionPart(line, column);

        List<Position> possibleMoves = calculatePossibleMoves(currentPosition, usedCard, player);
        if (!possibleMoves.contains(newPosition)) {
            return null;  // Movimento não é válido, retorna null
        }

        PartEntity partToMove = partService.findPartAtPosition(player.getParts(), currentPositionPart);
        if (partToMove == null) {
            return null;  // Peça não encontrada na posição atual, retorna null
        }

        // Atualizar a posição da peça
        partToMove.setPosition(newPositionPart);
        PartEntity part = partService.save(partToMove);

        // Verificar e capturar peça do adversário
        partService.captureOpponentPartAtPosition(player, part.getPosition());

        // Trocar as cartas entre jogador e mesa
        cardService.swapCardsWithTable(player, usedCard);

        return partToMove.getPosition();  // Retorna a nova posição da peça
    }
}
