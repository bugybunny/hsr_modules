package week4.exercise3.b;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.junit.After;
import org.junit.Before;

public class ConcurrentTest {
    private static final int                 TIMEOUT            = 1000;

    private ConcurrentLinkedQueue<Throwable> uncaughtExceptions = new ConcurrentLinkedQueue<>();

    @Before
    public void before() {
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread thread, Throwable exception) {
                uncaughtExceptions.add(exception);
            }
        });
    }

    @After
    public void after() {
        for (Thread thread : Thread.getAllStackTraces().keySet()) {
            if (thread != Thread.currentThread() && !thread.isDaemon()
                    && !isSystemThread(thread)) {
                try {
                    thread.join(TIMEOUT);
                    if (thread.isAlive()) {
                        throw new RuntimeException("Thread join timed out: "
                                + thread);
                    }
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (Throwable throwable : uncaughtExceptions) {
            throwable.printStackTrace();
        }
        if (uncaughtExceptions.size() > 0) {
            throw new RuntimeException("Errors in other threads");
        }
    }

    private boolean isSystemThread(Thread thread) {
        return thread.getClass().getPackage().getName()
                .startsWith("org.eclipse");
    }
}
