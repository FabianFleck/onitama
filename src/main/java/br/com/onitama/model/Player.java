package br.com.onitama.model;

import java.util.List;

public class Player {
    private String name;
    private PlayerColor color;
    private List<Part> part;
    private boolean movesDown; // True se avança para cima, false se avança para baixo

    public Player() {
    }

    public Player(boolean movesDown) {
        this.movesDown = movesDown;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setColor(PlayerColor color) {
        this.color = color;
    }

    public List<Part> getPart() {
        return part;
    }

    public void setPart(List<Part> part) {
        this.part = part;
    }

    public boolean isMovesDown() {
        return movesDown;
    }

    public void setMovesDown(boolean movesDown) {
        this.movesDown = movesDown;
    }
}
