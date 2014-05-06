package week8.exercise1;

public class PetersonTest {
    private static int counter;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        final int NofRounds = 100000;
        final PetersonMutex mutex = new PetersonMutex();
        counter = 0;

        Thread t0 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < NofRounds; i++) {
                    mutex.thread0Lock();
                    counter++;
                    mutex.thread0Unlock();
                }
            }
        };
        Thread t1 = new Thread() {
            @Override
            public void run() {
                for (int i = 0; i < NofRounds; i++) {
                    mutex.thread1Lock();
                    counter--;
                    mutex.thread1Unlock();
                }
            }
        };
        t0.start();
        t1.start();
        t0.join();
        t1.join();
        if (counter != 0) {
            System.out.println(System.currentTimeMillis() - start);
            throw new AssertionError("Wrong synchronization");
        }
        System.out.println(System.currentTimeMillis() - start);
        System.out.println("Completed");
    }
}
