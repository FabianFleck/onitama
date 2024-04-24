package br.com.onitama.model.entity;

import br.com.onitama.model.enumeration.ColorEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Enumerated(EnumType.STRING)
    private ColorEnum color;

    @OneToMany(mappedBy = "player", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PartEntity> parts;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card1_id")
    private CardEntity card1;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "card2_id")
    private CardEntity card2;

    @ManyToOne
    @JoinColumn(name = "battle_id")
    @JsonIgnore
    private BattleEntity battle;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public List<PartEntity> getParts() {
        return parts;
    }

    public void setParts(List<PartEntity> parts) {
        this.parts = parts;
    }

    public CardEntity getCard1() {
        return card1;
    }

    public void setCard1(CardEntity card1) {
        this.card1 = card1;
    }

    public CardEntity getCard2() {
        return card2;
    }

    public void setCard2(CardEntity card2) {
        this.card2 = card2;
    }

    public BattleEntity getBattle() {
        return battle;
    }

    public void setBattle(BattleEntity battle) {
        this.battle = battle;
    }
}
