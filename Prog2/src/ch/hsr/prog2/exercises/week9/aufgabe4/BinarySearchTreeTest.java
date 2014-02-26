/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 18 14:19:46 CEST 2013
 */

package ch.hsr.prog2.exercises.week9.aufgabe4;

import java.util.Iterator;
import java.util.Random;

import ch.hsr.prog2.exercises.week9.aufgabe4.BinarySearchTree.Entry;

public class BinarySearchTreeTest {

    private static Random randomGenerator = new Random(1);

    private static BinarySearchTree<Integer, String> generateTree(int nodes) {
        int key;
        BinarySearchTree<Integer, String> ret = new BinarySearchTree<Integer, String>();
        for (int i = 0; i < nodes; i++) {
            key = randomGenerator.nextInt() * Integer.MAX_VALUE;
            ret.insert(Integer.valueOf(key), "String_" + i);
        }
        return ret;
    }

    public static void main(String[] args) {
        System.out.println("BINARY TREE TEST");
        System.out
                .println("Please be patient, the following operations may take some time...");
        final int TESTRUNS = 100;
        final int BEGINSIZE = 10000;
        final int VARYSIZE = 10;
        long startTime = System.currentTimeMillis();

        BinarySearchTree<Integer, String> bst = new BinarySearchTree<Integer, String>();
        double avgHeight = 0;
        double avgEntries = 0;
        double avgTime = 0;
        for (int i = 0; i < TESTRUNS; i++) {
            startTime = System.currentTimeMillis();
            bst = generateTree(BEGINSIZE + i * VARYSIZE);
            avgTime += System.currentTimeMillis() - startTime;
            avgHeight += bst.getHeight();
            avgEntries += BEGINSIZE + i * VARYSIZE;
        }
        avgTime /= TESTRUNS;
        avgEntries /= TESTRUNS;
        avgHeight /= TESTRUNS;
        System.out.println("Test successful, results are as follows:");
        System.out.println("Average time for generation is: " + avgTime + "ms");
        System.out.println("Average entries are: " + avgEntries);
        System.out.println("Average height is: " + avgHeight);
        System.out.println("In h=C*log2(n), C=h/log2(n) = " + avgHeight
                / (Math.log(avgEntries) / Math.log(2)));
        System.out.println();

        bst = generateTree(20);
        int search = 15138431;
        Entry<Integer, String> searchResult;
        bst.insert(Integer.valueOf(search), "String_" + search);
        searchResult = bst.find(Integer.valueOf(search));
        if (searchResult == null) {
            System.err.println("Search for node " + search + " failed!");
        } else {
            System.out.println("Search for node " + search + " successful!");
        }
        System.out.println();
        bst.insert(Integer.valueOf(search), "String_" + search);
        bst.insert(Integer.valueOf(search), "String_" + search);
        bst.insert(Integer.valueOf(search), "String_" + search);
        Iterator<Entry<Integer, String>> it = bst.findAll(
                Integer.valueOf(search)).iterator();
        int count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
            System.out.println("Search for node " + search + " successful!");
        }
        System.out.println("Search for node " + search + ": " + count
                + " nodes found!");
        System.out.println();
        it = bst.findAll(Integer.valueOf(search)).iterator();
        count = 0;
        while (it.hasNext()) {
            bst.remove(it.next());
        }

        it = bst.findAll(Integer.valueOf(search)).iterator();
        count = 0;
        while (it.hasNext()) {
            count++;
            it.next();
            System.out.println("Search for node " + search + " successful!");
        }
        System.out.println("Search for node " + search + ": " + count
                + " nodes found!");
    }

}

/*
 * Session-Log:
 * 
 * BINARY TREE TEST Please be patient, the following operations may take some
 * time... Test successful, results are as follows: Average time for generation
 * is: 9.07ms Average entries are: 10495.0 Average height is: 30.81 In
 * h=C*log2(n), C=h/log2(n) = 2.306584099301782
 * 
 * Search for node 15138431 successful!
 * 
 * Search for node 15138431 successful! Search for node 15138431 successful!
 * Search for node 15138431 successful! Search for node 15138431 successful!
 * Search for node 15138431: 4 nodes found!
 * 
 * Search for node 15138431: 0 nodes found!
 */

