package week3.exercise1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class WarehouseWithLockCondition implements Warehouse {
    private int       currentAmount = 0;
    private Lock      monitor;
    private Condition nonFull;
    private Condition nonEmpty;
    private int       capacity;

    public WarehouseWithLockCondition(int aCapacity, boolean isFair) {
        capacity = aCapacity;
        monitor = new ReentrantLock(isFair);
        nonFull = monitor.newCondition();
        nonEmpty = monitor.newCondition();
    }

    @Override
    public void put(int anAmount) throws InterruptedException {
        monitor.lock();
        try {
            while (currentAmount + anAmount > capacity) {
                nonFull.await();
            }
            currentAmount += anAmount;
            nonEmpty.signal();
        }
        finally {
            monitor.unlock();
        }
    }

    @Override
    public void get(int anAmount) throws InterruptedException {
        monitor.lock();
        try {
            while (currentAmount - anAmount < 0) {
                nonEmpty.await();
            }
            currentAmount -= anAmount;
            nonFull.signal();
        }
        finally {
            monitor.unlock();
        }
    }

}
