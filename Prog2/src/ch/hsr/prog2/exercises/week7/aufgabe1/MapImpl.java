/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr  4 16:04:31 CEST 2013
 */

package ch.hsr.prog2.exercises.week7.aufgabe1;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class MapImpl<K, V> implements Map<K, V> {

    private LinkedList<MapEntry<K, V>> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean containsKey(Object aKey) {
        for (MapEntry<K, V> tempEntry : list) {
            if (tempEntry.getKey().equals(aKey)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(Object aValue) {
        for (MapEntry<K, V> tempEntry : list) {
            if (tempEntry.getValue().equals(aValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(Object key) {
        V value = null;
        for (MapEntry<K, V> tempEntry : list) {
            if (tempEntry.getKey().equals(key)) {
                value = tempEntry.getValue();
            }
        }
        return value;
    }

    @Override
    public V put(K aKey, V aValue) {
        MapEntry<K, V> entry = null;
        V oldValue = null;
        for (MapEntry<K, V> tempEntry : list) {
            if (tempEntry.getKey().equals(aKey)) {
                entry = tempEntry;
                oldValue = entry.getValue();
                entry.setValue(aValue);
            }
        }
        if (entry == null) {
            entry = new MapEntry<K, V>(aKey, aValue);
            list.add(entry);
        }

        return oldValue;
    }

    @Override
    public V remove(Object aKey) {
        MapEntry<K, V> entryToRemove = null;
        V oldValue = null;
        for (MapEntry<K, V> tempEntry : list) {
            if (tempEntry.getKey().equals(aKey)) {
                entryToRemove = tempEntry;
                oldValue = entryToRemove.getValue();
            }
        }
        list.remove(entryToRemove);

        return oldValue;

    }

    @Override
    public void putAll(Map<? extends K, ? extends V> map) {
        for (java.util.Map.Entry<? extends K, ? extends V> tempEntry : map
                .entrySet()) {
            put(tempEntry.getKey(), tempEntry.getValue());
        }
    }

    @Override
    public void clear() {
        list.clear();
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        for (MapEntry<K, V> tempEntry : list) {
            keySet.add(tempEntry.getKey());
        }
        return keySet;
    }

    @SuppressWarnings("unchecked")
    @Override
    public Collection<V> values() {
        return (Collection<V>) list.subList(0, list.size() - 1);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<java.util.Map.Entry<K, V>> entrySet = new HashSet<Entry<K, V>>();
        for (MapEntry<K, V> tempEntry : list) {
            entrySet.add(tempEntry);
        }
        return entrySet;
    }

    public void print() {
        for (MapEntry<K, V> tempEntry : list) {
            System.out.println(tempEntry);
        }
    }

    public static void main(String[] args) {

        MapImpl<Integer, String> map1 = new MapImpl<Integer, String>();
        map1.put(Integer.valueOf(1), "one");
        map1.put(Integer.valueOf(5), "five 1");
        map1.put(Integer.valueOf(10), "ten");
        map1.put(Integer.valueOf(5), "five 2");
        map1.put(Integer.valueOf(54), "fifty-four");
        map1.print();

        MapImpl<Integer, String> map2 = new MapImpl<Integer, String>();
        map2.put(Integer.valueOf(2), "two");
        map2.put(Integer.valueOf(6), "six 1");
        map2.put(Integer.valueOf(5), "five 3");
        map2.put(Integer.valueOf(11), "eleven");
        map2.put(Integer.valueOf(55), "fifty-five");
        map2.put(Integer.valueOf(6), "six 2");
        map2.print();

        map1.putAll(map2);
        map1.print();

    }
}

/*
 * Session-Log (SOLL):
 * 
 * Printing map (4 Entries): 1: one 5: five 2 10: ten 54: fifty-four Printing
 * map (5 Entries): 2: two 6: six 2 5: five 3 11: eleven 55: fifty-five Printing
 * map (8 Entries): 1: one 5: five 3 10: ten 54: fifty-four 2: two 6: six 2 11:
 * eleven 55: fifty-five
 */

