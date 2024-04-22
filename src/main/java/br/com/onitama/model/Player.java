package br.com.onitama.model;

import br.com.onitama.model.enumeration.ColorEnum;

import java.util.List;

public class Player {
    private String name;
    private ColorEnum color;
    private List<Part> part;

    public Player() {
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

    public List<Part> getPart() {
        return part;
    }

    public void setPart(List<Part> part) {
        this.part = part;
    }
}
