/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:34:08 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe3;

import gvs.tree.GVSTreeWithCollection;

import java.util.ArrayList;
import java.util.Collection;

import javax.swing.JOptionPane;

import ch.hsr.prog2.exercises.week6.aufgabe2.Entry;

public class HuffmanTree {

    private Node<Entry<Long, Character>> root;
    private int                          size;
    private GVSTreeWithCollection        gvsTree = null;

    public HuffmanTree(Collection<Entry<Long, Character>> frequencies,
            String gvsTitle) {
        // init the communication to the "Graph-Visualization-Server GVS":
        initGVS(gvsTitle);

        size = frequencies.size();

        // example
        Entry<Long, Character> e;
        e = new Entry<Long, Character>(Long.valueOf(1), Character.valueOf('r'));
        Node<Entry<Long, Character>> leftright = new Node<Entry<Long, Character>>(
                e);
        gvsTree.add(leftright);
        gvsTree.display();

        e = new Entry<Long, Character>(Long.valueOf(2), Character.valueOf('l'));
        Node<Entry<Long, Character>> leftleft = new Node<Entry<Long, Character>>(
                e);
        gvsTree.add(leftleft);
        gvsTree.display();

        e = new Entry<Long, Character>(Long.valueOf(3), Character.valueOf('l'));
        Node<Entry<Long, Character>> left = new Node<Entry<Long, Character>>(e,
                leftleft, leftright);
        gvsTree.add(left);
        gvsTree.display();

        e = new Entry<Long, Character>(Long.valueOf(4), Character.valueOf('r'));
        Node<Entry<Long, Character>> right = new Node<Entry<Long, Character>>(e);
        gvsTree.add(right);
        e = new Entry<Long, Character>(Long.valueOf(5), Character.valueOf('#'));
        root = new Node<Entry<Long, Character>>(e, left, right);
        gvsTree.add(root);
        gvsTree.display();

        // TODO:
        // 1. comment out the example
        // 2. implement a Huffman-Tree

        gvsTree.disconnect();
    }

    public Node<Entry<Long, Character>> getRoot() {
        return root;
    }

    public void print() {
        int indent = (int) Math.pow(2,
                1 + Math.ceil(Math.log(size) / (2 * Math.log(2))));
        int lastIndent = -1;
        int ci = 0;
        Node<Entry<Long, Character>> n;
        ArrayList<Node<Entry<Long, Character>>> indices = new ArrayList<Node<Entry<Long, Character>>>();
        ArrayList<Long> indents = new ArrayList<Long>();
        indices.add(root);
        indents.add(Long.valueOf(indent));
        while (indices.size() > 0) {
            n = indices.get(0);
            indices.remove(0);
            indent = indents.get(0).intValue();
            indents.remove(0);
            if (indent < lastIndent) {
                System.out.println();
                ci = 0;
            }
            lastIndent = indent;
            if (n.getLeftChild() != null) {
                indices.add(n.getLeftChild());
                indents.add(Long.valueOf(indent / 2));
            }
            if (n.getRightChild() != null) {
                indices.add(n.getRightChild());
                indents.add(Long.valueOf(indent + indent / 2));
            }

            for (; ci < indent; ci++) {
                System.out.print("    ");
            }
            System.out.print(n.getValue().toString());

        }
    }

    void initGVS(String title) {
        gvsTree = new GVSTreeWithCollection(title);
        if ((System.getProperty("NoGVS") == null) && (!gvsTree.isConnected())) {
            String[] text = { "Connection to GVS-Server failed!\n",
                    "Start \"GVS_Server_v1.4.jar\" first.",
                    "(located in \"7_Zusatzmaterial/GraphsVisualizationService-GVS_v1.6.zip\")" };
            JOptionPane.showMessageDialog(null, text);
            System.exit(2);
        }
    }

    public static void main(String[] args) {
        ArrayList<Entry<Long, Character>> frequencies = new ArrayList<Entry<Long, Character>>();
        frequencies.add(new Entry<Long, Character>(Long.valueOf(3L), Character
                .valueOf('a')));
        frequencies.add(new Entry<Long, Character>(Long.valueOf(4L), Character
                .valueOf('e')));
        frequencies.add(new Entry<Long, Character>(Long.valueOf(5L), Character
                .valueOf('i')));
        frequencies.add(new Entry<Long, Character>(Long.valueOf(6L), Character
                .valueOf('o')));
        frequencies.add(new Entry<Long, Character>(Long.valueOf(7L), Character
                .valueOf('u')));
        HuffmanTree tree = new HuffmanTree(frequencies, "HuffmanTree");
        tree.print();
    }
}

/*
 * Session-Log (SOLL):
 * 
 * 
 * 25 11 14 5: i 6: o7: u 7 3: a 4: e
 */

