/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe2;

public class DequeImplementation<T> {

    Node<T> header, trailer;
    int     size;

    public DequeImplementation() {
        header = new Node<T>();
        trailer = new Node<T>();
        header.setNext(trailer);
        trailer.setPrev(header);
        size = 0;
    }

    public void insertFirst(T element) {
        Node<T> second = header.getNext();
        Node<T> first = new Node<T>(element, header, second);
        second.setPrev(first);
        header.setNext(first);
        size++;
    }

    public void insertLast(T element) {
        Node<T> oldLast = trailer.getPrev();
        Node<T> newLast = new Node<T>(element, oldLast, trailer);
        newLast.setPrev(oldLast);
        oldLast.setNext(newLast);
        trailer.setPrev(newLast);
        size++;
    }

    public T removeLast() throws DequeEmptyException {
        if (!isEmpty()) {
            Node<T> last = trailer.getPrev();
            T o = last.getElement();
            Node<T> secondtolast = last.getPrev();
            trailer.setPrev(secondtolast);
            secondtolast.setNext(trailer);
            size--;
            return o;
        } else {
            throw new DequeEmptyException("Deque is empty!");
        }
    }

    public T removeFirst() throws DequeEmptyException {
        if (!isEmpty()) {
            Node<T> first = header.getNext();
            T deletedElement = first.getElement();
            Node<T> second = first.getNext();
            header.setNext(second);
            size--;
            return deletedElement;

        } else {
            throw new DequeEmptyException("Deque is empty!");
        }
    }

    public T first() throws DequeEmptyException {
        if (!isEmpty()) {
            return header.getNext().getElement();
        } else {
            throw new DequeEmptyException("Deque is empty!");
        }
    }

    public T last() throws DequeEmptyException {
        if (!isEmpty()) {
            return trailer.getPrev().getElement();
        } else {
            throw new DequeEmptyException("Deque is empty!");
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public static void main(String[] args) {
        DequeImplementation<Integer> deque = new DequeImplementation<Integer>();
        // for (int i = 0; i < 10; i++) {
        // System.out.println("insertFirst(): " + i);
        // deque.insertFirst(Integer.valueOf(i));
        // }
        // for (int i = 10; i < 20; i++) {
        // System.out.println("insertLast(): " + i);
        // deque.insertLast(Integer.valueOf(i));
        // }
        // System.out.println("first(): " + deque.first());
        // System.out.println("last():  " + deque.last());
        // for (int i = 0; i < 5; i++) {
        // deque.removeFirst();
        // }
        // for (int i = 0; i < 5; i++) {
        // deque.removeLast();
        // }
        // System.out.println("first(): " + deque.first());
        // System.out.println("last():  " + deque.last());

        System.out.println("Total memory: "
                + Runtime.getRuntime().totalMemory() + ", free memory: "
                + Runtime.getRuntime().freeMemory());

        for (int i = 0; i < 10_000_000; i++) {
            deque.insertLast(Integer.valueOf(i));
        }

        System.out.println("Total memory: "
                + Runtime.getRuntime().totalMemory() + ", free memory: "
                + Runtime.getRuntime().freeMemory());
    }
}

/*
 * Session-Log (SOLL):
 * 
 * insertFirst(): 0 insertFirst(): 1 insertFirst(): 2 insertFirst(): 3
 * insertFirst(): 4 insertFirst(): 5 insertFirst(): 6 insertFirst(): 7
 * insertFirst(): 8 insertFirst(): 9 insertLast(): 10 insertLast(): 11
 * insertLast(): 12 insertLast(): 13 insertLast(): 14 insertLast(): 15
 * insertLast(): 16 insertLast(): 17 insertLast(): 18 insertLast(): 19 first():
 * 9 last(): 19 first(): 4 last(): 14
 */

