package br.com.onitama.model.entity;

import br.com.onitama.model.enumeration.BattleResultEnum;
import br.com.onitama.model.enumeration.ColorEnum;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "battle")
public class BattleEntity {

    @Id
    private String id = UUID.randomUUID().toString(); // Gera um ID único

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player1_id", referencedColumnName = "id")
    private PlayerEntity player1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "player2_id", referencedColumnName = "id")
    private PlayerEntity player2;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "table_card_id")
    private CardEntity tableCard;

    @Enumerated(EnumType.STRING)
    @Column(name = "current_player")
    private ColorEnum currentPlayer;

    @Enumerated(EnumType.STRING)
    @Column(name = "result")
    private BattleResultEnum result;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public PlayerEntity getPlayer1() {
        return player1;
    }

    public void setPlayer1(PlayerEntity player1) {
        this.player1 = player1;
    }

    public PlayerEntity getPlayer2() {
        return player2;
    }

    public void setPlayer2(PlayerEntity player2) {
        this.player2 = player2;
    }

    public CardEntity getTableCard() {
        return tableCard;
    }

    public void setTableCard(CardEntity tableCard) {
        this.tableCard = tableCard;
    }

    public ColorEnum getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void nextPlayer() {
        this.currentPlayer = currentPlayer == ColorEnum.RED ? ColorEnum.BLUE : ColorEnum.RED;
    }

    public void initializeTableCard() {
        this.currentPlayer = this.tableCard.getColorEnum();
    }

    public BattleResultEnum getResult() {
        return result;
    }

    public void setResult(BattleResultEnum result) {
        this.result = result;
    }
}
