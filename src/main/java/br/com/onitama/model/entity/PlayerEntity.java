package br.com.onitama.model.entity;

import br.com.onitama.model.enumeration.ColorEnum;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
