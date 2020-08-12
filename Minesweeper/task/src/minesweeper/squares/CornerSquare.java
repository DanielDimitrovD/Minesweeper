package minesweeper.squares;

import minesweeper.Board;

public class CornerSquare extends Square {


    public CornerSquare(int row, int col, char symbol) {
        super(row, col, symbol);
    }


    @Override
    public int getBombsInPerimeter(final Board board) {

        int bombsAroundEmptySpace = 0;

        int i = row;
        int j = col;

        Square[][] arr = board.getMinefield();

        // top left corner
        if (i == 0 && j == 0) {

            if (arr[i][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }


        } // top right corner
        else if (i == 0 && j == 8) {

            if (arr[i][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j].getSymbol() == 'X') {
                bombsAroundEmptySpace++;
            }
        } // bottom left corner
        else if (i == 8 && j == 0) {

            if (arr[i - 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        } // bottom right corner
        else if (i == 8 && j == 8) {

            if (arr[i - 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        }

        return bombsAroundEmptySpace;
    }
}

