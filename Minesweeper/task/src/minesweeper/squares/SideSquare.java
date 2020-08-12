package minesweeper.squares;

import minesweeper.Board;

public class SideSquare extends Square {


    public SideSquare(int row, int col, char symbol) {
        super(row, col, symbol);
    }

    @Override
    public int getBombsInPerimeter(Board board) {

        int bombsAroundEmptySpace = 0;


        int i = row;
        int j = col;

        Square[][] arr = board.getMinefield();

        // top side
        if (i == 0) {

            if (arr[i][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        }
        // bottom side
        else if (i == 8) {

            if (arr[i][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        }
        // left side
        else if (j == 0) {

            if (arr[i - 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j + 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        }
        // right side
        else if (j == 8) {

            if (arr[i - 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i - 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }

            if (arr[i + 1][j - 1].isBombSquare()) {
                bombsAroundEmptySpace++;
            }
        }


        return bombsAroundEmptySpace;
    }
}

