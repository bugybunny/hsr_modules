package week1.exercise3;

import java.util.ArrayList;
import java.util.List;

public class PrimeCounter extends Thread {
    private long count;
    private long startRange;
    private long endRange;

    public PrimeCounter(long aStartRange, long anEndRange) {
        super();
        startRange = aStartRange;
        endRange = anEndRange;
    }

    @Override
    public void run() {
        count = 0;
        for (long number = startRange; number < endRange; number++) {
            if (isPrime(number)) {
                count++;
            }
        }
    }

    private static boolean isPrime(long number) {
        for (long factor = 2; factor * factor <= number; factor++) {
            if (number % factor == 0) {
                return false;
            }
        }
        return true;
    }

    private static final long START = 1_000_000L;
    private static final long END   = 10_000_000L;

    public static void main(String[] args) throws InterruptedException {
        int numberOfThreads = 10;
        long totalNumberOfPrimes = 0;
        long range = (END - START) / numberOfThreads;

        List<PrimeCounter> threadList = new ArrayList<>(numberOfThreads);

        for (int threadNumber = 0; threadNumber < numberOfThreads; threadNumber++) {
            long start = START + (range * threadNumber);
            long end = start + range;
            PrimeCounter t = new PrimeCounter(start, end);
            threadList.add(t);
        }

        long startTime = System.currentTimeMillis();
        for (Thread tempThreadToStart : threadList) {
            tempThreadToStart.start();
        }

        for (PrimeCounter tempThread : threadList) {
            tempThread.join();
            totalNumberOfPrimes += tempThread.count;
        }

        long endTime = System.currentTimeMillis();
        System.out.println("#Primes: " + totalNumberOfPrimes + " Time: "
                + (endTime - startTime) + " ms");
    }
}
