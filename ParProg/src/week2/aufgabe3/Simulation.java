package week2.aufgabe3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

class BoundedBuffer<T> {
    private ArrayBlockingQueue<T> buffer;
    private int                   capacity;

    public BoundedBuffer(int aCapacity) {
        buffer = new ArrayBlockingQueue<>(aCapacity);
        capacity = aCapacity;
    }

    public void put(T item) throws InterruptedException {
        buffer.put(item);
    }

    public T get() throws InterruptedException {
        return buffer.take();
    }
}

class Producer extends Thread {
    private final BoundedBuffer<Long> buffer;
    private final int                 nofItems;

    public Producer(BoundedBuffer<Long> buffer, int nofItems) {
        this.buffer = buffer;
        this.nofItems = nofItems;
    }

    @Override
    public void run() {
        Random random = new Random();
        for (int i = 0; i < nofItems; i++) {
            try {
                buffer.put(Long.valueOf(random.nextLong()));
            }
            catch (InterruptedException anEx) {
                anEx.printStackTrace();
            }
        }
        System.out.println("Producer finished " + getName());
    }
}

class Consumer extends Thread {
    private final BoundedBuffer<Long> buffer;
    private final int                 nofItems;

    public Consumer(BoundedBuffer<Long> buffer, int nofItems) {
        this.buffer = buffer;
        this.nofItems = nofItems;
    }

    @Override
    public void run() {
        for (int i = 0; i < nofItems; i++) {
            try {
                buffer.get();
            }
            catch (InterruptedException anEx) {
                anEx.printStackTrace();
            }
        }
        System.out.println("Consumer finished " + getName());
    }
}

public class Simulation {
    private static final int NOF_PRODUCERS         = 1;
    private static final int NOF_CONSUMERS         = 1;
    private static final int BUFFER_CAPACITY       = 1;
    // TotalElements must be a multiple of ElementsPerProducer and
    // ElementsPerConsumer
    private static final int TOTAL_ELEMENTS        = 1_000_000;
    private static final int ELEMENTS_PER_PRODUCER = TOTAL_ELEMENTS
                                                           / NOF_PRODUCERS;
    private static final int ELEMENTS_PER_CONSUMER = TOTAL_ELEMENTS
                                                           / NOF_CONSUMERS;

    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<Thread>();
        BoundedBuffer<Long> buffer = new BoundedBuffer<Long>(BUFFER_CAPACITY);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < NOF_PRODUCERS; i++) {
            threads.add(new Producer(buffer, ELEMENTS_PER_PRODUCER));
        }
        for (int i = 0; i < NOF_CONSUMERS; i++) {
            threads.add(new Consumer(buffer, ELEMENTS_PER_CONSUMER));
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        long stopTime = System.currentTimeMillis();
        System.out.println("Producer-consumer simulation finished");
        System.out.println("Total time: " + (stopTime - startTime) + " ms");
    }
}
