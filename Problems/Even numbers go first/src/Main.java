import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        Deque<Integer> deque = new ArrayDeque<>();

        int n = scanner.nextInt();

        for (int i = 0; i < n; i++) {

            int number = scanner.nextInt();

            if (number % 2 == 0) {
                deque.offerFirst(number);
            }
            else {
                deque.offerLast(number);
            }

        }

        for (int i : deque) {
            System.out.println(i);
        }



    }
}