/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

import java.util.LinkedList;

public class PriorityQueue<K extends Comparable<? super K>, V> implements
        PriorityQueueInterface<K, V> {

    private LinkedList<Entry<K, V>> list;

    public PriorityQueue() {
        list = new LinkedList<>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public Entry<K, V> min() throws EmptyPriorityQueueException {
        if (!isEmpty()) {
            return list.getFirst();
        } else {
            throw new EmptyPriorityQueueException("queue is empty!");
        }
    }

    @Override
    public void insert(K key, V value) {
        for (int i = 0; i < list.size(); i++) {
            int compareResult = list.get(i).getKey().compareTo(key);
            switch (compareResult) {
                case 0:
                    list.set(i, new Entry<K, V>(key, value));
                    break;
            }
        }
        // for (Entry<K, V> tempEntry : list) {
        // if (key.compareTo(tempEntry.getKey()) < 0) {
        // tempEntry.
        // }
        // }
    }

    @Override
    public Entry<K, V> removeMin() throws EmptyPriorityQueueException {
        Entry<K, V> removed = list.getFirst();
        list.removeFirst();
        return removed;
    }

    @Override
    public void print() {
        for (Entry<K, V> tempEntry : list) {
            System.out
                    .println(tempEntry.getKey() + ": " + tempEntry.getValue());
        }
    }

    public static void main(String[] args) {
        PriorityQueue<Integer, String> pq = new PriorityQueue<Integer, String>();
        pq.insert(Integer.valueOf(8), "eight");
        pq.insert(Integer.valueOf(2), "two");
        pq.insert(Integer.valueOf(5), "five");
        pq.insert(Integer.valueOf(1), "one");
        pq.insert(Integer.valueOf(2), "two");
        pq.insert(Integer.valueOf(1), "one");
        pq.insert(Integer.valueOf(4), "four");
        pq.insert(Integer.valueOf(4), "four");
        pq.insert(Integer.valueOf(7), "seven");
        pq.insert(Integer.valueOf(2), "two");
        pq.insert(Integer.valueOf(6), "six");
        pq.insert(Integer.valueOf(3), "three");
        pq.insert(Integer.valueOf(1), "one");

        pq.print();
    }
}

/*
 * Session-Log (SOLL):
 * 
 * Print priority queue entries: Element 1 with key '1' has the value 'one'
 * Element 2 with key '1' has the value 'one' Element 3 with key '1' has the
 * value 'one' Element 4 with key '2' has the value 'two' Element 5 with key '2'
 * has the value 'two' Element 6 with key '2' has the value 'two' Element 7 with
 * key '3' has the value 'three' Element 8 with key '4' has the value 'four'
 * Element 9 with key '4' has the value 'four' Element 10 with key '5' has the
 * value 'five' Element 11 with key '6' has the value 'six' Element 12 with key
 * '7' has the value 'seven' Element 13 with key '8' has the value 'eight'
 */

