package br.com.onitama.service;

import br.com.onitama.error.exception.UnprocessableEntityException;
import br.com.onitama.mapper.BattleMapper;
import br.com.onitama.model.entity.BattleEntity;
import br.com.onitama.model.entity.CardEntity;
import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.response.BattleResponse;
import br.com.onitama.repository.CardRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class CardService {

    private final CardRepository repository;
    private final BattleService battleService;
    private final PlayerService playerService;
    private final BattleMapper mapper;

    public CardService(CardRepository repository, BattleService battleService, PlayerService playerService, BattleMapper mapper) {
        this.repository = repository;
        this.battleService = battleService;
        this.playerService = playerService;
        this.mapper = mapper;
    }

    public List<CardEntity> getAllCards() {
        return repository.findAll();
    }

    public CardEntity findById(Long cardId) {
        return this.repository.findById(cardId)
                .orElseThrow(() -> new UnprocessableEntityException("Card não encontrado."));
    }

    private void registeredPlayers(BattleEntity battle) {
        if (battle.getPlayer1() == null || battle.getPlayer2() == null) {
            throw new UnprocessableEntityException("A batalha não está preenchida com os dois jogadores.");
        }
    }

    public BattleResponse distributeCards(String battleId) {
        BattleEntity battle = battleService.findById(battleId);
        registeredPlayers(battle);
        distributeCards(battle);

        List<CardEntity> allCards = repository.findAll();
        Collections.shuffle(allCards);

        battle.getPlayer1().setCard1(allCards.get(0)); // 2 cartas para Player1
        battle.getPlayer1().setCard2(allCards.get(1)); // 2 cartas para Player1
        battle.getPlayer2().setCard1(allCards.get(2));  // 2 cartas para Player2
        battle.getPlayer2().setCard2(allCards.get(3));  // 2 cartas para Player2
        battle.setTableCard(allCards.get(4)); // 1 carta na mesa

        battle.initializeTableCard();

        BattleEntity battleSave = battleService.save(battle);

        BattleResponse battleResponse = mapper.toBattleResponse(battleSave);

        battleService.notifyBattleUpdates(battleResponse);

        return battleResponse;
    }

    private void distributeCards(BattleEntity battle) {
        if (battle.getPlayer1().getCard1() != null
                && battle.getPlayer1().getCard2() != null
                && battle.getPlayer2().getCard1() != null
                && battle.getPlayer2().getCard2() != null
                && battle.getTableCard() != null) {
            throw new UnprocessableEntityException("As cartas já foram distribuídas para essa batalha.");
        }
    }

    public void swapCardsWithTable(PlayerEntity player, CardEntity usedCard) {
        BattleEntity battle = battleService.findByPlayer(player);
        CardEntity tableCard = battle.getTableCard();
        battle.setTableCard(usedCard);
        if (player.getCard1().equals(usedCard)) {
            player.setCard1(tableCard);
        } else if (player.getCard2().equals(usedCard)) {
            player.setCard2(tableCard);
        }
        playerService.save(player);
        battleService.save(battle);
    }

    public CardEntity findById(PlayerEntity player, Long cardId) {
        if (player.getCard1().getId().equals(cardId)) {
            return player.getCard1();
        } else if (player.getCard2().getId().equals(cardId)) {
            return player.getCard2();
        }
        throw new UnprocessableEntityException("Card não encontrado para o player.");
    }
}
