/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 18 14:19:46 CEST 2013
 */

package ch.hsr.prog2.exercises.week9.aufgabe4;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import ch.hsr.prog2.exercises.week9.aufgabe4.BinarySearchTree.Entry;

public class BinarySearchTreeJUnitTest {

    BinarySearchTree<Integer, String> bst;

    @Before
    public void setUp() {
        bst = new BinarySearchTree<Integer, String>();
    }

    @Test
    public void testEmptySizeInsertClear() {
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        bst.insert(Integer.valueOf(1), "String_1");
        assertEquals(1, bst.size());
        assertFalse(bst.isEmpty());
        bst.insert(Integer.valueOf(2), "String_2");
        assertEquals(2, bst.size());
        bst.insert(Integer.valueOf(2), "String_2");
        assertEquals(3, bst.size());
        bst.clear();
        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
    }

    @Test
    public void testGetHeight() {
        assertEquals(-1, bst.getHeight());
        bst.insert(Integer.valueOf(1), "String_1");
        assertEquals(0, bst.getHeight());
        bst.insert(Integer.valueOf(2), "String_2");
        assertEquals(1, bst.getHeight());
    }

    @Test
    public void testFind() {
        Entry<Integer, String> entry;
        entry = bst.find(Integer.valueOf(1));
        assertNull(entry);
        Entry<Integer, String> insertedEntry = bst.insert(Integer.valueOf(1),
                "String_1");
        entry = bst.find(Integer.valueOf(1));
        assertNotNull(entry);
        assertEquals(Integer.valueOf(1), entry.getKey());
        assertEquals("String_1", entry.getValue());
        assertSame(insertedEntry, entry);
    }

    @Test
    public void testFindAll() {
        Collection<Entry<Integer, String>> col;
        col = bst.findAll(Integer.valueOf(1));
        assertEquals(0, col.size());
        bst.insert(Integer.valueOf(1), "String_1");
        col = bst.findAll(Integer.valueOf(2));
        assertEquals(0, col.size());
        bst.insert(Integer.valueOf(2), "String_2");
        col = bst.findAll(Integer.valueOf(2));
        assertEquals(1, col.size());
        bst.insert(Integer.valueOf(2), "String_2");
        col = bst.findAll(Integer.valueOf(2));
        assertEquals(2, col.size());
    }

    @Test
    public void testRemove() {
        Entry<Integer, String> entry = new Entry<Integer, String>(
                Integer.valueOf(1), "String_1");
        entry = bst.remove(entry);
        assertNull(entry);
        final Entry<Integer, String> entry1 = bst.insert(Integer.valueOf(1),
                "String_1");
        entry = bst.remove(entry1);
        assertSame(entry, entry1);
        assertEquals(0, bst.size());
        final Entry<Integer, String> entry1a = bst.insert(Integer.valueOf(1),
                "String_1a");
        final Entry<Integer, String> entry1b = bst.insert(Integer.valueOf(1),
                "String_1b");
        assertEquals(2, bst.size());
        entry = bst.remove(entry1a);
        assertSame(entry1a, entry);
        assertEquals(1, bst.size());
        entry = bst.remove(entry1b);
        assertSame(entry1b, entry);
        assertEquals(0, bst.size());
    }

    @Test
    public void testRemoveCase3() {
        bst.insert(Integer.valueOf(1), "String_1");
        Entry<Integer, String> entryToRemove = bst.insert(Integer.valueOf(3),
                "String_3");
        bst.insert(Integer.valueOf(2), "String_2");
        bst.insert(Integer.valueOf(8), "String_8");
        bst.insert(Integer.valueOf(6), "String_6");
        bst.insert(Integer.valueOf(9), "String_9");
        bst.insert(Integer.valueOf(5), "String_5");
        assertEquals(7, bst.size());
        assertEquals(4, bst.getHeight());
        Entry<Integer, String> removedEntry = bst.remove(entryToRemove);
        assertSame(entryToRemove, removedEntry);
        assertEquals(6, bst.size());
        assertEquals(3, bst.getHeight());
        bst.remove(bst.find(Integer.valueOf(6)));
        assertEquals(5, bst.size());
        assertEquals(3, bst.getHeight());
        bst.remove(bst.find(Integer.valueOf(9)));
        assertEquals(4, bst.size());
        assertEquals(2, bst.getHeight());
    }

    @Test
    public void stressTest() {
        final int SIZE = 10000;
        Random randomGenerator = new Random();
        List<Entry<Integer, String>> entriesList = new LinkedList<Entry<Integer, String>>();
        // key-Counters: count for every key how many time it was generated
        Map<Integer, Integer> keyCounters = new Hashtable<Integer, Integer>();
        // fill the Tree
        for (int i = 0; i < SIZE; i++) {
            int key = (int) (randomGenerator.nextFloat() * SIZE / 3);
            Integer numberOfKeys = keyCounters.get(Integer.valueOf(key));
            if (numberOfKeys == null) {
                numberOfKeys = Integer.valueOf(1);
            } else {
                numberOfKeys = Integer.valueOf(numberOfKeys.intValue() + 1);
            }
            keyCounters.put(Integer.valueOf(key), numberOfKeys);
            Entry<Integer, String> entry = bst.insert(Integer.valueOf(key),
                    "String_" + i);
            entriesList.add(entry);
            assertEquals(i + 1, bst.size());
        }
        // verify the number of entries per key
        for (Map.Entry<Integer, Integer> keyEntry : keyCounters.entrySet()) {
            int key = keyEntry.getKey().intValue();
            int numberOfKeys = keyEntry.getValue().intValue();
            assertEquals(numberOfKeys, bst.findAll(Integer.valueOf(key)).size());

        }
        // remove all entries
        int size = bst.size();
        for (Entry<Integer, String> entry : entriesList) {
            Entry<Integer, String> deletedEntry = bst.remove(entry);
            assertSame(entry, deletedEntry);
            assertEquals(--size, bst.size());
        }
    }

}
