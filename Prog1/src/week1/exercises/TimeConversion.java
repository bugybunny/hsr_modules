package week1.exercises;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author Marco
 */
public class TimeConversion {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            System.out
                    .println("Enter an int value in seconds that you want to convert:");
            int timeInSeconds = Common.readUntilValidInt(scanner);

            int days = timeInSeconds / (60 * 60 * 24);
            int hours = timeInSeconds / 3600 - (days * 24);
            int minutes = timeInSeconds / 60 - (days * 1440) - (hours * 60);
            int seconds = timeInSeconds % 60;

            DecimalFormat nf = new DecimalFormat("00");
            System.out.println(days + "d " + nf.format(hours) + "h "
                    + nf.format(minutes) + "m " + nf.format(seconds) + "s");
        }
    }
}
