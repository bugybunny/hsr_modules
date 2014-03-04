package week2.exercise4;

public class Room {
    private int currentCount = 0;
    private int capacity;

    public Room(int aCapacity) {
        capacity = aCapacity;
    }

    public synchronized void enter() throws InterruptedException {
        while (currentCount >= capacity) {
            wait();
        }
        currentCount++;
    }

    public void exit() {
        currentCount--;
        notify();
    }

}
