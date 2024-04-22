package br.com.onitama.service;

import br.com.onitama.model.Position;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.model.entity.PositionEntity;
import br.com.onitama.model.enumeration.ColorEnum;
import br.com.onitama.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;
    private final BattleService battleService;

    public CardService(CardRepository repository, BattleService battleService) {
        this.repository = repository;
        this.battleService = battleService;
    }

    public List<CardEntity> getAllCards() {
        return repository.findAll();
    }

    public List<Position> getPossibleMoves(int line, int column, ColorEnum colorEnum, Long cardId) {
        return calculatePossibleMoves(new Position(line, column), findById(cardId), colorEnum);
    }

    public CardEntity findById(Long cardId) {
        return this.repository.findById(cardId).orElse(null);
    }

    public List<Position> calculatePossibleMoves(Position currentPosition, CardEntity card, ColorEnum colorEnum) {
        List<Position> possibleMoves = new ArrayList<>();
        int lineDirection = colorEnum == ColorEnum.RED ? 1 : -1;

        for (PositionEntity move : card.getPositions()) {
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

    public BattleEntity distributeCards(String battleId) {
        List<CardEntity> allCards = repository.findAll();
        Collections.shuffle(allCards);

        BattleEntity battle = battleService.findById(battleId);

        battle.getPlayer1().setCard1(allCards.get(0)); // 2 cartas para Player1
        battle.getPlayer1().setCard2(allCards.get(1)); // 2 cartas para Player1
        battle.getPlayer2().setCard1(allCards.get(2));  // 2 cartas para Player2
        battle.getPlayer2().setCard2(allCards.get(3));  // 2 cartas para Player2
        battle.setTableCard(allCards.get(4)); // 1 carta na mesa

        return battleService.save(battle);
    }
}
