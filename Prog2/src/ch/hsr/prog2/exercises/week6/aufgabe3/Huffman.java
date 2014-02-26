/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:34:08 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe3;

import ch.hsr.prog2.exercises.week6.aufgabe2.Entry;

public class Huffman {

    /**
     * Recursively traverses the tree and determines the Huffman-Bitcode.
     * 
     * @param n
     *            The current node of the tree.
     * @param bits
     *            char-array containing the actual bitcode so far.
     * @param nBits
     *            The length of the actual bitcode.
     * @return Length of the whole code.
     */
    public long assignBits(Node<Entry<Long, Character>> n, char[] bits,
            int nBits) {
        // TODO Implement here...
        return -1;
    }

    public static void main(String[] args) {
        String text;
        if (args.length > 0) {
            text = args[0];
            for (int i = 1; i < args.length; i++) {
                text += " ";
                text += args[i];
            }
        } else {
            text = "Und der Name des Pferdes war Pestilence und der Name des Reiters war Tod";
        }
        // build the counter
        // Frequency fc = new Frequency();
        // ArrayList<Entry<Long, Character>> frequencies =
        // fc.countFrequency(text);
        // int originalBitCount = 0;
        // for (int i = 0; i < frequencies.size(); i++) {
        // originalBitCount += frequencies.get(i).getKey() * 8;
        // }
        // build the Huffman-Tree
        // HuffmanTree tree = new HuffmanTree(frequencies, "Huffman");
        //
        // // tree.print();
        //
        // // assign the bits
        // char bits[] = new char[frequencies.size()];
        // long bitCount = new Huffman().assignBits(tree.getRoot(), bits, 0);
        // // output
        // System.out.println("Compression: "
        // + (100 - 100 * bitCount / (double) originalBitCount) + "%");
    }

}

/*
 * Session-Log (SOLL):
 * 
 * 6: r: 000 3: n: 0010 2: m: 00110 2: N: 00111 1: R: 010000 1: u: 010001 1: o:
 * 010010 1: T: 010011 1: f: 010100 1: U: 010101 1: c: 010110 1: l: 010111 2: w:
 * 01100 2: P: 01101 2: i: 01110 2: t: 01111 8: d: 100 4: a: 1010 5: s: 1011 13:
 * : 110 13: e: 111 Compression: 52.083333333333336%
 */

