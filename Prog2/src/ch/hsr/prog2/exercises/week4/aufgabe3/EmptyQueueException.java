/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe3;

/**
 * Runtime exception thrown when one tries to perform operation on an empty
 * queue.
 */

public class EmptyQueueException extends RuntimeException {

    public EmptyQueueException(String err) {
        super(err);
    }
}
