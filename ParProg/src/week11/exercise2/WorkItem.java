package week11.exercise2;

import java.util.concurrent.Callable;

/**
 * Diese Klasse simuliert ein gewisse Arbeitslast indem für eine zufällige Zahl
 * die Zahl der Primzahlen gezählt wird die kleiner als die Zahl sind.
 * Zusätzlich wird diese Berechnung noch verzögert, und in rund 10% der Fälle
 * wird eine Exception geworfen.
 */
public class WorkItem implements Callable<Long> {
    final long numberToTest = (long) (Math.random() * 100);

    @Override
    public Long call() throws InterruptedException {

        if (Math.random() >= 0.9) {
            throw new RuntimeException("Crashed");
        }

        Thread.sleep(1000);

        return countPrimes(numberToTest);
    }

    public String getDescription() {
        return "How many primes are there up to " + numberToTest + "?";
    }

    private boolean isPrime(long number) {
        for (long factor = 2; factor * factor <= number; factor++) {
            if (number % factor == 0) {
                return false;
            }
        }
        return true;
    }

    private long countPrimes(long end) {
        long count = 0;
        for (long number = 2; number < end; number++) {
            if (isPrime(number)) {
                count++;
            }
        }
        return count;
    }
}