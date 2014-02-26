/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Wed Apr 24 15:24:29 CEST 2013
 */

package ch.hsr.prog2.exercises.week10.aufgabe3;

import java.util.Collection;

import ch.hsr.prog2.exercises.week9.aufgabe4.BinarySearchTree.Entry;

public class AVLTree<K extends Comparable<? super K>, V> {

    private AVLTreeImpl<K, V> avlTreeImpl = new AVLTreeImpl<K, V>();

    // Start the GVS-Server first: Double-Click 'GVS_Server_v1.4.jar'
    // private AVLTreeImpl<K, V> avlTreeImpl = new AVLTreeImplGVS<K, V>();

    public V put(K key, V value) {
        return avlTreeImpl.put(key, value);
    }

    public V get(K key) {
        return avlTreeImpl.get(key);
    }

    public V remove(K key) {
        return avlTreeImpl.remove(key);
    }

    public int getHeight() {
        return avlTreeImpl.getHeight();
    }

    public int size() {
        return avlTreeImpl.size();
    }

    public boolean isEmpty() {
        return avlTreeImpl.isEmpty();
    }

    public void clear() {
        avlTreeImpl.clear();
    }

    public Collection<Entry<K, V>> inorder() {
        return avlTreeImpl.inorder();
    }

    public void printInorder() {
        avlTreeImpl.printInorder();
    }

    public void print() {
        avlTreeImpl.print();
    }

    protected AVLTreeImpl<K, V> getImpl() {
        return avlTreeImpl;
    }

    public static void main(String[] args) {

        AVLTree<Integer, String> avlTree = new AVLTree<Integer, String>();

        System.out.println("Inserting 5:");
        avlTree.put(5, "Str_5");
        avlTree.print();
        System.out.println("================================");
        System.out.println("Inserting 7:");
        avlTree.put(7, "Str_7");
        avlTree.print();
        System.out.println("================================");
        System.out.println("Inserting 9: Single-Rotation");
        avlTree.put(9, "Str_9");
        avlTree.print();
        System.out.println("================================");
        System.out.println("Inserting 3:");
        avlTree.put(3, "Str_3");
        avlTree.print();
        System.out.println("================================");
        System.out.println("Inserting 1: Single-Rotation");
        avlTree.put(1, "Str_1");
        avlTree.print();
        System.out.println("================================");
        System.out.println("Inserting 4: Double-Rotation");
        avlTree.put(4, "Str_4");
        avlTree.print();
        System.out.println("================================");

    }

}

/*
 * Session-Log:
 * 
 * Inserting 5: 5 / Str_5 | h=0 ROOT ================================ Inserting
 * 7: 5 / Str_5 | h=1 ROOT 7 / Str_7 | h=0 \ parent(key)=5
 * ================================ Inserting 9: Single-Rotation 5 / Str_5 | h=0
 * / parent(key)=7 7 / Str_7 | h=1 ROOT 9 / Str_9 | h=0 \ parent(key)=7
 * ================================ Inserting 3: 3 / Str_3 | h=0 / parent(key)=5
 * 5 / Str_5 | h=1 / parent(key)=7 7 / Str_7 | h=2 ROOT 9 / Str_9 | h=0 \
 * parent(key)=7 ================================ Inserting 1: Single-Rotation 1
 * / Str_1 | h=0 / parent(key)=3 3 / Str_3 | h=1 / parent(key)=7 5 / Str_5 | h=0
 * \ parent(key)=3 7 / Str_7 | h=2 ROOT 9 / Str_9 | h=0 \ parent(key)=7
 * ================================ Inserting 4: Double-Rotation 1 / Str_1 | h=0
 * / parent(key)=3 3 / Str_3 | h=1 / parent(key)=5 4 / Str_4 | h=0 \
 * parent(key)=3 5 / Str_5 | h=2 ROOT 7 / Str_7 | h=1 \ parent(key)=5 9 / Str_9
 * | h=0 \ parent(key)=7 ================================
 */

