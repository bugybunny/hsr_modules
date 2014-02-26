/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2010-02-16 15:22:23 +0100 (Di, 16 Feb 2010) $
 */

package ch.hsr.prog2.exercises.week5.aufgabe7;

import java.util.ArrayList;

public class VectorTree<T> implements TreeInterface<T> {

    private ArrayList<T> binaryTree;
    private int          size = 0;

    public VectorTree() {
        binaryTree = new ArrayList<T>(10);
        binaryTree.add(0, null);
    }

    @Override
    public T root() {
        if (isValidIndex(1)) {
            return binaryTree.get(1); // return still null if root is null
        } else {
            return null;
        }
    }

    @Override
    /**
     * Sets a new root and removes all other nodes.
     */
    public void setRoot(T root) {
        if (isValidIndex(1)) {
            binaryTree.set(1, root);
        } else {
            binaryTree.add(1, root);
        }

        for (int i = 2; i < binaryTree.size(); i++) {
            binaryTree.set(i, null);
        }
        size = 1;
    }

    @Override
    public T parent(T child) throws NoSuchNodeException {
        T parentNode = null;

        int childIndex = binaryTree.indexOf(child);
        if (childIndex > 1) { // 1 would be root with no parent
            int parentIndex = 0;
            if (childIndex % 2 == 0) {
                parentIndex = childIndex / 2;
            } else {
                parentIndex = (childIndex - 1) / 2;
            }
            parentNode = binaryTree.get(parentIndex);
        }

        return parentNode;
    }

    @Override
    public T leftChild(T parent) throws NoSuchNodeException {
        T leftChild = null;

        int parentIndex = binaryTree.indexOf(parent);
        if (binaryTree.get(parentIndex) == null) {
            throw new NoSuchNodeException();
        }

        int leftChildIndex = parentIndex * 2;
        if (isValidIndex(leftChildIndex)) {
            leftChild = binaryTree.get(leftChildIndex);
        }

        return leftChild;
    }

    @Override
    public T rightChild(T parent) throws NoSuchNodeException {
        T rightChild = null;

        int parentIndex = binaryTree.indexOf(parent);
        if (binaryTree.get(parentIndex) == null) {
            throw new NoSuchNodeException();
        }

        int rightChildIndex = parentIndex * 2 + 1;
        if (isValidIndex(rightChildIndex)) {
            rightChild = binaryTree.get(rightChildIndex);
            if (rightChild == null) {
                throw new NoSuchNodeException();
            }
        }

        return rightChild;
    }

    @Override
    public boolean isInternal(T node) throws NoSuchNodeException {
        return !isExternal(node);
    }

    @Override
    public boolean isExternal(T node) throws NoSuchNodeException {
        if (leftChild(node) == null && rightChild(node) == null) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isRoot(T node) {
        if (size > 0) {
            return binaryTree.get(1).equals(node);
        } else {
            return false;
        }
    }

    @Override
    public void setRightChild(T parent, T child) throws NoSuchNodeException {
        int parentIndex = binaryTree.indexOf(parent);
        if (parentIndex > 0) {
            int rightChildIndex = parentIndex * 2 + 1;
            // index already exists
            if (!isValidIndex(rightChildIndex)) {
                fillTilIndex(rightChildIndex);
            }
            if (binaryTree.set(rightChildIndex, child) == null) {
                size++;
            }

        } else {
            throw new NoSuchNodeException();
        }
    }

    @Override
    public void setLeftChild(T parent, T child) throws NoSuchNodeException {
        int parentIndex = binaryTree.indexOf(parent);
        if (parentIndex > 0) {
            int leftChildIndex = parentIndex * 2;
            // index already exists
            if (!isValidIndex(leftChildIndex)) {
                fillTilIndex(leftChildIndex);
            }
            if (binaryTree.set(leftChildIndex, child) == null) {
                size++;
            }

        } else {
            throw new NoSuchNodeException();
        }
    }

    @Override
    public void removeRightChild(T parent) throws NoSuchNodeException {
        try {
            T rightChild = rightChild(parent);
            removeRightChild(rightChild(rightChild));
            removeLeftChild(leftChild(rightChild));
            rightChild = null;
            size--;
        }
        catch (NoSuchNodeException anEx) {
        }
    }

    @Override
    public void removeLeftChild(T parent) throws NoSuchNodeException {
        try {
            T leftChild = leftChild(parent);
            removeRightChild(rightChild(leftChild));
            removeLeftChild(leftChild(leftChild));
            leftChild = null;
            size--;
        }
        catch (NoSuchNodeException anEx) {
        }
    }

    @Override
    public int size() {
        return size;
    }

    public void printVector() {
        System.out.println(binaryTree);
    }

    private boolean isValidIndex(int anIndex) {
        try {
            binaryTree.get(anIndex);
        }
        catch (IndexOutOfBoundsException anEx) {
            return false;
        }

        return true;
    }

    private void fillTilIndex(int anIndex) {
        while (binaryTree.size() <= anIndex) {
            binaryTree.add(null);
        }
    }

    public static void main(String[] args) throws NoSuchNodeException {

        ;// Hinweis:
         // Beispiel ist aus Folien-Skript
         // "Speicherverfahren f�r B�ume: 2) Array basiert"

        VectorTree<Character> vt = new VectorTree<Character>();
        if (vt.size() != 0) {
            throw new Error("Bad size: " + vt.size() + " != 0");
        }
        if (vt.root() != null) {
            throw new Error("vt.root() != null");
        }

        System.out.println("\nSetting root with 'A':");
        Character a = Character.valueOf('A');
        vt.setRoot(a);
        vt.printVector();
        if (vt.size() != 1) {
            throw new Error("Bad size: " + vt.size() + " != 1");
        }
        if (!vt.isRoot(a)) {
            throw new Error("!vt.root(a)");
        }
        if (!vt.root().equals(a)) {
            throw new Error("!vt.root().equals(a) : " + vt.root());
        }
        if (!vt.isExternal(a)) {
            throw new Error("!vt.isExternal(a)");
        }
        if (vt.parent(a) != null) {
            throw new Error("vt.parent(a) != null");
        }

        System.out.println("\nSetting right child of 'A' with 'D':");
        Character d = Character.valueOf('D');
        vt.setRightChild(vt.root(), d);
        vt.printVector();
        if (vt.size() != 2) {
            throw new Error("Bad size: " + vt.size() + " != 2");
        }
        if (!vt.rightChild(vt.root()).equals(d)) {
            throw new Error("!vt.rightChild(vt.root()).equals(d) : "
                    + vt.rightChild(vt.root()));
        }
        if (!vt.isExternal(d)) {
            throw new Error("!vt.isExternal(d)");
        }
        if (!vt.isInternal(vt.root())) {
            throw new Error("!vt.isInternal(vt.root()");
        }
        if (!vt.parent(d).equals(a)) {
            throw new Error("!vt.parent(d).equals(a)");
        }

        System.out.println("\nSetting left child of 'A' with 'B':");
        Character b = Character.valueOf('B');
        vt.setLeftChild(vt.root(), b);
        vt.printVector();
        if (vt.size() != 3) {
            throw new Error("Bad size: " + vt.size() + " != 3");
        }

        System.out.println("\nSetting right child of 'B' with 'F':");
        Character f = Character.valueOf('F');
        vt.setRightChild(b, f);
        vt.printVector();

        System.out.println("\nSetting right child of 'F' with 'H':");
        Character h = Character.valueOf('H');
        vt.setRightChild(f, h);
        vt.printVector();

        System.out.println("\nSetting left child of 'F' with 'G':");
        Character g = Character.valueOf('G');
        vt.setLeftChild(f, g);
        vt.printVector();
        if (vt.size() != 6) {
            throw new Error("Bad size: " + vt.size() + " != 6");
        }
        if (!vt.isInternal(f)) {
            throw new Error("!vt.isInternal(f)");
        }
        if (!vt.isExternal(h)) {
            throw new Error("!vt.isExternal(h)");
        }
        if (!vt.rightChild(vt.rightChild(vt.leftChild(vt.root()))).equals(h)) {
            throw new Error(
                    "!vt.rightChild(vt.rightChild(vt.leftChild(vt.root()))).equals(h)");
        }

        vt.removeLeftChild(b);
        if (vt.size() != 6) {
            throw new Error("Bad size: " + vt.size() + " != 6");
        }

        System.out.println("\nRemoving right child of 'B':");
        vt.removeRightChild(b);
        vt.printVector();
        if (vt.size() != 3) {
            throw new Error("Bad size: " + vt.size() + " != 3");
        }
        if (!vt.isExternal(b)) {
            throw new Error("!vt.isExternal(b)");
        }

        System.out.println("\nSetting right child of 'D' with 'J':");
        vt.setRightChild(d, Character.valueOf('J'));
        vt.printVector();

        System.out.println("\nSetting right child of root 'A' with 'X':");
        vt.setRightChild(a, Character.valueOf('X'));
        vt.printVector();
        if (vt.size() != 3) {
            throw new Error("Bad size: " + vt.size() + " != 3");
        }

        System.out.println("\nSetting root with 'Y':");
        vt.setRoot(Character.valueOf('Y'));
        vt.printVector();
        if (vt.size() != 1) {
            throw new Error("Bad size: " + vt.size() + " != 1");
        }
    }

}

/*
 * Session-Log:
 * 
 * Setting root with 'A': [null, A]
 * 
 * Setting right child of 'A' with 'D': [null, A, null, D]
 * 
 * Setting left child of 'A' with 'B': [null, A, B, D]
 * 
 * Setting right child of 'B' with 'F': [null, A, B, D, null, F]
 * 
 * Setting right child of 'F' with 'H': [null, A, B, D, null, F, null, null,
 * null, null, null, H]
 * 
 * Setting left child of 'F' with 'G': [null, A, B, D, null, F, null, null,
 * null, null, G, H]
 * 
 * Removing right child of 'B': [null, A, B, D, null, null, null, null, null,
 * null, null, null]
 * 
 * Setting right child of 'D' with 'J': [null, A, B, D, null, null, null, J,
 * null, null, null, null]
 * 
 * Setting right child of root 'A' with 'X': [null, A, B, X, null, null, null,
 * null, null, null, null, null]
 * 
 * Setting root with 'Y': [null, Y, null, null, null, null, null, null, null,
 * null, null, null]
 */

