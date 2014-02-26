package week1.exercises;

import java.util.Scanner;

/**
 * @author msyfrig
 */

public class MaxMin {

    public static void main(String[] someArgs) {
        int x, y;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Enter x: ");
            x = Common.readUntilValidInt(scanner);
            System.out.println("Enter y: ");
            y = Common.readUntilValidInt(scanner);
        }

        String result = "x (=" + x + ") is ";
        if (x > y) {
            result += "bigger than";
        }
        else if (x < y) {
            result += "smaller than";
        }
        else {
            result += "equal to";
        }
        result += " y (=" + y + ")";
        System.out.println(result);
    }
}
