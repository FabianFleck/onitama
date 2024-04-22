package br.com.onitama.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Objects;

@Schema(description = "Objeto de posição")
public class Position {
    @Schema(description = "Linha da posição", example = "5")
    private int line;

    @Schema(description = "Coluna da posição", example = "3")
    private int column;

    public Position() {
    }

    public Position(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public int getColumn() {
        return this.column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return line == position.line && column == position.column;
    }

    @Override
    public int hashCode() {
        return Objects.hash(line, column);
    }
}
