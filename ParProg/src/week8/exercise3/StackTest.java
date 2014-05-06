package week8.exercise3;

import java.util.ArrayList;
import java.util.List;

public class StackTest {
    private static final int NOF_THREADS  = 100;
    private static final int NOF_ELEMENTS = 1000000;

    public static void main(String[] args) throws InterruptedException {
        // test(new SynchronizedStack<Integer>());
        test(new LockFreeStack<Integer>());
    }

    private static void test(final Stack<Integer> stack)
            throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Thread> allThreads = new ArrayList<>();
        for (int i = 0; i < NOF_THREADS; i++) {
            Thread t = new Thread() {
                @Override
                public void run() {
                    for (int k = 0; k < NOF_ELEMENTS; k++) {
                        stack.push(k);
                        Integer x = stack.pop();
                        if (x == null) {
                            throw new AssertionError("Broken implementation");
                        }
                    }
                }
            };
            allThreads.add(t);
            t.start();
        }
        for (Thread t : allThreads) {
            t.join();
        }
        long stop = System.currentTimeMillis();
        System.out.println(stack.getClass().getName() + ": " + (stop - start)
                + "ms");
    }
}
