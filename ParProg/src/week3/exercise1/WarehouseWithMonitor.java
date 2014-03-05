package week3.exercise1;

public class WarehouseWithMonitor implements Warehouse {
    private int capacity;
    private int currentAmount;

    public WarehouseWithMonitor(int aCapacity) {
        capacity = aCapacity;
    }

    @Override
    public synchronized void put(int anAmount) throws InterruptedException {
        while (currentAmount + anAmount > capacity) {
            wait();
        }
        currentAmount += anAmount;
        notifyAll();
    }

    @Override
    public synchronized void get(int anAmount) throws InterruptedException {
        while (currentAmount - anAmount < 0) {
            wait();
        }
        currentAmount -= anAmount;
        notifyAll();
    }
}
