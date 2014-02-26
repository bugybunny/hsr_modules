/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe3;

import java.lang.reflect.Array;
import java.util.Arrays;

public class QueueImpl<T> implements Queue<T> {

    private T[]     array;
    private int     first;
    private int     last;
    private int     size;
    Class<T>        dataType;
    int             emptySize = 0;

    /**
     * If tracing, than enlarging or reducing of the capacity shall be traced to
     * the console.
     */
    private boolean tracing   = true;

    @SuppressWarnings("unchecked")
    public QueueImpl(Class<T> dataType) {
        this.dataType = dataType;
        array = (T[]) Array.newInstance(dataType, 2);
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void ensureCapacity() {
        if (array.length >= size) {
        }
    }

    @Override
    public T front() throws EmptyQueueException {
        if (!isEmpty()) {
            return array[first];
        } else {
            throw new EmptyQueueException("Array is empty!");
        }
    }

    @Override
    public void enqueue(T element) {
        ensureCapacity();
        array[last + 1] = element;
        last++;
    }

    @Override
    public T dequeue() throws EmptyQueueException {
        T deletedElement = array[first];
        array[first] = null;
        first++;
        emptySize++;
        return deletedElement;
    }

    @SuppressWarnings("unchecked")
    private void enlargeArray() {
        int newSize = (array.length * 3 / 2) + 1;
        array = Arrays.copyOf(array, newSize);
        if (tracing) {
            System.out.println("array incremented by " + size / 2
                    + ", size is now " + newSize);
        }
    }

    @SuppressWarnings("unchecked")
    private void reduceArray() {
        // TODO Implement here...
    }

    @Override
    public void print() {
        if (size == 0) {
            System.out.println("The queue is empty.");
        } else {
            for (int i = 0; i < size; i++) {
                System.out.print(array[i]);
            }
        }
    }

    public void tracing(boolean newValue) {
        tracing = newValue;
    }

    public static void main(String[] args) {
        QueueImpl<Integer> queue = new QueueImpl<Integer>(Integer.class);
        for (int i = 0; i < 20; i++) {
            System.out.println("enqueue(): " + i);
            queue.enqueue(Integer.valueOf(i));
        }
        System.out.println("front(): " + queue.front());
        while (!queue.isEmpty()) {
            System.out.println("dequeue(): " + queue.dequeue());
        }
    }
}

/*
 * Session-Log (SOLL):
 * 
 * enqueue(): 0 enqueue(): 1 enqueue(): 2 Enlarging array from 2 to 4 enqueue():
 * 3 enqueue(): 4 Enlarging array from 4 to 8 enqueue(): 5 enqueue(): 6
 * enqueue(): 7 enqueue(): 8 Enlarging array from 8 to 16 enqueue(): 9
 * enqueue(): 10 enqueue(): 11 enqueue(): 12 enqueue(): 13 enqueue(): 14
 * enqueue(): 15 enqueue(): 16 Enlarging array from 16 to 32 enqueue(): 17
 * enqueue(): 18 enqueue(): 19 front(): 0 dequeue(): 0 dequeue(): 1 dequeue(): 2
 * Reducing array from 32 to 16 dequeue(): 3 dequeue(): 4 dequeue(): 5
 * dequeue(): 6 dequeue(): 7 dequeue(): 8 dequeue(): 9 dequeue(): 10 Reducing
 * array from 16 to 8 dequeue(): 11 dequeue(): 12 dequeue(): 13 dequeue(): 14
 * Reducing array from 8 to 4 dequeue(): 15 dequeue(): 16 Reducing array from 4
 * to 2 dequeue(): 17 dequeue(): 18 dequeue(): 19
 */

