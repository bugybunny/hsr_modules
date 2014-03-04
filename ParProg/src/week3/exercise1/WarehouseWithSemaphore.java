package week3.exercise1;

import java.util.concurrent.Semaphore;

public class WarehouseWithSemaphore implements Warehouse {
    private Semaphore upperLimit;
    private Semaphore lowerLimit;
    private Semaphore mutex;
    private int       currentAmount;

    public int getCurrentAmount() {
        return currentAmount;
    }

    public WarehouseWithSemaphore(int aCapacity, boolean isFair) {
        upperLimit = new Semaphore(aCapacity, isFair);
        lowerLimit = new Semaphore(0, isFair);
        mutex = new Semaphore(1, isFair);
        currentAmount = 0;
    }

    @Override
    public void put(int anAmount) throws InterruptedException {
        upperLimit.acquire(anAmount);
        mutex.acquire();
        currentAmount += anAmount;
        mutex.release();
        lowerLimit.release(anAmount);
    }

    @Override
    public void get(int anAmount) throws InterruptedException {
        lowerLimit.acquire(anAmount);
        mutex.acquire();
        currentAmount -= anAmount;
        mutex.release();
        upperLimit.release(anAmount);
    }
}
