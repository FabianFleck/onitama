package br.com.onitama.model;

import br.com.onitama.model.enumeration.PartTypeEnum;

public class Part {
    private Position position;
    private PartTypeEnum partTypeEnum;

    public Part(Position position, PartTypeEnum partTypeEnum) {
        this.position = position;
        this.partTypeEnum = partTypeEnum;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PartTypeEnum getPartType() {
        return partTypeEnum;
    }

    public void setPartType(PartTypeEnum partTypeEnum) {
        this.partTypeEnum = partTypeEnum;
    }
}
