/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Wed Apr 24 15:24:29 CEST 2013
 */

package ch.hsr.prog2.exercises.week10.aufgabe3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Map;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ch.hsr.prog2.exercises.week9.aufgabe4.BinarySearchTree.Entry;

public class AVLTreeJUnitTest {

    AVLTreeImpl<Integer, String> avlTree;

    @Before
    public void setUp() {
        avlTree = new AVLTree<Integer, String>().getImpl();
    }

    @After
    public void tearDown() {
        if (avlTree instanceof AVLTreeImplGVS) {
            ((AVLTreeImplGVS<Integer, String>) avlTree).gvsTree.disconnect();
        }
    }

    @Test
    public void testPut() {
        int[] keys = { 2, 1, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
    }

    @Test
    public void testGet() {
        int[] keys = { 2, 1, 5, 4, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=2 ROOT", " 3 / Str_3 | h=0 / parent(key)=4",
                " 4 / Str_4 | h=1 \\ parent(key)=2",
                " 5 / Str_5 | h=0 \\ parent(key)=4", };
        runTest(keys, expected);
        assertEquals("Str_2", avlTree.get(2));
        assertEquals("Str_5", avlTree.get(5));
        assertNull(avlTree.get(0));
        assertNull(avlTree.get(6));
    }

    @Test
    public void testSingleRotationLeftInRoot() {
        int[] keys = { 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
    }

    @Test
    public void testSingleRotationLeftBelowRoot() {
        int[] keys = { 5, 6, 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=5",
                " 3 / Str_3 | h=0 \\ parent(key)=2", " 5 / Str_5 | h=2 ROOT",
                " 6 / Str_6 | h=0 \\ parent(key)=5", };
        runTest(keys, expected);
    }

    @Test
    public void testSingleRotationRightInRoot() {
        int[] keys = { 3, 2, 1 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
    }

    @Test
    public void testSingleRotationRightBelowRoot() {
        int[] keys = { 2, 1, 5, 4, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=2 ROOT", " 3 / Str_3 | h=0 / parent(key)=4",
                " 4 / Str_4 | h=1 \\ parent(key)=2",
                " 5 / Str_5 | h=0 \\ parent(key)=4", };
        runTest(keys, expected);
    }

    @Test
    public void testDoubleRotationLeftInRoot() {
        int[] keys = { 1, 3, 2 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
    }

    @Test
    public void testDoubleRotationLeftBelowRoot() {
        int[] keys = { 2, 1, 3, 5, 4 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=2 ROOT", " 3 / Str_3 | h=0 / parent(key)=4",
                " 4 / Str_4 | h=1 \\ parent(key)=2",
                " 5 / Str_5 | h=0 \\ parent(key)=4", };
        runTest(keys, expected);
    }

    @Test
    public void testDoubleRotationRightinRoot() {
        int[] keys = { 3, 1, 2 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
    }

    @Test
    public void testDoubleRotationRightBelowRoot() {
        int[] keys = { 4, 3, 5, 1, 2 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=4",
                " 3 / Str_3 | h=0 \\ parent(key)=2", " 4 / Str_4 | h=2 ROOT",
                " 5 / Str_5 | h=0 \\ parent(key)=4", };
        runTest(keys, expected);
    }

    @Test
    public void testMultipleSameKeys() {
        int[] keys = { 3, 1, 2 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
        avlTree.put(2, "Str_22");
        avlTree.put(2, "Str_23");
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_23 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingCase1() {
        // L�schen Fall 1 gem. BST-Folie 11:
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 6, 2, 9, 1, 4, 8 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=6",
                " 4 / Str_4 | h=0 \\ parent(key)=2", " 6 / Str_6 | h=2 ROOT",
                " 8 / Str_8 | h=0 / parent(key)=9",
                " 9 / Str_9 | h=1 \\ parent(key)=6", };
        runTest(keys, expected);
        assertEquals("Str_4", avlTree.remove(4));
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=6", " 6 / Str_6 | h=2 ROOT",
                " 8 / Str_8 | h=0 / parent(key)=9",
                " 9 / Str_9 | h=1 \\ parent(key)=6", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingCase2() {
        // L�schen Fall 2 gem. BST-Folie 12:
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 6, 2, 9, 1, 4, 8, 5 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=2 / parent(key)=6",
                " 4 / Str_4 | h=1 \\ parent(key)=2",
                " 5 / Str_5 | h=0 \\ parent(key)=4", " 6 / Str_6 | h=3 ROOT",
                " 8 / Str_8 | h=0 / parent(key)=9",
                " 9 / Str_9 | h=1 \\ parent(key)=6", };
        runTest(keys, expected);
        assertEquals("Str_4", avlTree.remove(4));
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=6",
                " 5 / Str_5 | h=0 \\ parent(key)=2", " 6 / Str_6 | h=2 ROOT",
                " 8 / Str_8 | h=0 / parent(key)=9",
                " 9 / Str_9 | h=1 \\ parent(key)=6", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingCase3() {
        // L�schen Fall 3 gem. BST-Folie 13:
        // Hinweis: Baum entsprechend 'aufgef�llt' (wegen AVL!)
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 1, -10, 4, -15, -5, 2, 9, -18, -12, -7, -3, 3, 7, 10, 6 };
        String[] expected = { "-18 / Str_-18 | h=0 / parent(key)=-15",
                "-15 / Str_-15 | h=1 / parent(key)=-10",
                "-12 / Str_-12 | h=0 \\ parent(key)=-15",
                "-10 / Str_-10 | h=2 / parent(key)=1",
                "-7 / Str_-7 | h=0 / parent(key)=-5",
                "-5 / Str_-5 | h=1 \\ parent(key)=-10",
                "-3 / Str_-3 | h=0 \\ parent(key)=-5", " 1 / Str_1 | h=4 ROOT",
                " 2 / Str_2 | h=1 / parent(key)=4",
                " 3 / Str_3 | h=0 \\ parent(key)=2",
                " 4 / Str_4 | h=3 \\ parent(key)=1",
                " 6 / Str_6 | h=0 / parent(key)=7",
                " 7 / Str_7 | h=1 / parent(key)=9",
                " 9 / Str_9 | h=2 \\ parent(key)=4",
                "10 / Str_10 | h=0 \\ parent(key)=9", };
        runTest(keys, expected);
        assertEquals("Str_4", avlTree.remove(4));
        expected = new String[] { "-18 / Str_-18 | h=0 / parent(key)=-15",
                "-15 / Str_-15 | h=1 / parent(key)=-10",
                "-12 / Str_-12 | h=0 \\ parent(key)=-15",
                "-10 / Str_-10 | h=2 / parent(key)=1",
                "-7 / Str_-7 | h=0 / parent(key)=-5",
                "-5 / Str_-5 | h=1 \\ parent(key)=-10",
                "-3 / Str_-3 | h=0 \\ parent(key)=-5", " 1 / Str_1 | h=3 ROOT",
                " 2 / Str_2 | h=1 / parent(key)=6",
                " 3 / Str_3 | h=0 \\ parent(key)=2",
                " 6 / Str_6 | h=2 \\ parent(key)=1",
                " 7 / Str_7 | h=0 / parent(key)=9",
                " 9 / Str_9 | h=1 \\ parent(key)=6",
                "10 / Str_10 | h=0 \\ parent(key)=9", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingAtRoot1() {
        int[] keys = { 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
        assertEquals("Str_1", avlTree.remove(1));
        assertEquals(2, avlTree.size());
        assertEquals("Str_3", avlTree.remove(3));
        assertEquals(1, avlTree.size());
        assertEquals("Str_2", avlTree.remove(2));
        assertEquals(0, avlTree.size());
    }

    @Test
    public void testRemovingAtRoot2() {
        int[] keys = { 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
        assertEquals("Str_1", avlTree.remove(1));
        assertEquals(2, avlTree.size());
        assertEquals("Str_2", avlTree.remove(2));
        assertEquals(1, avlTree.size());
        assertEquals("Str_3", avlTree.remove(3));
        assertEquals(0, avlTree.size());
    }

    @Test
    public void testRemovingAtRoot3() {
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
        assertEquals("Str_2", avlTree.remove(2));
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=3",
                " 3 / Str_3 | h=1 ROOT", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
        assertEquals(2, avlTree.size());
        assertEquals("Str_3", avlTree.remove(3));
        assertEquals(1, avlTree.size());
        assertEquals("Str_1", avlTree.remove(1));
        assertEquals(0, avlTree.size());
    }

    @Test
    public void testRemovingAtRoot4() {
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 3, 2, 6, 4 };
        String[] expected = { " 2 / Str_2 | h=0 / parent(key)=3",
                " 3 / Str_3 | h=2 ROOT", " 4 / Str_4 | h=0 / parent(key)=6",
                " 6 / Str_6 | h=1 \\ parent(key)=3", };
        runTest(keys, expected);
        assertEquals("Str_3", avlTree.remove(3));
        expected = new String[] { " 2 / Str_2 | h=0 / parent(key)=4",
                " 4 / Str_4 | h=1 ROOT", " 6 / Str_6 | h=0 \\ parent(key)=4", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingAtRoot5() {
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 3, 2, 6, 1, 4, 7, 5 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=3", " 3 / Str_3 | h=3 ROOT",
                " 4 / Str_4 | h=1 / parent(key)=6",
                " 5 / Str_5 | h=0 \\ parent(key)=4",
                " 6 / Str_6 | h=2 \\ parent(key)=3",
                " 7 / Str_7 | h=0 \\ parent(key)=6", };
        runTest(keys, expected);
        assertEquals("Str_3", avlTree.remove(3));
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 / parent(key)=4", " 4 / Str_4 | h=2 ROOT",
                " 5 / Str_5 | h=0 / parent(key)=6",
                " 6 / Str_6 | h=1 \\ parent(key)=4",
                " 7 / Str_7 | h=0 \\ parent(key)=6", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void testRemovingAtRoot6() {
        int[] keys = { 1 };
        String[] expected = { " 1 / Str_1 | h=0 ROOT", };
        runTest(keys, expected);
        assertEquals(null, avlTree.remove(8888));
        assertEquals(1, avlTree.size());
        runTest(keys, expected);
        assertEquals("Str_1", avlTree.remove(1));
        assertEquals(0, avlTree.size());
    }

    @Test
    public void testRemovingEntryNotInTree() {
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        int[] keys = { 1, 2, 3 };
        String[] expected = { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        runTest(keys, expected);
        assertNull(avlTree.remove(4));
        expected = new String[] { " 1 / Str_1 | h=0 / parent(key)=2",
                " 2 / Str_2 | h=1 ROOT", " 3 / Str_3 | h=0 \\ parent(key)=2", };
        avlTree.inorder(nodes, avlTree.getRoot());
        verify(nodes, expected);
    }

    @Test
    public void stressTest() {
        final int SIZE = 10000;
        Random randomGenerator = new Random(1);
        // a Map to compare:
        Map<Integer, String> map = new Hashtable<Integer, String>();
        // key-Counters: count for every key how many time it was generated
        Map<Integer, Integer> keyCounters = new Hashtable<Integer, Integer>();
        // fill the Tree
        for (int i = 0; i < SIZE; i++) {
            int key = (int) (randomGenerator.nextFloat() * SIZE / 3);
            Integer numberOfKeys = keyCounters.get(key);
            if (numberOfKeys == null) {
                numberOfKeys = 1;
            } else {
                numberOfKeys++;
            }
            keyCounters.put(key, numberOfKeys);
            avlTree.put(key, "_" + i);
            map.put(key, "_" + i);
            assertEquals(keyCounters.size(), avlTree.size());
            assertEquals(map.size(), avlTree.size());
        }
        verifyInorder();
        // remove all Keys
        Integer[] keyArr = new Integer[1];
        keyArr = map.keySet().toArray(keyArr);
        for (int key : keyArr) {
            assertEquals(map.remove(key), avlTree.remove(key));
            assertEquals(map.size(), avlTree.size());
            verifyInorder();
        }
        assertEquals(0, avlTree.size());
    }

    private void verifyInorder() {
        Collection<Entry<Integer, String>> inorderList = avlTree.inorder();
        int last = Integer.MIN_VALUE;
        for (Entry<Integer, String> entry : inorderList) {
            Integer key = entry.getKey();
            assertTrue(key.compareTo(last) >= 0);
            last = key;
        }
    }

    private void runTest(int[] keys, String[] expected) {
        for (int key : keys) {
            avlTree.put(key, "Str_" + key);
        }
        Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes = new LinkedList<AVLTreeImpl<Integer, String>.AVLNode>();
        avlTree.inorder(nodes, avlTree.getRoot());
        assertEquals(expected.length, nodes.size());
        verify(nodes, expected);
    }

    private void verify(Collection<AVLTreeImpl<Integer, String>.AVLNode> nodes,
            String[] expected) {
        int i = 0;
        for (AVLTreeImpl<Integer, String>.AVLNode node : nodes) {
            String nodeStr = node.toString();
            String expectedStr = expected[i];
            assertEquals(expectedStr, nodeStr);
            i++;
        }
    }

}
