/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:21:28 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe1;

import java.util.Arrays;
import java.util.Random;

public class InsertionSort {

    private int[] a;
    private int   n;

    public int[] sort(int[] a0) {
        a = a0;
        n = a.length;
        return insertionsort();
    }

    private int[] insertionsort() {
        int i, j, t;
        for (i = 1; i < n; i++) {
            for (j = i; j > 0; j--) {
                if (a[j] < a[j - 1]) {
                    // t = a[j];
                    // a[j] = a[j - 1];
                    // a[j - 1] = t;
                    a[j - 1] = a[j - 1] ^ a[j];
                    a[j] = a[j - 1] ^ a[j];
                    a[j - 1] = a[j - 1] ^ a[j];
                } else {
                    break;
                }
            }
        }
        return a;
    }

    public static void main(String[] leer) {
        InsertionSort is;
        // int[] a = { 1, 3, 2, 9, 8, 7 };
        int[] a = { 5, 3 };

        System.out.println("Unsorted array:");
        for (int i = 0; i < a.length; i++) {
            System.out.print("\t" + a[i]);
        }

        is = new InsertionSort();
        a = is.sort(a);
        System.out.println("\nSorted array:");
        for (int i = 0; i < a.length; i++) {
            System.out.print("\t" + a[i]);
        }
        System.out.println();

        stressTest(is);
    }

    private static void stressTest(InsertionSort is) {
        System.out.print("\nStress-Test: ... ");
        final int NUMBER_OF_TESTS = 100000;
        final int LENGTH_RANGE = 5;
        final int DATA_RANGE = 10;
        Random random = new Random(1);
        for (int testNr = 0; testNr < NUMBER_OF_TESTS; testNr++) {
            int length = (int) (random.nextDouble() * LENGTH_RANGE + 1);
            int[] array = new int[length];
            for (int i = 0; i < length; i++) {
                int number = (int) (random.nextDouble() * DATA_RANGE + 1);
                array[i] = number;
            }
            int[] backup = Arrays.copyOf(array, array.length);
            array = is.sort(array);
            Arrays.sort(backup);
            if (!Arrays.equals(array, backup)) {
                System.out.println("\nERROR: Array wrong sorted!");
                for (int i = 0; i < array.length; i++) {
                    System.out.print("\t" + array[i]);
                }
                System.exit(1);
            }
        }
        System.out.println("o.k.");
    }

}

/*
 * Session-Log (SOLL):
 * 
 * $ java -Xint -Xms2m -Xmx2m uebung06.ml.aufgabe01.InsertionSort
 * 
 * Unsorted array: 1 3 2 9 8 7 Sorted array: 1 2 3 7 8 9
 * 
 * Stress-Test: ... o.k.
 */

