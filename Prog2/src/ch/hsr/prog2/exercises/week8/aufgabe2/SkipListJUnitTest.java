/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 11 11:27:57 CEST 2013
 */

package ch.hsr.prog2.exercises.week8.aufgabe2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class SkipListJUnitTest {

    SkipList<Integer, String> skipList;

    @Before
    public void setUp() {
        skipList = new SkipList<Integer, String>(4);
    }

    @Test
    public void testInit() {
        assertEquals(0, skipList.size());
    }

    @Test
    public void testPut() {
        assertNull(skipList.put(Integer.valueOf(1), "value_1"));
        assertEquals(1, skipList.size());
        assertEquals("value_1", skipList.put(Integer.valueOf(1), "value_2"));
        assertEquals(1, skipList.size());
    }

    @Test
    public void testGet() {
        assertNull(skipList.get(Integer.valueOf(-1)));
        testPut();
        assertNull(skipList.get(Integer.valueOf(-1)));
        assertEquals("value_2", skipList.get(Integer.valueOf(1)));
    }

    @Test
    public void testRemove() {
        assertNull(skipList.remove(Integer.valueOf(-1)));
        testPut();
        assertNull(skipList.remove(Integer.valueOf(-1)));
        assertEquals("value_2", skipList.remove(Integer.valueOf(1)));
        assertNull(skipList.remove(Integer.valueOf(1)));
    }

    @Test
    public void stressTest() {

        final int NUMBER_OF_TESTS = 10000;
        final int RANGE = 100;
        Random rand = new Random(1);
        Map<Integer, String> map = new HashMap<Integer, String>();

        for (int i = 0; i < NUMBER_OF_TESTS; i++) {
            int n = rand.nextInt() % RANGE;
            String str = "String_" + n;

            String retMap = map.put(Integer.valueOf(n), str);
            String retSkipList = skipList.put(Integer.valueOf(n), str);
            assertEquals(retMap, retSkipList);

            int sizeMap = map.size();
            int sizeSkipList = skipList.size();
            assertEquals(sizeMap, sizeSkipList);

            for (int j = -RANGE; j <= RANGE; j++) {
                String mapGet = map.get(Integer.valueOf(j));
                String skipListGet = skipList.get(Integer.valueOf(j));
                assertEquals(mapGet, skipListGet);
            }

        }

        for (int i = -RANGE; i <= RANGE; i++) {
            String mapRemove = map.remove(Integer.valueOf(i));
            String skipListRemove = skipList.remove(Integer.valueOf(i));
            assertEquals(mapRemove, skipListRemove);
        }

    }

}
