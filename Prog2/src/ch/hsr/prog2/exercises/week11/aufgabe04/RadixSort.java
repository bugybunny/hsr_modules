/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu May  2 11:09:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week11.aufgabe04;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * A Radix-Sort which uses internally a Bucket-Sort to sort a list of arrays of
 * strings.
 * 
 * @author mbuehlma
 */
public class RadixSort {

    // buckets used for bucket sort
    private final LinkedList<String>[] buckets;

    @SuppressWarnings("unchecked")
    RadixSort() {
        // create LinkedList for buckets
        buckets = (LinkedList<String>[]) new LinkedList<?>[1 + ('z' - 'a' + 1)];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<String>();
        }
    }

    public void radixSort(String[] data) {
        LinkedList<String>[] bucket = new LinkedList[27];
        for (int i = 0; i < bucket.length; i++) {
            bucket[i] = new LinkedList<>();
        }

        int maxLength = -1;
        for (int i = 0; i < data.length; i++) {
            if (data[i].length() > maxLength) {
                maxLength = data[i].length();
            }
        }

        for (int i = maxLength - 1; i >= 0; i--) {
            // store them in buckets
            for (String s : data) {
                int charAtIndex = i - (maxLength - s.length());
                if (charAtIndex >= 0) {
                    bucket[s.charAt(charAtIndex) - 'a' + 1].addLast(s);
                } else {
                    bucket[0].addLast(s);
                }
            }

            // then copy the values back into the data array (ordered)
            int counter = 0;
            for (LinkedList<String> bucketList : bucket) {
                while (!bucketList.isEmpty()) {
                    data[counter++] = bucketList.removeFirst();
                }
            }
            System.out.println(Arrays.toString(data));
        }
    }

    public static void main(String[] args) {

        // unsorted data
        final String[] data = new String[] { "bruno", "brach", "auto", "auto",
                "autonom", "clown", "bismarck", "autark", "authentisch",
                "authentische", "autobahn", "bleibe", "clan" };

        new RadixSort().radixSort(data);
        System.out.println(Arrays.toString(data));

        // verification array, for test purpose only
        final String[] verification;
        // sort the verification array
        verification = data.clone();
        Arrays.sort(verification);

        // print sorted and verify output:
        for (int i = 0; i < verification.length; i++) {
            if (verification[i].equals(data[i])) {
                System.out.println(data[i]);
            } else {
                System.err.println("test failed: " + data[i]);
                break;
            }
        }
    }

}

/*
 * Session-Log:
 * 
 * autark authentisch authentische auto auto autobahn autonom bismarck bleibe
 * brach bruno clan clown
 */

