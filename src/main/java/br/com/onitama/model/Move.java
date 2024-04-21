package br.com.onitama.model;

public class Move {

    private int deltaX;
    private int deltaY;

    public Move(int deltaX, int deltaY) {
        this.deltaX = deltaX;
        this.deltaY = deltaY;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }
}
