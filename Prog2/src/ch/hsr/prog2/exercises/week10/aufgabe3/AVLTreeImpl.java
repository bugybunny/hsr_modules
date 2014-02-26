/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Wed Apr 24 15:24:29 CEST 2013
 */

package ch.hsr.prog2.exercises.week10.aufgabe3;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import ch.hsr.prog2.exercises.week9.aufgabe4.BinarySearchTree;

class AVLTreeImpl<K extends Comparable<? super K>, V> extends
        BinarySearchTree<K, V> {

    /**
     * After a BST-operation, actionNode shall point to where the balance has to
     * be checked. -> rebalance() will then be called with actionNode.
     */
    protected AVLNode actionNode;

    protected class AVLNode extends BinarySearchTree<K, V>.Node {

        private int  height;
        private Node parent;

        AVLNode(Entry<K, V> entry) {
            super(entry);
        }

        protected AVLNode setParent(AVLNode parent) {
            AVLNode old = (AVLNode) this.parent;
            this.parent = parent;
            return old;
        }

        protected AVLNode getParent() {
            return (AVLNode) parent;
        }

        protected int setHeight(int height) {
            int old = this.height;
            this.height = height;
            return old;
        }

        protected int getHeight() {
            return height;
        }

        @Override
        public AVLNode getLeftChild() {
            return (AVLNode) super.getLeftChild();
        }

        @Override
        public AVLNode getRightChild() {
            return (AVLNode) super.getRightChild();
        }

        @Override
        public String toString() {
            String result = String.format("%2d", getEntry().getKey()) + " / "
                    + getEntry().getValue() + " | h=" + height;
            if (parent == null) {
                result += " ROOT";
            } else {
                boolean left = (parent.getLeftChild() == this) ? true : false;
                result += (left ? " / " : " \\ ") + "parent(key)="
                        + parent.getEntry().getKey();
            }
            return result;
        }

    } // End of class AVLNode

    protected AVLNode getRoot() {
        return (AVLNode) root;
    }

    public V put(K key, V value) {
        super.insert(key, value);
        AVLNode zPos = actionNode; // start at the insertion position
        // TODO replace zpos with new AVLNode(key,value,1)
        rebalance(zPos);

        return null;
    }

    public V get(K key) {
        // TODO Implement here...
        return null;
    }

    /**
     * Assures the balance of the tree from 'node' up to the root.
     * 
     * @param node
     *            The node from where to start.
     */
    protected void rebalance(AVLNode node) {
        while (!node.equals(getRoot())) {
            node = node.getParent();
            setHeight(node);
            if (!isBalanced(node)) { // perform a rotation
                AVLNode xPos = tallerChild(tallerChild(node));
                node = restructure(xPos);
                setHeight(node.getLeftChild());
                setHeight(node.getRightChild());
                setHeight(node);
            }
        }
    }

    /**
     * Returns the height of this node.
     * 
     * @param node
     * @return The height or -1 if null.
     */
    protected int height(AVLNode node) {
        return (node != null) ? node.getHeight() : -1;
    }

    /**
     * Restructures the tree with rotations.
     * 
     * @param xPos
     *            The X-node.
     * @return The new root-node of this subtree.
     */
    protected AVLNode restructure(AVLNode xPos) {
        Object[] cutLinkArray = new Object[8];

        AVLNode yPos = xPos.getParent();
        AVLNode zPos = yPos.getParent();

        // if (zPos.getRightChild().equals()) {
        return null;
        // }
    }

    /**
     * Returns the child with the bigger height.
     */
    protected AVLNode tallerChild(AVLNode node) {
        // return a child of p with height no
        // smaller than that of the other child
        if (height(node.getLeftChild()) >= height(node.getRightChild())) {
            return node.getLeftChild();
        } else {
            return node.getRightChild();
        }
    }

    protected AVLNode rotateWithLeftChild(AVLNode k2) {
        AVLNode k1 = k2.getLeftChild();
        k2.setLeftChild(k1.getRightChild());
        k1.setRightChild(k2);
        if (k2.getLeftChild() != null) {
            k2.getLeftChild().setParent(k2);
        }
        adjustParents(k2, k1);

        return k1;
    }

    protected AVLNode doubleRotateWithLeftChild(AVLNode k3) {
        k3.setLeftChild(rotateWithRightChild(k3.getLeftChild()));
        return rotateWithLeftChild(k3);
    }

    protected AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.getRightChild();
        k1.setRightChild(k2.getLeftChild());
        k2.setLeftChild(k1);
        if (k1.getRightChild() != null) {
            k1.getRightChild().setParent(k1);
        }
        adjustParents(k1, k2);

        return k2;
    }

    protected AVLNode doubleRotateWithRightChild(AVLNode k3) {
        k3.setRightChild(rotateWithLeftChild(k3.getRightChild()));
        return rotateWithRightChild(k3);
    }

    /**
     * Assures that the childrens have theirs correct parents. Used after
     * rotations.
     * 
     * @param oldSubtreeRoot
     *            The old root-node of this subtree.
     * @param newSubtreeRoot
     *            The new root-node of this subtree.
     */
    protected void adjustParents(final AVLNode oldSubtreeRoot,
            final AVLNode newSubtreeRoot) {
        newSubtreeRoot.setParent(oldSubtreeRoot.getParent());
        oldSubtreeRoot.setParent(newSubtreeRoot);

    }

    protected boolean isBalanced(AVLNode node) {
        // TODO Implement here...
        return false;
    }

    /**
     * Assures the correct height for node.
     * 
     * @param node
     */
    protected void setHeight(AVLNode node) {
        // TODO Implement here...
    }

    /**
     * Factory-Method. Creates a new node.
     * 
     * @param entry
     *            The entry to be inserted in the new node.
     * @return The new created node.
     */
    @Override
    protected Node newNode(Entry<K, V> entry) {
        AVLNode avlNode = new AVLNode(entry);
        return avlNode;
    }

    public V remove(K key) {
        // TODO Implement here...
        return null;
    }

    /**
     * Generates an inorder-node-list.
     * 
     * @param nodeList
     *            The node-list to fill in inorder.
     * @param node
     *            The node to start from.
     */
    protected void inorder(Collection<AVLNode> nodeList, AVLNode node) {
        if (node == null) {
            return;
        }
        inorder(nodeList, node.getLeftChild());
        nodeList.add(node);
        inorder(nodeList, node.getRightChild());
    }

    public void print() {
        List<AVLNode> nodeList = new LinkedList<AVLNode>();
        inorder(nodeList, (AVLNode) root);
        for (AVLNode node : nodeList) {
            System.out.println(node + "  ");
        }
    }

}
