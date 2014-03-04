package week3.exercise1;

public class JavaMonitoredWarehouse implements Warehouse {
    int currentAmount;

    public JavaMonitoredWarehouse() {
    }

    @Override
    public synchronized void put(int anAmount) throws InterruptedException {
        currentAmount += anAmount;
        notify();
    }

    @Override
    public synchronized void get(int anAmount) throws InterruptedException {
        while (currentAmount < anAmount) {
            wait();
        }
        currentAmount -= anAmount;
    }
}
