/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Wed Apr 24 15:24:29 CEST 2013
 */

package ch.hsr.prog2.exercises.week10.aufgabe3;

import gvs.tree.GVSBinaryTreeNode;
import gvs.tree.GVSTreeWithRoot;
import gvs.typ.node.GVSNodeTyp;

class AVLTreeImplGVS<K extends Comparable<? super K>, V> extends
        AVLTreeImpl<K, V> {

    protected GVSTreeWithRoot gvsTree;

    private int               DELAY = 200;

    protected class AVLNodeGVS extends AVLTreeImpl<K, V>.AVLNode implements
            GVSBinaryTreeNode {

        protected AVLNodeGVS(Entry<K, V> entry) {
            super(entry);
        }

        @Override
        public GVSBinaryTreeNode getGVSLeftChild() {
            return (GVSBinaryTreeNode) getLeftChild();
        }

        @Override
        public GVSBinaryTreeNode getGVSRightChild() {
            return (GVSBinaryTreeNode) getRightChild();
        }

        @Override
        public String getNodeLabel() {
            Entry<K, V> e = getEntry();
            return e.getKey() + " " + e.getValue();
            // return e.getKey().toString();
        }

        @Override
        public GVSNodeTyp getNodeTyp() {
            return null;
        }

    } // class BinaryTreeTestGVS.NodeGVS

    AVLTreeImplGVS() {
        this("AVLTreeGVS");
    }

    AVLTreeImplGVS(String title) {
        gvsTree = new GVSTreeWithRoot(title);
    }

    @Override
    protected Node newNode(Entry<K, V> entry) {
        return new AVLNodeGVS(entry);
    }

    @Override
    public V put(K key, V value) {
        V result = super.put(key, value);
        gvsTree.setRoot((GVSBinaryTreeNode) root);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        return result;
    }

    @Override
    public Entry<K, V> insert(K key, V value) {
        Entry<K, V> newEntry = super.insert(key, value);
        gvsTree.setRoot((GVSBinaryTreeNode) root);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        return newEntry;
    }

    @Override
    protected AVLNode rotateWithRightChild(AVLNode k1) {
        gvsTree.setRoot((GVSBinaryTreeNode) root);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        AVLNode newRoot = super.rotateWithRightChild(k1);
        return newRoot;
    }

    @Override
    protected AVLNode rotateWithLeftChild(AVLNode k2) {
        gvsTree.setRoot((GVSBinaryTreeNode) root);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        AVLNode newRoot = super.rotateWithLeftChild(k2);
        return newRoot;
    }

    @Override
    public Entry<K, V> remove(Entry<K, V> entry) {
        Entry<K, V> deletedEntry = super.remove(entry);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        return deletedEntry;
    }

    @Override
    public V remove(K key) {
        V result = super.remove(key);
        gvsTree.display();
        try {
            Thread.sleep(DELAY);
        }
        catch (InterruptedException e) {
        }
        return result;
    }

}
