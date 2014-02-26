/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:29:41 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe2;

public class Entry<K extends Comparable<? super K>, V> {

    private K key;

    private V value;

    public Entry() {
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public K getKey() {
        return key;
    }

    public K setKey(K key) {
        K oldKey = this.key;
        this.key = key;
        return oldKey;
    }

    public V getValue() {
        return value;
    }

    public V setValue(V value) {
        V oldValue = this.value;
        this.value = value;
        return oldValue;
    }

    @Override
    public String toString() {
        String str = key.toString();
        if (value != null) {
            str += ": " + value.toString();
        }
        return str;
    }
}
