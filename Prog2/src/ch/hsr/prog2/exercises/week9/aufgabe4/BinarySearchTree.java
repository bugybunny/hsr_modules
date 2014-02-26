/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 18 14:19:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week9.aufgabe4;

import java.util.Collection;

public class BinarySearchTree<K extends Comparable<? super K>, V> {

    protected Node root;

    public static class Entry<K, V> {

        private K key;
        private V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        protected K setKey(K key) {
            K oldKey = this.key;
            this.key = key;
            return oldKey;
        }

        public K getKey() {
            return key;
        }

        public V setValue(V value) {
            V oldValue = this.value;
            this.value = value;
            return oldValue;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            StringBuilder result = new StringBuilder();
            result.append("[").append(key).append("/").append(value)
                    .append("]");
            return result.toString();
        }

    } // End of class Entry

    protected class Node {

        private Entry<K, V> entry;
        private Node        leftChild;
        private Node        rightChild;

        public Node(Entry<K, V> entry) {
            this.entry = entry;
        }

        public Node(Entry<K, V> entry, Node leftChild, Node rightChild) {
            this.entry = entry;
            this.leftChild = leftChild;
            this.rightChild = rightChild;
        }

        public Entry<K, V> getEntry() {
            return entry;
        }

        public Entry<K, V> setEntry(Entry<K, V> entry) {
            Entry<K, V> oldEntry = entry;
            this.entry = entry;
            return oldEntry;
        }

        public Node getLeftChild() {
            return leftChild;
        }

        public void setLeftChild(Node leftChild) {
            this.leftChild = leftChild;
        }

        public Node getRightChild() {
            return rightChild;
        }

        public void setRightChild(Node rightChild) {
            this.rightChild = rightChild;
        }

    } // End of class Node

    public Entry<K, V> insert(K key, V value) {
        // go on the left side
        if (key.compareTo(root.entry.getKey()) < 0) {
        }
        // go on the right side
        if (key.compareTo(root.entry.getKey()) > 0) {

        }
        return null;
    }

    /**
     * Factory-Method: Creates a new node.
     * 
     * @param entry
     *            The entry to be inserted in the new node.
     * @return The new created node.
     */
    protected Node newNode(Entry<K, V> entry) {
        return new Node(entry);
    }

    public void clear() {
        root = null;
    }

    public Entry<K, V> find(K key) {
        Node actNode = root;
        while (actNode.getLeftChild() != null
                && actNode.getRightChild() != null) {
        }
        return null;
    }

    /**
     * Returns a collection with all entries with key.
     * 
     * @param key
     *            The key to be searched.
     * @return Collection of all entries found. An empty collection is returned
     *         if no entry with key is found.
     */
    public Collection<Entry<K, V>> findAll(K key) {
        // TODO Implement here...
        return null;
    }

    /**
     * Returns a collection with all entries in inorder.
     * 
     * @return Inorder-Collection of all entries.
     */
    public Collection<Entry<K, V>> inorder() {
        Node leftChild = root.getLeftChild();
        return null;
    }

    /**
     * Prints the entries of the tree as a list in inorder to the console.
     */
    public void printInorder() {
        // TODO Implement here...
    }

    public Entry<K, V> remove(Entry<K, V> entry) {
        // TODO Implement here...
        return null;
    }

    /**
     * The height of the tree.
     * 
     * @return The actual height. -1 for an empty tree.
     */
    public int getHeight() {
        // TODO Implement here...
        return -1;
    }

    public int size() {
        // TODO Implement here...
        return -1;
    }

    public boolean isEmpty() {
        // TODO Implement here...
        return true;
    }

    public static void main(String[] args) {

        // Example from lecture "L�schen (IV/IV)":
        BinarySearchTree<Integer, String> bts = new BinarySearchTree<Integer, String>();
        System.out.println("Inserting:");
        bts.insert(Integer.valueOf(1), "Str1");
        bts.printInorder();
        bts.insert(Integer.valueOf(3), "Str3");
        bts.printInorder();
        bts.insert(Integer.valueOf(2), "Str2");
        bts.printInorder();
        bts.insert(Integer.valueOf(8), "Str8");
        bts.printInorder();
        bts.insert(Integer.valueOf(9), "Str9");
        bts.insert(Integer.valueOf(6), "Str6");
        bts.insert(Integer.valueOf(5), "Str5");
        bts.printInorder();

        System.out.println("Removeing 3:");
        Entry<Integer, String> entry = bts.find(Integer.valueOf(3));
        System.out.println(entry);
        bts.remove(entry);
        bts.printInorder();

    }

    /*
     * Session-Log:
     * 
     * Inserting: [1/Str1] [1/Str1] [3/Str3] [1/Str1] [2/Str2] [3/Str3] [1/Str1]
     * [2/Str2] [3/Str3] [8/Str8] [1/Str1] [2/Str2] [3/Str3] [5/Str5] [6/Str6]
     * [8/Str8] [9/Str9] Removeing 3: [3/Str3] [1/Str1] [2/Str2] [5/Str3]
     * [6/Str6] [8/Str8] [9/Str9]
     */

} // End of class BinarySearchTree

