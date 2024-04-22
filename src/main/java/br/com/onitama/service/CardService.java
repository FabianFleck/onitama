package br.com.onitama.service;

import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.repository.CardRepository;
import org.springframework.stereotype.Service;

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

    public CardEntity findById(Long cardId) {
        return this.repository.findById(cardId).orElse(null);
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
