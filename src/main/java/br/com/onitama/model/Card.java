package br.com.onitama.model;

import br.com.onitama.model.enumeration.ColorEnum;

import java.util.List;

public class Card {
    private String name;
    private List<Position> positions;
    private ColorEnum colorEnum;

    public Card(String name, List<Position> positions) {
        this.name = name;
        this.positions = positions;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Position> getPositions() {
        return positions;
    }

    public void setPositions(List<Position> positions) {
        this.positions = positions;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }
}
