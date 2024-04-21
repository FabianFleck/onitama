package br.com.onitama.model;

public class Part {
    private Position position;
    private PartType partType;

    public Part(Position position, PartType partType) {
        this.position = position;
        this.partType = partType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public PartType getPartType() {
        return partType;
    }

    public void setPartType(PartType partType) {
        this.partType = partType;
    }
}
