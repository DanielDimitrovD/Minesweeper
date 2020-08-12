package minesweeper;

import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        Board board = new Board(true);

        System.out.println("How many mines do you want on the field?");

        int mines = scanner.nextInt();

        System.out.println("Initial board");
        board.printMineField();


        System.out.print("Set/unset mines marks or claim a cell as free:");

        int y = scanner.nextInt() - 1;

        int x = scanner.nextInt() - 1;

        String command = scanner.next();

        if ("free".equals(command)) {
            board.initializeBombs(mines, x, y);
        }

        board.addHints(x, y);

        board.playGame();
    }
}
