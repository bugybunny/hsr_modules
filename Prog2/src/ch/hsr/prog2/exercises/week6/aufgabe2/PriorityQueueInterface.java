/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

/**
 * @author msuess
 */
public interface PriorityQueueInterface<K extends Comparable<? super K>, V> {

  int size();

  boolean isEmpty();

  Entry<K, V> min() throws EmptyPriorityQueueException;

  void insert(K key, V value);

  Entry<K, V> removeMin() throws EmptyPriorityQueueException;

  void print();
}
 
 
 
 
