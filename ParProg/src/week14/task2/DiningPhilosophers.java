package week14.task2;

import java.util.concurrent.Semaphore;

class Fork {
    // TODO: Replace semaphore by usage of software transactions
    private Semaphore semaphore = new Semaphore(1);

    public void acquire() {
        try {
            semaphore.acquire();
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void release() {
        semaphore.release();
    }
}

class PhilosopherThread extends Thread {
    private final int  meals;
    private final Fork left;
    private final Fork right;

    public PhilosopherThread(int meals, Fork left, Fork right) {
        this.meals = meals;
        this.left = left;
        this.right = right;
    }

    @Override
    public void run() {
        for (int m = 0; m < meals; m++) {
            pickUpForks();
            // eat
            putDownForks();
        }
    }

    private void pickUpForks() {
        left.acquire();
        right.acquire();
    }

    private void putDownForks() {
        left.release();
        right.release();
    }
}

public class DiningPhilosophers {
    private static long measure(int tableSize, int meals)
            throws InterruptedException {
        Fork[] forks = new Fork[tableSize];
        for (int i = 0; i < tableSize; i++) {
            forks[i] = new Fork();
        }

        PhilosopherThread[] threads = new PhilosopherThread[tableSize];
        for (int i = 0; i < tableSize; i++) {
            int leftNo = i;
            int rightNo = (i + 1) % tableSize;

            // TODO: Remove lock ordering for deadlock avoidance in STM solution
            Fork left = forks[Math.min(leftNo, rightNo)];
            Fork right = forks[Math.max(leftNo, rightNo)];

            threads[i] = new PhilosopherThread(meals, left, right);
        }

        long start = System.currentTimeMillis();

        for (PhilosopherThread thread : threads) {
            thread.start();
        }

        for (PhilosopherThread thread : threads) {
            thread.join();
        }

        return System.currentTimeMillis() - start;
    }

    public static void main(String[] args) throws InterruptedException {
        final int meals = 1_000_000;
        for (int philosopherNo = 0; philosopherNo < 5; philosopherNo++) {
            long elapsed = measure(5, meals);
            System.out.printf("%3.1f s/meal\n", (elapsed * 1000.0) / meals);
        }
    }
}
