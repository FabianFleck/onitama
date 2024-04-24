package br.com.onitama.model.entity;

import javax.persistence.Embeddable;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionPart that = (PositionPart) o;
        return line == that.line && column == that.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}
