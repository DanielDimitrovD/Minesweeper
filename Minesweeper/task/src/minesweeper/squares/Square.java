package minesweeper.squares;

import minesweeper.Board;

public abstract class Square {

    protected int row;
    protected int col;
    protected char symbol;
    protected boolean isBombSquare;

    public Square(int row, int col, char symbol) {
        this.row = row;
        this.col = col;
        this.symbol = symbol;
        this.isBombSquare = false;

    }

    public abstract int getBombsInPerimeter(final Board board);

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public boolean isBombSquare() {
        return this.isBombSquare;
    }

    public void setBombSquare(boolean bombSquare) {
        isBombSquare = bombSquare;
    }


    public boolean isEmptySpace() {
        return symbol == '.' || symbol == '*';
    }

    public int getIndex() {
        return row * 9 + col;
    }

}
