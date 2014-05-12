package week9.exercise1.calculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MathCalculator {
    public static void main(String[] args) throws IOException {
        MathSession session = new MathSession();
        BufferedReader reader = new BufferedReader(new InputStreamReader(
                System.in));
        String inputLine = reader.readLine();
        while (inputLine != null) {
            String outputLine = session.processCommand(inputLine);
            System.out.println(outputLine);
            inputLine = reader.readLine();
        }
    }
}
