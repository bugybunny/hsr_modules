package week1.exercises;

import java.util.Scanner;

/**
 * @author Marco
 */
public class Common {

    public static final int readUntilValidInt(Scanner aScanner) {
        int intValue = 0;
        boolean valid = false;
        while (!valid) {
            try {
                intValue = Integer.parseInt(aScanner.next());
                valid = true;
            }
            catch (NumberFormatException anEx) {
                System.err.println("Please enter a valid int value");
            }
        }
        return intValue;
    }

    public static final int readUntilUnsignedInt(Scanner aScanner) {
        int intValue = 0;
        boolean valid = false;
        while (!valid) {
            try {
                intValue = Integer.parseInt(aScanner.next());
                if (intValue < -1) {
                    System.err
                            .println("Please enter a int value bigger than -1");
                }
                else {
                    valid = true;
                }
            }
            catch (NumberFormatException anEx) {
                System.err.println("Please enter a valid int value");
            }
        }
        return intValue;
    }

}
