/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4;

import java.util.Random;

/**
 * @title task 1 from exercise 4 4
 * @author tbeeler
 * 
 *         1a) see numNodes() 1b) see concatenate() 1c) see reverse()
 */
public class LinkedList {

    /**
     * Solution task 1a. Recursive calculation of the numbers of nodes in the
     * list "head". Runtime O(n).
     * 
     * @param head
     *            Head of the list.
     * @return Number of nodes in the list.
     */
    public int numNodes(Node head) {
        if (head != null) {
            return numNodes(head.getNext()) + 1;
        } else {
            return 0;
        }
    }

    /**
     * Solution task 1b. Concatenate two lists l1 and l2 to a new list. l1 and
     * l2 were not changed. If a change is allowed, the runtime can be improved
     * to O(|l1|). Runtime O(|l2|+|l1|) = O(n), whereas n is the number of
     * elements in the new list.
     * 
     * @param l1
     *            List 1.
     * @param l2
     *            List 2.
     * @return Concatenated list l1 and l2.
     */
    public Node concatenate(Node l1, Node l2) {
        Node l3 = null;
        if (l1 != null) {
            l3 = new Node(l1.getElement());
            l3.append(l1.getNext());
            if (l2 != null) {
                append(append(l3, l1.getNext()), l2);
            } else {
                append(l3, l1.getNext());
            }
        } else {
            if (l2 != null) {
                l3 = new Node(l2.getElement());
                l3.append(l2.getNext());
                append(l3, l2.getNext());
            }
        }
        return l3;
    }

    /**
     * Solution task 1c. Invert the order of list 'head'. No new list is built.
     * The old head afterwards is the last node in the list. Runtime O(n)
     * (task).
     * 
     * @param head
     *            Head of the list.
     * @return New head.
     */
    public Node reverse(Node head) {
        if (head != null && head.getNext() != null) {
            Node newHead = reverse(head.getNext());
            head.append(null);
            Node lastHead = newHead;
            while (lastHead.getNext() != null) {
                lastHead = lastHead.getNext();
            }
            lastHead.append(head);
            return newHead;
        } else {
            return head;
        }
    }

    /*
     * Add a copy of l2 to tail
     */
    private Node append(Node tail, Node l2) {
        while (l2 != null) {
            tail.append(new Node(l2.getElement()));
            l2 = l2.getNext();
            tail = tail.getNext();
        }
        return tail;
    }

    /**
     * Print the elements of the list.
     * 
     * @param head
     *            Head of the list.
     */
    public void printList(Node head) {
        while (head != null) {
            System.out.print(head.getElement().toString());
            System.out.print(" ");
            head = head.getNext();
        }
        System.out.println();
    }

    /**
     * Tests the class exercise 1.
     */
    public static void main(String[] args) {
        // Node head = new Node(Integer.valueOf(1));
        // Node e2 = new Node(Integer.valueOf(2));
        // Node e3 = new Node(Integer.valueOf(3));
        // Node e4 = new Node(Integer.valueOf(4));
        // head.append(e2);
        // e2.append(e3);
        // e3.append(e4);
        // LinkedList ueb1 = new LinkedList();
        // ueb1.printList(head);
        // head = ueb1.reverse(head);
        // ueb1.printList(head);

        System.out.println("=========================================");
        System.out.println("Exercise 4 - Task 1");
        System.out.println("Author: tbeeler@hsr.ch");
        final int L1 = 10;
        final int L2 = 15;
        LinkedList ueb1 = new LinkedList();
        Random random = new Random(1);
        // create a List l1 with 10 elements
        Node head1 = new Node(new Integer((int) (random.nextDouble() * 100)));
        Node l1 = head1;
        for (int i = 1; i < L1; i++) {
            l1.append(new Node(new Integer((int) (random.nextDouble() * 100))));
            l1 = l1.getNext();
        }
        System.out.println("List 1:");
        ueb1.printList(head1);
        System.out.println();
        // create a List l2 with 15 elements
        Node head2 = new Node(new Integer((int) (random.nextDouble() * 100)));
        Node l2 = head2;
        for (int i = 1; i < L2; i++) {
            l2.append(new Node(new Integer((int) (random.nextDouble() * 100))));
            l2 = l2.getNext();
        }
        System.out.println("List 2:");
        ueb1.printList(head2);
        System.out.println();
        if (ueb1.numNodes(head1) != L1) {
            System.err.println("Node count does not match L1 ("
                    + ueb1.numNodes(head1) + " != " + L1 + ")");
            System.exit(1);
        }
        if (ueb1.numNodes(head2) != L2) {
            System.err.println("Node count does not match L2 ("
                    + ueb1.numNodes(head2) + " != " + L2 + ")");
            System.exit(1);
        }
        Node head3 = ueb1.concatenate(head1, head2);
        System.out.println("List 3 = List 1 o List 2:");
        ueb1.printList(head3);
        System.out.println();
        if (ueb1.numNodes(head1) + ueb1.numNodes(head2) != ueb1.numNodes(head3)) {
            System.err.println("Node count does not match head3 ("
                    + (ueb1.numNodes(head1) + ueb1.numNodes(head2)) + ")"
                    + " != " + ueb1.numNodes(head3));
            System.exit(1);
        }
        Node head4 = ueb1.reverse(head3);
        System.out.println("List 4 = inverted List 3:");
        ueb1.printList(head4);
        System.out.println();
        if (ueb1.numNodes(head1) + ueb1.numNodes(head2) != ueb1.numNodes(head4)) {
            System.err.println("Node count does not match head4 ("
                    + (ueb1.numNodes(head1) + ueb1.numNodes(head2)) + ")"
                    + " != " + ueb1.numNodes(head4));
            System.exit(1);
        }

        System.out.println("\nDONE\n");
        System.out.println("=========================================");
    }
}

/**
 * @title Node
 * @author tbeeler
 * 
 *         Utility class. Nodes of a simple linked list.
 */
class Node {

    private final Object element;
    private Node         next;

    /**
     * Constructs a new unlinked node.
     * 
     * @param elem
     *            Object for the node.
     */
    public Node(Object elem) {
        element = elem;
        next = null;
    }

    /**
     * Adds the node next to this node.
     * 
     * @param next
     *            The next node.
     */
    public void append(Node next) {
        this.next = next;
    }

    public Node getNext() {
        return next;
    }

    public Object getElement() {
        return element;
    }
}

/*
 * Session-Log (SOLL):
 * 
 * ========================================= Exercise 4 - Task 1 Author:
 * tbeeler@hsr.ch List 1: 73 41 20 33 96 0 96 93 94 93
 * 
 * List 2: 39 34 29 50 11 77 65 15 37 13 69 80 0 52 74
 * 
 * List 3 = List 1 o List 2: 73 41 20 33 96 0 96 93 94 93 39 34 29 50 11 77 65
 * 15 37 13 69 80 0 52 74
 * 
 * List 4 = inverted List 3: 74 52 0 80 69 13 37 15 65 77 11 50 29 34 39 93 94
 * 93 96 0 96 33 20 41 73
 * 
 * 
 * DONE
 * 
 * =========================================
 */

