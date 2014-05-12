public class PriorityThread {

    private static final int[] prio = { Thread.MIN_PRIORITY,
            Thread.NORM_PRIORITY, Thread.MAX_PRIORITY };

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = createThread(1000);
        t1.start();
        Thread t2 = createThread(2500);
        t2.start();

        t1.join();
        t2.join();
    }

    private static Thread createThread(final int sleeptime) {
        return new Thread((() -> {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.currentThread().setPriority(prio[i % prio.length]);
                    Thread.sleep(sleeptime);
                }
                catch (Exception anEx) {
                    anEx.printStackTrace();
                }
            }
        }));
    }
}
