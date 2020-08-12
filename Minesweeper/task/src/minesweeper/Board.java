package minesweeper;

import minesweeper.squares.CornerSquare;
import minesweeper.squares.InnerSquare;
import minesweeper.squares.SideSquare;
import minesweeper.squares.Square;

import java.util.*;

public class Board {

    private static final int BOARD_ROW = 9;

    private static HashSet<Integer> minePositions = new HashSet<>();
    private static HashSet<Integer> playerAnswers = new HashSet<>();


    private boolean isFirstMove;
    private Square[][] minefield;

    public Board(boolean isFirstMove) {
        this.isFirstMove = isFirstMove;
        initializeMinefield();
    }

    private static boolean isValidRow(final int row) {
        return row >= 0 && row <= 8;
    }

    private static boolean isValidColumn(final int col) {
        return col >= 0 && col <= 8;
    }

    private void initializeMinefield() {
        minefield = new Square[BOARD_ROW][BOARD_ROW];

        setCornerSquares(minefield);

        setSideSquares(minefield);

        setInnerSquares(minefield);

    }

    private void setInnerSquares(Square[][] minefield) {

        for (int i = 1; i < 8; i++) {

            for (int j = 1; j < 8; j++) {

                minefield[i][j] = new InnerSquare(i, j, '.');
            }
        }

    }

    private void setSideSquares(Square[][] minefield) {

        //top and bottom side
        for (int j = 1; j < 8; j++) {

            minefield[0][j] = new SideSquare(0, j, '.');

            minefield[8][j] = new SideSquare(8, j, '.');

        }


        //left and right side
        for (int i = 1; i < 8; i++) {

            minefield[i][0] = new SideSquare(i, 0, '.');

            minefield[i][8] = new SideSquare(i, 8, '.');
        }
    }

    private void setCornerSquares(Square[][] minefield) {

        minefield[0][0] = new CornerSquare(0, 0, '.');

        minefield[0][BOARD_ROW - 1] = new CornerSquare(0, BOARD_ROW - 1, '.');

        minefield[BOARD_ROW - 1][0] = new CornerSquare(BOARD_ROW - 1, 0, '.');

        minefield[BOARD_ROW - 1][BOARD_ROW - 1] = new CornerSquare(BOARD_ROW - 1, BOARD_ROW - 1, '.');
    }

    public void addHints(int x, int y) {

        Deque<Square> squares = new ArrayDeque<>();

        HashSet<Square> visited = new HashSet<>();

        Square initialSquare = minefield[x][y];

        squares.offer(initialSquare);

        while (!squares.isEmpty()) {

            Square currentSquare = squares.poll();

            if (visited.contains(currentSquare)) {
                continue;
            }


            visited.add(currentSquare);

            int bombsInPerimeter = currentSquare.getBombsInPerimeter(this);

            int row = currentSquare.getRow();
            int col = currentSquare.getCol();

            if (bombsInPerimeter == 0) {

                currentSquare.setSymbol('/');

                // add top square
                if (isValidRow(row - 1)) {

                    squares.offer(minefield[row - 1][col]);

                }

                // add bottom square
                if (isValidRow(row + 1)) {

                    squares.offer(minefield[row + 1][col]);

                }

                // add left square
                if (isValidColumn(col - 1)) {

                    squares.offer(minefield[row][col - 1]);

                }

                // add right square
                if (isValidColumn(col + 1)) {

                    squares.offer(minefield[row][col + 1]);

                }

                // add top-left square
                if (isValidRow(row - 1) && isValidColumn(col - 1)) {

                    squares.offer(minefield[row - 1][col - 1]);

                }
                // add top-right square
                if (isValidRow(row - 1) && isValidColumn(col + 1)) {

                    squares.offer(minefield[row - 1][col + 1]);
                }
                // add bottom-left square
                if (isValidRow(row + 1) && isValidColumn(col - 1)) {

                    squares.offer(minefield[row + 1][col - 1]);
                }
                // add bottom-right square
                if (isValidRow(row + 1) && isValidColumn(col + 1)) {

                    squares.offer(minefield[row + 1][col + 1]);
                }

            } else {
                minefield[row][col].setSymbol((char) (bombsInPerimeter + '0'));

            }
        }

    }


    public void playGame() {

        final Scanner scanner = new Scanner(System.in);

        while (!playerAnswers.equals(minePositions)) {

            if ((getNumberOfDots() == minePositions.size())) {
                break;
            }

            printBoard();

            System.out.print("Set/unset mines marks or claim a cell as free:");


            int y = scanner.nextInt() - 1;
            int x = scanner.nextInt() - 1;

            String command = scanner.next();

            if ("mine".equals(command)) {

                int index = x * minefield.length + y;

                if (playerAnswers.contains(index)) {

                    playerAnswers.remove(index);
                    minefield[x][y].setSymbol('.');

                } else {
                    playerAnswers.add(index);
                    minefield[x][y].setSymbol('*');

                }
            } else if ("free".equals(command)) {

                int index = x * minefield.length + y;

                // user loses
                if (minePositions.contains(index)) {

                    System.out.println("You stepped on a mine and failed!");
                    System.exit(1);

                } // square is near a bomb
                else if (minefield[x][y].isEmptySpace() && minefield[x][y].getBombsInPerimeter(this) > 0) {

                    int bombsInPerimeter = minefield[x][y].getBombsInPerimeter(this);

                    minefield[x][y].setSymbol((char) (bombsInPerimeter + '0'));
                    //   addHints(x, y);

                } // square is free from mines
                else {
                    addHints(x, y);
                }
            }

            //   printBoard();
        }
        printBoard();
        System.out.println("Congratulations! You found all mines!");
    }

    private int getNumberOfDots() {

        int counter = 0;

        for (Square[] squares : minefield) {

            for (Square square : squares) {

                if (square.isBombSquare() || square.isEmptySpace()) {
                    counter++;
                }
            }
        }

        return counter;

    }


    public Square[][] getMinefield() {
        return minefield;
    }

    @Override
    public String toString() {
        return "Board{" +
                "minefield=" + Arrays.toString(minefield) +
                '}';
    }

    public void printBoard() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");

        for (int i = 0; i < minefield.length; i++) {

            System.out.print(i + 1 + "|");

            for (int j = 0; j < minefield[i].length; j++) {


                if (minefield[i][j].isBombSquare()) {

                    int index = i * 9 + j;

                    if (playerAnswers.contains(index)) {
                        System.out.print('*');
                    } else {
                        System.out.print('.');
                    }

                } else {

                    System.out.print(minefield[i][j].getSymbol());

                }
            }

            System.out.println("|");

        }

        System.out.println("—│—————————│");

    }

    public void printMineField() {
        System.out.println(" │123456789│");
        System.out.println("—│—————————│");

        for (int i = 0; i < minefield.length; i++) {

            System.out.print(i + 1 + "|");

            for (int j = 0; j < minefield[i].length; j++) {

                System.out.print(minefield[i][j].getSymbol());

            }
            System.out.println("|");
        }


        System.out.println("—│—————————│");

    }

    public void initializeBombs(int mines, int x, int y) {

        int forbiddenIndex = x * 9 + y;

        Random random = new Random();

        for (int i = 0; i < mines; i++) {

            int pseudoPosition = random.nextInt(81);

            while (minePositions.contains(pseudoPosition) || pseudoPosition == forbiddenIndex) {
                pseudoPosition = random.nextInt(81);
            }

            minePositions.add(pseudoPosition);

        }

        for (Square[] squareArray : minefield) {
            for (Square square : squareArray) {

                if (minePositions.contains(square.getIndex())) {
                    int i = square.getRow();
                    int j = square.getCol();

                    minefield[i][j].setBombSquare(true);
                }
            }
        }

    }
}
