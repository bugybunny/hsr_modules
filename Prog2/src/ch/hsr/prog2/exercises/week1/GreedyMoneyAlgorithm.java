package ch.hsr.prog2.exercises.week1;

import java.util.Collections;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class GreedyMoneyAlgorithm {

    /**
     * Creates a new instance of this class.
     */
    public GreedyMoneyAlgorithm() {
    }

    public static void main(String[] args) {
        int amount = 999;
        SortedMap<Integer, AtomicInteger> availableMoneyValues = new TreeMap<>(
                Collections.reverseOrder());

        availableMoneyValues.put(Integer.valueOf(1), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(2), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(5), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(10), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(20), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(50), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(100), new AtomicInteger(0));
        availableMoneyValues.put(Integer.valueOf(200), new AtomicInteger(0));

        int restAmount = amount;
    }

}
