import java.util.Scanner;
import java.util.Stack;

public class Exam_Advance_2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập số ISBN (10 chữ số): ");
        String isbn = scanner.nextLine();

        if (isValidISBN(isbn)) {
            System.out.println(isbn + " là số ISBN hợp lệ.");
        } else {
            System.out.println(isbn + " không phải là số ISBN hợp lệ.");
        }

        scanner.close();
    }

    private static boolean isValidISBN(String isbn) {

        if (isbn.length() != 10 || !isbn.matches("\\d{10}")) {
            return false;
        }


        Stack<Character> stack = new Stack<>();
        for (char digit : isbn.toCharArray()) {
            stack.push(digit);
        }


        int sum = 0;
        for (int i = 1; i <= 10; i++) {
            int digit = stack.pop() - '0';
            sum += i * digit;
        }

        return sum % 11 == 0;
    }
}
