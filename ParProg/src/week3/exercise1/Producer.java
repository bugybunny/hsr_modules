package week3.exercise1;

import java.util.Random;

class Producer extends Thread {
    private final Warehouse warehouse;
    private final int       nofItems;
    private final int       maxPerCall;

    public Producer(final Warehouse warehouse, int nofItems, int maxPerCall) {
        this.warehouse = warehouse;
        this.nofItems = nofItems;
        this.maxPerCall = maxPerCall;
    }

    @Override
    public void run() {
        try {
            Random random = new Random(4711);
            int countAllItems = 0;
            while (countAllItems < nofItems) {
                int countPerCall = random.nextInt(maxPerCall) + 1;
                if (countAllItems + countPerCall > nofItems) {
                    countPerCall = nofItems - countAllItems;
                }
                warehouse.put(countPerCall);
                countAllItems += countPerCall;
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
