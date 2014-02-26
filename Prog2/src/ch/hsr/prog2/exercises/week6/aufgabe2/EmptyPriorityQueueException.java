/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

/**
 * Runtime exception thrown when one tries to perform operation on an empty
 * priority queue.
 */

public class EmptyPriorityQueueException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public EmptyPriorityQueueException(String err) {
    super(err);
  }
}
 
 
 
 
