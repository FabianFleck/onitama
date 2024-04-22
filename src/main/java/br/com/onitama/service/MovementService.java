package br.com.onitama.service;

import br.com.onitama.model.Position;
import br.com.onitama.model.entity.*;
import br.com.onitama.model.enumeration.ColorEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovementService {

    private final CardService cardService;
    private final PlayerService playerService;

    public MovementService(CardService cardService, PlayerService playerService) {
        this.cardService = cardService;
        this.playerService = playerService;
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
}
