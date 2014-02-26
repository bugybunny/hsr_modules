/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu May  2 11:09:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week11.aufgabe04;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

public class RadixSortJUnitTest {

    @Test
    public void testRadixSort() {

        final int LOOPS = 100;
        final int MIN_STRING_LEN = 1;
        final int MAX_STRING_LEN = 10;
        final int ARRAY_LEN = 100;

        for (int loop = 0; loop < LOOPS; loop++) {
            String[] strArr = new String[ARRAY_LEN];
            for (int i = 0; i < ARRAY_LEN; i++) {
                int len = random(MIN_STRING_LEN, MAX_STRING_LEN);
                char[] charArr = new char[MAX_STRING_LEN];
                for (int j = 0; j < len; j++) {
                    charArr[j] = (char) random('a', 'z');
                }
                String str = new String(charArr, 0, len);
                strArr[i] = str;
            }
            String[] strArrSored = strArr.clone();
            Arrays.sort(strArrSored);
            new RadixSort().radixSort(strArr);
            for (int i = 0; i < ARRAY_LEN; i++) {
                assertEquals(strArrSored[i], strArr[i]);
            }
        }
    }

    /**
     * Returns a random-number in the range from..to.
     * 
     * @param from
     *            The Lower-Bound (inclusive).
     * @param to
     *            The Upper-Bound (inclusive).
     * @return The generated random-number.
     */
    private int random(int from, int to) {
        return from + (int) (Math.random() * (to - from + 1));
    }

}
