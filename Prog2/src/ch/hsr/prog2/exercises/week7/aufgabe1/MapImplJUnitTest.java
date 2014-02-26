package ch.hsr.prog2.exercises.week7.aufgabe1;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class MapImplJUnitTest {

    private MapImpl<Integer, String> map;

    @Before
    public void initTest() {
        map = new MapImpl<Integer, String>();
    }

    @Test
    public void testFillingAndRemoving() {
        final int LEN = 3;
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
        for (int i = 0; i < LEN; i++) {
            map.put(Integer.valueOf(i), "String_" + i);
            assertEquals(i + 1, map.size());
        }
        for (int i = 0; i < LEN; i++) {
            map.remove(Integer.valueOf(i));
            assertEquals(LEN - i - 1, map.size());
        }
        assertTrue(map.isEmpty());
        assertEquals(0, map.size());
    }

    @Test
    public void testPut() {
        map.put(Integer.valueOf(1), "String_1");
        assertEquals("String_1", map.put(Integer.valueOf(1), "String_1.1"));
        assertEquals("String_1.1", map.get(Integer.valueOf(1)));
    }

    @Test
    public void testSearching() {
        assertFalse(map.containsKey(Integer.valueOf(0)));
        assertFalse(map.containsValue("String_0"));
        assertNull(map.get(Integer.valueOf(0)));
        assertNull(map.remove(Integer.valueOf(0)));
        final int LEN = 10000;
        fillMapWithEntries(map, LEN);
        assertEquals(LEN, map.size());
        assertEquals("String_1", map.get(Integer.valueOf(1)));
        assertTrue(map.containsKey(Integer.valueOf(LEN)));
        assertTrue(map.containsValue("String_" + LEN));
        assertFalse(map.containsKey(Integer.valueOf(LEN + 1)));
        assertFalse(map.containsValue("String_" + (LEN + 1)));
    }

    @Test
    public void testPutAll() {
        MapImpl<Integer, String> map1 = new MapImpl<Integer, String>();
        fillMapWithEntries(map1, 1, 10);
        MapImpl<Integer, String> map2 = new MapImpl<Integer, String>();
        fillMapWithEntries(map2, 5, 10);
        map1.putAll(map2);
        assertEquals(14, map1.size());
    }

    @Test
    public void testSets() {
        final int LEN = 3;
        fillMapWithEntries(map, LEN);
        Set<Entry<Integer, String>> entrySet = map.entrySet();
        assertEquals(LEN, entrySet.size());
        Set<Integer> keySet = map.keySet();
        for (int i = 1; i <= LEN; i++) {
            assertTrue(keySet.contains(Integer.valueOf(i)));
        }
        map.clear();
        assertEquals(0, map.size());
    }

    @Test
    public void testValues() {
        final int LEN = 3;
        fillMapWithEntries(map, LEN);
        Collection<String> values = map.values();
        for (int i = 1; i <= LEN; i++) {
            String str = "String_" + i;
            assertTrue(values.contains(str));
        }
    }

    @Test
    public void testNulls() {
        assertEquals(null, map.put(null, "NULL 1"));
        assertEquals("NULL 1", map.put(null, "NULL 2"));
        assertEquals("NULL 2", map.get(null));
        assertEquals(Boolean.TRUE, Boolean.valueOf(map.containsKey(null)));
        assertEquals("NULL 2", map.remove(null));
        assertEquals(null, map.put(Integer.valueOf(0), null));
        assertEquals(Boolean.TRUE, Boolean.valueOf(map.containsValue(null)));
    }

    /**
     * Filling the map with sequence of entries: [1, "String_1"], [2,
     * "String_2"], ...
     * 
     * @param map
     *            The map to fill.
     * @param nr
     *            Number of entries to fill.
     */
    private void fillMapWithEntries(Map<Integer, String> map, int nr) {
        fillMapWithEntries(map, 1, nr);
    }

    /**
     * Filling the map with sequence of entries: [`start`, "String_`start`"],
     * [`start+1`, "String_`start+1`"], ...
     * 
     * @param map
     *            The map to fill.
     * @param start
     *            Number for first entry.
     * @param nr
     *            Number of entries to fill.
     */
    private void fillMapWithEntries(Map<Integer, String> map, int start, int nr) {
        for (int i = start; i < start + nr; i++) {
            map.put(Integer.valueOf(i), "String_" + i);
        }
    }

}
