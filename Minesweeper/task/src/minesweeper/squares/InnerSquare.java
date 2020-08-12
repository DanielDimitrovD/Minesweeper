package minesweeper.squares;

import minesweeper.Board;

public class InnerSquare extends Square {

    public InnerSquare(int row, int col, char symbol) {
        super(row, col, symbol);
    }

    @Override
    public int getBombsInPerimeter(Board board) {

        int bombsAroundEmptySpace = 0;

        int i = row;
        int j = col;

        Square[][] arr = board.getMinefield();

        // top
        if (arr[i - 1][j].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // bottom
        if (arr[i + 1][j].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // left
        if (arr[i][j - 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // right
        if (arr[i][j + 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // top-left
        if (arr[i - 1][j - 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // top-right
        if (arr[i - 1][j + 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // bottom-left
        if (arr[i + 1][j - 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }
        // bottom-right
        if (arr[i + 1][j + 1].isBombSquare()) {
            bombsAroundEmptySpace++;
        }


        return bombsAroundEmptySpace;
    }
}

