package week4.aufgabe1;

import java.util.concurrent.CountDownLatch;

public class BrokenCyclicLatch {
    private static final int      NOF_ROUNDS  = 10;
    private static final int      NOF_THREADS = 2;

    private static CountDownLatch latch       = new CountDownLatch(NOF_THREADS);

    private static void multiRounds(int threadId) throws InterruptedException {
        for (int round = 0; round < NOF_ROUNDS; round++) {
            latch.countDown();
            latch.await();
            if (threadId == 0) {
                latch = new CountDownLatch(NOF_THREADS); // new latch for new
                                                         // round
            }
            System.out.println("Round " + round + " thread " + threadId);
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < NOF_THREADS; i++) {
            int threadId = i;
            new Thread(() -> {
                try {
                    multiRounds(threadId);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
