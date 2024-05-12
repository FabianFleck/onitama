package br.com.onitama.model.response;

import br.com.onitama.model.enumeration.PartTypeEnum;

public class PartResponse {
    private PositionResponse position;
    private PartTypeEnum type;

    public PartResponse(PositionResponse position, PartTypeEnum type) {
        this.position = position;
        this.type = type;
    }

    public PositionResponse getPosition() {
        return position;
    }

    public void setPosition(PositionResponse position) {
        this.position = position;
    }

    public PartTypeEnum getType() {
        return type;
    }

    public void setType(PartTypeEnum type) {
        this.type = type;
    }
}
