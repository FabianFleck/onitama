package br.com.onitama.model;

public class Position {
    private int line;
    private char column;

    public Position(int line, char column) {
        this.line = line;
        this.column = column;
    }

    public int getLine() {
        return this.line;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public char getColumn() {
        return this.column;
    }

    public void setColumn(char column) {
        this.column = column;
    }
}
