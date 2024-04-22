package br.com.onitama.model.entity;

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
}
