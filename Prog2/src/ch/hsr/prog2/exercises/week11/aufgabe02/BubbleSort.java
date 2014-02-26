/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu May  2 11:09:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week11.aufgabe02;


/**
 * @author tbeeler
 * 
 *         BubbleSort. Two versions of the bubblesort for sorting integers.
 * 
 */

public class BubbleSort {

    /**
     * First version: no optimization.
     * 
     * @param <T>
     *            Type of elements to be sorted. Must be comparable.
     * @param sequence
     *            The sequence to be sorted.
     */
    public static <T extends Comparable<? super T>> void bubbleSort1(
            T[] sequence) {
        boolean sorted = true;
        do {
            sorted = true;
            for (int i = 0; i < sequence.length - 1; i++) {
                if (sequence[i].compareTo(sequence[i + 1]) > 0) {
                    sorted = false;
                    T temp = sequence[i];
                    sequence[i] = sequence[i + 1];
                    sequence[i + 1] = temp;
                }
            }
        }
        while (!sorted);
    }

    /**
     * Second version with slight optimization: The upper boundary is reduced by
     * one in every iteration (the biggest bubble is on top now).
     * 
     * @param <T>
     *            Type of elements to be sorted. Must be comparable.
     * @param sequence
     *            The sequence to be sorted.
     */
    public static <T extends Comparable<? super T>> void bubbleSort2(
            T[] sequence) {
        boolean sorted = true;
        do {
            sorted = true;
            int upperBound = sequence.length - 1;
            for (int i = 0; i < upperBound; i++) {
                if (sequence[i].compareTo(sequence[i + 1]) > 0) {
                    sorted = false;
                    T temp = sequence[i];
                    sequence[i] = sequence[i + 1];
                    sequence[i + 1] = temp;
                }
            }
            upperBound--;
        }
        while (!sorted);
    }

    public static void main(String args[]) throws Exception {
        int nSequence = 200;
        if (args.length > 0) {
            nSequence = Integer.parseInt(args[0]);
        }
        Integer s1[] = new Integer[nSequence];
        Integer s2[] = new Integer[nSequence];
        for (int i = 0; i < nSequence; i++) {
            s1[i] = s2[i] = (int) (Math.random() * 100);
        }
        if (nSequence > 300) {
            System.out.println("Too many elements, not printing to stdout.");
        } else {
            for (int i = 0; i < nSequence; i++) {
                System.out.print(s1[i] + ",");
            }
            System.out.println();
        }
        System.out.print("Bubble sort 1...");
        long then = System.nanoTime();
        bubbleSort1(s1);
        ;
        long now = System.nanoTime();
        long d1 = now - then;
        System.out.println("done.");
        System.out.print("Bubble sort 2...");
        then = System.nanoTime();
        bubbleSort2(s2);
        now = System.nanoTime();
        long d2 = now - then;
        System.out.println("done.");

        if (nSequence > 300) {
            System.out.println("Too many elements, not printing to stdout.");
        } else {
            for (int i = 0; i < nSequence; i++) {
                if (s1[i] != s2[i]) {
                    System.err.println("Sorting does not match!");
                    System.exit(1);
                }
                System.out.print(s2[i] + ",");
            }
            System.out.println();
        }
        System.out.format("Time bubble sort 1 [us]: %,9d\n", d1 / 1000);
        System.out.format("Time bubble sort 2 [us]: %,9d\n", d2 / 1000);
    }
}

/*
 * Session-Log:
 * 
 * 11,2,96,88,13,94,31,13,29,28,36,16,65,73,52,80,78,16,69,14,90,57,51,0,11,68,15
 * ,
 * 45,95,92,82,73,88,9,8,70,10,36,3,4,17,92,75,14,0,94,69,45,69,12,4,37,12,83,72
 * ,
 * 19,82,12,37,11,62,4,21,11,65,75,90,66,2,29,27,24,78,7,2,79,50,27,49,63,42,84,
 * 55
 * ,8,39,6,23,82,4,95,9,79,47,23,58,6,70,5,56,19,6,97,96,22,72,46,95,46,84,38,56
 * ,
 * 96,32,77,52,91,20,58,6,56,49,88,23,1,31,42,41,47,8,40,18,47,28,82,65,73,96,56
 * ,
 * 4,49,13,17,93,38,79,16,2,67,44,25,21,83,56,21,82,41,83,51,46,84,16,12,49,79,
 * 71 ,
 * 77,22,30,47,78,25,13,1,79,77,23,86,85,68,78,61,98,42,52,1,4,76,88,91,39,61,51
 * ,40,26,76,11,65,88,81,18, Bubble sort 1...done. Bubble sort 2...done.
 * 0,0,1,1,
 * 1,2,2,2,2,3,4,4,4,4,4,4,5,6,6,6,6,7,8,8,8,9,9,10,11,11,11,11,11,12,12,
 * 12,12,13
 * ,13,13,13,14,14,15,16,16,16,16,17,17,18,18,19,19,20,21,21,21,22,22,23,
 * 23,23,23
 * ,24,25,25,26,27,27,28,28,29,29,30,31,31,32,36,36,37,37,38,38,39,39,40,
 * 40,41,41
 * ,42,42,42,44,45,45,46,46,46,47,47,47,47,49,49,49,49,50,51,51,51,52,52,
 * 52,55,56
 * ,56,56,56,56,57,58,58,61,61,62,63,65,65,65,65,66,67,68,68,69,69,69,70,
 * 70,71,72
 * ,72,73,73,73,75,75,76,76,77,77,77,78,78,78,78,79,79,79,79,79,80,81,82,
 * 82,82,82
 * ,82,83,83,83,84,84,84,85,86,88,88,88,88,88,90,90,91,91,92,92,93,94,94,
 * 95,95,95,96,96,96,96,97,98, Time bubble sort 1 [us]: 5 Time bubble sort 2
 * [us]: 3
 */

