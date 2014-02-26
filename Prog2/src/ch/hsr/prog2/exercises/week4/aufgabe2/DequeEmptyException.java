/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe2;

/**
 * Runtime exception thrown when one tries to perform an operation on an empty
 * queue.
 */

public class DequeEmptyException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DequeEmptyException(String err) {
        super(err);
    }
}
