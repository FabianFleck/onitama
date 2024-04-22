package br.com.onitama.model.entity;

import javax.persistence.Embeddable;

@Embeddable
public class PositionPart {
    private int line;
    private int column;

    public PositionPart() {
    }

    public PositionPart(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }
}
