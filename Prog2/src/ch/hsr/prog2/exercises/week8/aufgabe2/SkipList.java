/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 11 11:27:57 CEST 2013
 */

package ch.hsr.prog2.exercises.week8.aufgabe2;

import java.util.Random;

public class SkipList<K extends Comparable<? super K>, V> {

    protected int               size;        // elements in list (without tail
                                              // and header)

    protected final int         MAX_LEVEL;   // maximum allowed height of list

    protected int               highestLevel; // maximum current height of list

    protected ListElement<K, V> header;

    protected ListElement<K, V> tail;

    // don't ever directly compare to this one with equals(), only with ==
    protected K                 minKey;

    // don't ever directly compare to this one with equals(), only with ==
    protected K                 maxKey;

    private Random              random;

    public SkipList() {
        this(4);

        // element 11 with height 2
        ListElement<K, V> le11 = new ListElement<K, V>(2,
                (K) Integer.valueOf(11), (V) "11");
        ListElement<K, V> le3 = new ListElement<K, V>(3,
                (K) Integer.valueOf(3), (V) "3");
        ListElement<K, V> le4 = new ListElement<K, V>(0,
                (K) Integer.valueOf(4), (V) "4");
        ListElement<K, V> le7 = new ListElement<K, V>(1,
                (K) Integer.valueOf(7), (V) "7");
        ListElement<K, V> le9 = new ListElement<K, V>(2,
                (K) Integer.valueOf(9), (V) "9");
        ListElement<K, V> le13 = new ListElement<K, V>(0,
                (K) Integer.valueOf(13), (V) "13");
        ListElement<K, V> le34 = new ListElement<K, V>(3,
                (K) Integer.valueOf(34), (V) "34");

        // link them
        header.forward[0] = header.forward[1] = header.forward[2] = header.forward[3] = le3;
        le3.forward[3] = le34;
        le3.forward[2] = le9;
        le3.forward[1] = le7;
        le3.forward[0] = le4;

        le4.forward[0] = le7;

        le7.forward[1] = le7.forward[0] = le9;

        le9.forward[2] = le9.forward[1] = le9.forward[0] = le11;

        le11.forward[2] = le11.forward[1] = le34;
        le11.forward[0] = le13;

        le13.forward[0] = le34;

        le34.forward[3] = le34.forward[2] = le34.forward[1] = le34.forward[0] = tail;

        size = 7;
        highestLevel = 3;
    }

    @SuppressWarnings("unchecked")
    public SkipList(int maxHeight) {
        minKey = (K) new Integer(0);
        maxKey = (K) new Integer(0);
        MAX_LEVEL = maxHeight;
        header = new ListElement<K, V>(MAX_LEVEL, minKey, null);
        tail = new ListElement<K, V>(0, maxKey, null);

        for (int i = 0; i <= MAX_LEVEL; i++) {
            header.forward[i] = tail;
        }
        size = 0;
        random = new Random(4); // always the same sequence
    }

    private boolean isSmaller(K key1, K key2) {
        if (key1 == minKey) {
            return true;
        }
        if (key2 == maxKey) {
            return true;
        }
        if (key1 == maxKey) {
            return false;
        }
        if (key2 == minKey) {
            return false;
        }
        return key1.compareTo(key2) < 0;
    }

    private int getRandomLevel() {
        int level = 0;
        while (random.nextBoolean() && level < MAX_LEVEL) {
            level++;
        }
        System.out.println(level);
        return level;
    }

    public V get(K aKey) {
        ListElement<K, V> actElement = header;

        for (int i = highestLevel; i >= 0; i--) {
            while (actElement.forward[i].getKey().compareTo(aKey) > 0) {
                actElement = actElement.forward[i];
            }
        }
        actElement = actElement.forward[0];
        if (actElement.getKey().equals(aKey) && actElement != tail) {
            return actElement.getValue();
        } else {
            return null;
        }
    }

    public V put(K aKey, V aValue) {
        // first check if an element with this key is already in the skiplist
        // if so, replace the value and return the old one
        ListElement<K, V> actElement = header;
        ListElement<K, V>[] update = new ListElement[MAX_LEVEL];
        for (int i = highestLevel; i >= 0; i--) {
            while (isSmaller(actElement.forward[i].getKey(), aKey)) {
                System.out.println(actElement.forward[i].getKey()
                        + " is smaller than " + aKey + " on level " + i);
                actElement = actElement.forward[i];
            }
            update[i] = actElement;
        }
        actElement = actElement.forward[0];
        if (actElement.getKey().equals(aKey) && actElement != tail) {
            V oldValue = actElement.getValue();
            actElement.value = aValue;

            return oldValue;
        }

        // if not add it and link all collected values in the update array new
        int randomLevel = getRandomLevel();
        ListElement<K, V> elementToInsert = new ListElement<K, V>(randomLevel,
                aKey, aValue);

        for (int i = 0; i < randomLevel; i++) {
            elementToInsert.forward[i] = update[i].forward[i];
            update[i].forward[i] = elementToInsert;
        }

        size++;
        return null;
    }

    public V remove(K key) {
        ListElement<K, V>[] update = ListElement.createArray(MAX_LEVEL + 1);
        ListElement<K, V> aktElement = header;
        V oldValue = null;

        for (int i = highestLevel; i >= 0; i--) {
            while (isSmaller(aktElement.forward[i].key, key)) {
                aktElement = aktElement.forward[i];
            }
            update[i] = aktElement;
        }
        aktElement = aktElement.forward[0];

        if ((aktElement != tail) && aktElement.key.equals(key)) {
            oldValue = aktElement.value;
            size--;
            for (int i = 0; i <= highestLevel; i++) {
                if (update[i].forward[i] != aktElement) {
                    break;
                }
                update[i].forward[i] = aktElement.forward[i];
            }
        }

        while (highestLevel > 1 && header.forward[highestLevel].key == maxKey) {
            highestLevel--;
        }
        return oldValue;
    }

    public int size() {
        return size;
    }

    public void print() {
        System.out.println("-------------------------------------------");
        System.out.println("Size: " + size);
        ListElement<K, V> aktElement = header;
        Object[][] objMatrix = new Object[MAX_LEVEL + 1][size];
        aktElement = header.forward[0];
        int indx = 0;
        while (aktElement.key != maxKey) {
            for (int i = 0; i <= aktElement.level(); i++) {
                objMatrix[i][indx] = aktElement.key;
            }
            aktElement = aktElement.forward[0];
            indx++;
        }
        System.out.println("Highest-Level: " + highestLevel);
        System.out.println();
        for (int n = highestLevel; n >= 0; n--) {
            for (int i = 0; i < size; i++) {
                if (objMatrix[n][i] != null) {
                    System.out.print(objMatrix[n][i].toString());
                }
                System.out.print("\t");
            }
            System.out.println();
        }
        System.out.println("-------------------------------------------");
    }

    public ListElement<K, V> getHeader() {
        return header;
    }

    public K getMaxKey() {
        return maxKey;
    }

    public static class ListElement<K extends Comparable<? super K>, V> {

        private K                   key;
        private V                   value;

        private ListElement<K, V>[] forward;

        public ListElement() {
        }

        public ListElement(int level, K key, V value) {
            this.key = key;
            this.value = value;
            forward = createArray(level + 1);
        }

        @SuppressWarnings("unchecked")
        static <K extends Comparable<? super K>, V> ListElement<K, V>[] createArray(
                int length) {
            return new ListElement[length];
        }

        public int level() {
            return forward.length - 1;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public ListElement<K, V>[] getForward() {
            return forward;
        }

    } // end of ListElement

    public static void main(String[] args) {
        SkipList<Integer, String> s = new SkipList<Integer, String>();
        s.print();

        s.put(Integer.valueOf(12), "12");
        s.print();
        s.put(Integer.valueOf(1), "1");
        s.print();
        s.put(Integer.valueOf(8), "8");
        s.print();
    }
}

/*
 * Session-Log (SOLL):
 * 
 * $ java -classpath Uebungen uebung08.as.aufgabe02.SkipList
 * ------------------------------------------- Size: 7 Highest-Level: 3
 * 
 * 3 34 3 9 11 34 3 7 9 11 34 3 4 7 9 11 13 34
 * -------------------------------------------
 * ------------------------------------------- Size: 10 Highest-Level: 4
 * 
 * 12 3 12 34 3 9 11 12 34 3 7 8 9 11 12 34 1 3 4 7 8 9 11 12 13 34
 * -------------------------------------------
 */

