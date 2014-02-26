package ch.hsr.informatik.prog1.testat2;

import java.util.Iterator;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class ListPerformanceTests {

    private static int n = 50000;

    private ListPerformanceTests() {
    }

    public static void addToEnd(List<Integer> aList) {
        for (int i = 0; i < n; i++) {
            aList.add(Integer.valueOf(i));
        }
    }

    public static void addToStart(List<Integer> aList) {
        for (int i = 0; i < n; i++) {
            aList.add(0, Integer.valueOf(i));
        }
    }

    public static void getByIndex(List<Integer> aList) {
        for (int i = 0; i < aList.size(); i++) {
            aList.get(i);
        }
    }

    public static void getByIterator(List<Integer> aList) {
        Iterator<Integer> listIterator = aList.iterator();
        while (listIterator.hasNext()) {
            listIterator.next();
        }
    }

    public static void deleteByIndex(List<Integer> aList)
            throws NotEnoughElementsException {
        if (aList.size() < n) {
            throw new NotEnoughElementsException(
                    "there are less than "
                            + n
                            + " elements in this array. Please call #init and then this method again!");

        }

        else {
            for (int i = 0; i < 50000; i++) {
                aList.remove(0);
            }
        }
    }

    public static void deleteByIterator(List<Integer> aList)
            throws NotEnoughElementsException {
        if (aList.size() < n) {
            throw new NotEnoughElementsException(
                    "there are less than "
                            + n
                            + " elements in this array. Please call #init and then this method again!");

        }

        else {
            Iterator<Integer> iterator = aList.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                iterator.remove();
            }
        }
    }

    static void init(List<Integer> aList) {
        aList.clear();
        for (int i = 0; i < n; i++) {
            aList.add(Integer.valueOf(i));
        }
    }
}
