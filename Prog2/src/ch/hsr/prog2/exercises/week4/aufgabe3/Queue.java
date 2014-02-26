/*

 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe3;

public interface Queue<T> {

    /**
     * Returns the number of elements in the queue.
     * 
     * @return Number of elements in the queue.
     */
    public int size();

    /**
     * Returns whether the queue is empty.
     * 
     * @return True if the queue is empty, false otherwise.
     */
    public boolean isEmpty();

    /**
     * Inspects the element at the front of the queue.
     * 
     * @return Element at the front of the queue.
     * @exception EmptyQueueException
     *                If the queue is empty.
     */
    public T front() throws EmptyQueueException;

    /**
     * Inserts an element at the rear of the queue.
     * 
     * @param element
     *            New element to be inserted.
     */
    public void enqueue(T element);

    /**
     * Removes the element at the front of the queue.
     * 
     * @return Element removed.
     * @exception EmptyQueueException
     *                If the queue is empty.
     */
    public T dequeue() throws EmptyQueueException;

    /**
     * Prints the contents of the queue to the console.
     */
    public void print();

}
