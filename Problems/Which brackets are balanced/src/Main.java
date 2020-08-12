import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        Deque<Character> stack = new ArrayDeque<>();

        String input = scanner.nextLine();

        for (char c : input.toCharArray()) {

            if (c == '{' || c == '(' || c == '[') {

                stack.push(c);
            } else if (c == '}' || c == ')' || c == ']') {

                if (stack.isEmpty()) {
                    System.out.println(false);
                    return;
                }

                char openingTag = stack.pop();

                if (openingTag != '{' && c == '}') {
                    System.out.println(false);
                    return;
                }

                if (openingTag != '[' && c == ']' ) {
                    System.out.println(false);
                    return;
                }

                if (openingTag != '(' && c == ')') {
                    System.out.println(false);
                    return;
                }

            } else {
                System.out.println(false);
                return;
            }
        }

        if (!stack.isEmpty()) {
            System.out.println(false);
            return;
        }

        System.out.println(true);


    }
}