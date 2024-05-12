package br.com.onitama.model.response;

import br.com.onitama.model.enumeration.BattleResultEnum;
import br.com.onitama.model.enumeration.ColorEnum;

public class BattleResponse {
    private String id;
    private PlayerResponse player1;
    private PlayerResponse player2;
    private CardResponse tableCard;
    private ColorEnum currentPlayer;
    private BattleResultEnum result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerResponse getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerResponse player1) {
        this.player1 = player1;
    }

    public PlayerResponse getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerResponse player2) {
        this.player2 = player2;
    }

    public CardResponse getTableCard() {
        return tableCard;
    }

    public void setTableCard(CardResponse tableCard) {
        this.tableCard = tableCard;
    }

    public ColorEnum getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(ColorEnum currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public BattleResultEnum getResult() {
        return result;
    }

    public void setResult(BattleResultEnum result) {
        this.result = result;
    }
}
