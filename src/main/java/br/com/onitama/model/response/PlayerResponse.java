package br.com.onitama.model.response;

import br.com.onitama.model.enumeration.ColorEnum;

import java.util.List;

public class PlayerResponse {
    private Long id;
    private String name;
    private String username;
    private ColorEnum color;
    private List<PartResponse> parts;
    private CardResponse card1;
    private CardResponse card2;

    public PlayerResponse() {
    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ColorEnum getColor() {
        return color;
    }

    public void setColor(ColorEnum color) {
        this.color = color;
    }

    public List<PartResponse> getParts() {
        return parts;
    }

    public void setParts(List<PartResponse> parts) {
        this.parts = parts;
    }

    public CardResponse getCard1() {
        return card1;
    }

    public void setCard1(CardResponse card1) {
        this.card1 = card1;
    }

    public CardResponse getCard2() {
        return card2;
    }

    public void setCard2(CardResponse card2) {
        this.card2 = card2;
    }
}
