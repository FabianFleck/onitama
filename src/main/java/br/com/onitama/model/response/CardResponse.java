package br.com.onitama.model.response;

import br.com.onitama.model.enumeration.ColorEnum;

import java.util.List;

public class CardResponse {
    private Long id;
    private String name;
    private List<PositionResponse> positions;
    private ColorEnum colorEnum;

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

    public List<PositionResponse> getPositions() {
        return positions;
    }

    public void setPositions(List<PositionResponse> positionResponses) {
        this.positions = positionResponses;
    }

    public ColorEnum getColorEnum() {
        return colorEnum;
    }

    public void setColorEnum(ColorEnum colorEnum) {
        this.colorEnum = colorEnum;
    }
}
