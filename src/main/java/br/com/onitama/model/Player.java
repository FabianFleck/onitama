package br.com.onitama.model;

import java.util.List;

public class Player {

    private String name;
    private PlayerColor color;
    private List<Part> part;

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
}
