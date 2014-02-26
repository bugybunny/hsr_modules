/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-14 15:56:23 +0100 (Do, 14 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week5.aufgabe7;

/**
 * @author msuess
 */
public interface TreeInterface<T> {

    T root();

    void setRoot(T root);

    T parent(T child) throws NoSuchNodeException;

    T leftChild(T parent) throws NoSuchNodeException;

    T rightChild(T parent) throws NoSuchNodeException;

    boolean isInternal(T node) throws NoSuchNodeException;

    boolean isExternal(T node) throws NoSuchNodeException;

    boolean isRoot(T node);

    void setRightChild(T parent, T child) throws NoSuchNodeException;

    void setLeftChild(T parent, T child) throws NoSuchNodeException;

    void removeRightChild(T parent) throws NoSuchNodeException;

    void removeLeftChild(T parent) throws NoSuchNodeException;

    int size();

}
