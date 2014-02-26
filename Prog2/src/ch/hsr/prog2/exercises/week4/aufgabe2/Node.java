/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe2;

/**
 * Template exercise 4 Double linked list and DE-Queue (double ended queue)
 */
public class Node<T> {

    private T element;
    private Node<T> next, prev;

    Node() {
        this(null, null, null);
    }

    Node(T e, Node<T> p, Node<T> n) {
        element = e;
        next = n;
        prev = p;
    }

    void setElement(T newElem) {
        element = newElem;
    }

    void setNext(Node<T> newNext) {
        next = newNext;
    }

    void setPrev(Node<T> newPrev) {
        prev = newPrev;
    }

    T getElement() {
        return element;
    }

    Node<T> getNext() {
        return next;
    }

    Node<T> getPrev() {
        return prev;
    }
}
