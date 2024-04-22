package br.com.onitama.service;

import br.com.onitama.model.Card;
import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.model.Position;
import br.com.onitama.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;

    public CardService(CardRepository repository) {
        this.repository = repository;
    }

    public List<CardEntity> getAllCards() {
        return repository.findAll();
    }

    public List<Position> getPossibleMoves(int line, int column, ColorEnum colorEnum) {
        return calculatePossibleMoves(new Position(line, column), getCardByName(), colorEnum);
    }

    private Card getCardByName() {
        List<Position> elephantMoves = new ArrayList<>();
        elephantMoves.add(new Position(-1, 1));  // Avança uma linha e uma coluna para a direita
        elephantMoves.add(new Position(-1, -1)); // Avança uma linha e uma coluna para a esquerda
        elephantMoves.add(new Position(0, 1));  // Move uma coluna para a direita na mesma linha
        elephantMoves.add(new Position(0, -1)); // Move uma coluna para a esquerda na mesma linha
        return new Card("Elephant", elephantMoves);
    }

    public List<Position> calculatePossibleMoves(Position currentPosition, Card card, ColorEnum colorEnum) {
        List<Position> possibleMoves = new ArrayList<>();
        int lineDirection = colorEnum == ColorEnum.RED ? 1 : -1;

        for (Position move : card.getPositions()) {
            int newLine = currentPosition.getLine() + (move.getLine() * lineDirection);
            int newColumn = currentPosition.getColumn() + move.getColumn();

            if (isValidPosition(newLine, newColumn)) {
                possibleMoves.add(new Position(newLine, newColumn));
            }
        }
        return possibleMoves;
    }

    private boolean isValidPosition(int line, int column) {
        return line >= 1 && line <= 5 && column >= 1 && column <= 5;
    }
}
