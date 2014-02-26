package week1.exercises;

import java.math.BigInteger;
import java.util.Scanner;

/**
 * @author msyfrig
 */
public class DoubleFactorial {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            System.out
                    .println("Enter a number to calculate the double factiorial from (can take some seconds)");
            int readValue = Common.readUntilUnsignedInt(scanner);
            // special case 0
            if (readValue == 0) {
                readValue = 1;
            }
            // Math.abs for case -1
            int n = Math.abs(readValue);
            BigInteger result = BigInteger.ONE;

            for (int i = n % 2 == 0 ? 2 : 1; i <= n - 2; i += 2) {
                result = result.multiply(BigInteger.valueOf(i));
            }
            result = result.multiply(BigInteger.valueOf(n));

            System.out.println(result);
        }
    }
}
