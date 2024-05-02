package br.com.onitama.model.response;

import br.com.onitama.model.entity.PlayerEntity;
import br.com.onitama.model.enumeration.BattleResultEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class BattleSimpleResponse {
    @Schema(description = "Identificador da batalha.", example = "28c4a87a-95a0-4929-be13-e0961b60f3eb")
    private String id;

    private String playerOne;

    private String playerTwo;

    private BattleResultEnum result;

    public BattleSimpleResponse(String id, PlayerEntity playerOne, PlayerEntity playerTwo, BattleResultEnum result) {
        this.id = id;
        this.playerOne = playerOne != null ? playerOne.getUser().getUsername() : null;
        this.playerTwo = playerTwo != null ? playerTwo.getUser().getUsername() : null;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPlayerOne() {
        return playerOne;
    }

    public void setPlayerOne(String playerOne) {
        this.playerOne = playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public void setPlayerTwo(String playerTwo) {
        this.playerTwo = playerTwo;
    }

    public BattleResultEnum getResult() {
        return result;
    }

    public void setResult(BattleResultEnum result) {
        this.result = result;
    }
}
