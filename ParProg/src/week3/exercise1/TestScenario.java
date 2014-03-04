package week3.exercise1;

import java.util.ArrayList;
import java.util.List;

public class TestScenario {
    private static final int BUFFER_CAPACITY       = 5;
    // must be a multiple of #producers and #consumers
    private static final int TOTAL_ELEMENTS        = 1000000;
    private static final int MAX_ELEMENTS_PER_CALL = BUFFER_CAPACITY / 2;

    public static void main(String[] args) throws InterruptedException {
        testSeries(1, 1);
        testSeries(5, 5);
        testSeries(1, 10);
        testSeries(100, 100);
    }

    private static void testSeries(int nofProducers, int nofConsumers)
            throws InterruptedException {
        System.out.println("TEST SERIES: " + nofProducers + " producers "
                + nofConsumers + " consumers");
        test(new WarehouseWithMonitor(BUFFER_CAPACITY), "unfair", nofProducers,
                nofConsumers);
        test(new WarehouseWithSemaphore(BUFFER_CAPACITY, false), "unfair",
                nofProducers, nofConsumers);
        test(new WarehouseWithSemaphore(BUFFER_CAPACITY, true), "fair",
                nofProducers, nofConsumers);
        test(new WarehouseWithLockCondition(BUFFER_CAPACITY, false), "unfair",
                nofProducers, nofConsumers);
        test(new WarehouseWithLockCondition(BUFFER_CAPACITY, true), "fair",
                nofProducers, nofConsumers);
    }

    private static void test(Warehouse warehouse, String name,
            int nofProducers, int nofConsumers) throws InterruptedException {
        System.out.println("Test " + warehouse.getClass().getSimpleName() + " "
                + name);
        List<Thread> allThreads = new ArrayList<>();
        for (int i = 0; i < nofProducers; i++) {
            allThreads.add(new Producer(warehouse, TOTAL_ELEMENTS
                    / nofProducers, MAX_ELEMENTS_PER_CALL));
        }
        for (int i = 0; i < nofConsumers; i++) {
            allThreads.add(new Consumer(warehouse, TOTAL_ELEMENTS
                    / nofConsumers, MAX_ELEMENTS_PER_CALL));
        }
        long startTime = System.currentTimeMillis();
        for (Thread t : allThreads) {
            t.start();
        }
        for (Thread t : allThreads) {
            t.join();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Total time: " + (stopTime - startTime) + " ms");
    }
}
