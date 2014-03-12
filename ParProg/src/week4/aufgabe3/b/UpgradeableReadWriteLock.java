package week4.aufgabe3.b;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UpgradeableReadWriteLock {
    private ReentrantReadWriteLock lock;
    private Semaphore              upgrader;

    private Thread                 upgradeableThread;

    public UpgradeableReadWriteLock() {
        this(false);
    }

    public UpgradeableReadWriteLock(boolean isFair) {
        lock = new ReentrantReadWriteLock(isFair);
        upgrader = new Semaphore(1, isFair);
    }

    public void readLock() throws InterruptedException {
        lock.readLock().lock();
    }

    public void readUnlock() {
        lock.readLock().unlock();
    }

    public void upgradeableReadLock() throws InterruptedException {
        upgradeableThread = Thread.currentThread();
        readLock();
        upgrader.acquire();
    }

    public void upgradeableReadUnlock() {
        upgrader.release();
        readUnlock();
    }

    public void writeLock() throws InterruptedException {
        if (upgrader.availablePermits() == 0
                && upgradeableThread == Thread.currentThread()) {
            lock.readLock().unlock();
        }
        lock.writeLock().lock();
    }

    public void writeUnlock() {
        lock.writeLock().unlock();
        if (upgrader.availablePermits() == 0) {
            lock.readLock().lock();
            upgrader.release();
        }
    }
}
