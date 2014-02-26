package week1.exercises;

import java.util.Scanner;

/**
 * @author Marco
 */
public class Prime {

    /**
     * @param args
     */
    public static void main(String[] args) {
        boolean isPrime = true;
        System.out
                .println("Enter a number to check whether it’s a prime number or not");
        try (Scanner scanner = new Scanner(System.in)) {
            int n = Common.readUntilValidInt(scanner);
            if (n != 2 && n % 2 == 0 || (n < 2 && n > -2)) {
                isPrime = false;
            }
            for (int i = 3; i * i <= Math.sqrt(n); i += 2) {
                if (n % i == 0) {
                    isPrime = false;
                    break;
                }
            }
            System.out.println(n + " is " + (!isPrime ? "not" : "")
                    + " a prime number");
        }
    }
}
