/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu May  2 11:09:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week11.aufgabe03;

import java.awt.Point;
import java.util.Comparator;

/**
 * InPlaceQuickSort from "Data Structures and Algorithms" code fragment 11.6
 * implemented to use a comparator instead. This allows the usage of multiple
 * keys.
 * 
 * @author tbeeler
 */
public class QuickSort {

    /**
     * @param <T>
     *            Type of elements to be sorted.
     * @param sequence
     *            The sequence to be sorted.
     * @param comp
     *            The comperator to be used.
     * @param a
     *            The leftbound of the part that shall be sorted.
     * @param b
     *            The rightbound of the part that shall be sorted.
     */
    public <T> void inPlaceQuickSort(T[] sequence, Comparator<T> comp, int a,
            int b) {

    }

    public static void main(String[] args) {
        Comparator<Point> comp = new PointComparator();
        QuickSort qs = new QuickSort();
        int nSequence = 50;
        if (args.length > 0) {
            nSequence = Integer.parseInt(args[0]);
        }
        Point s1[] = new Point[nSequence];
        for (int i = 0; i < nSequence; i++) {
            s1[i] = new Point((int) (Math.random() * 100),
                    (int) (Math.random() * 100));
        }
        if (nSequence > 300) {
            System.out.println("Too many elements, not printing to stdout.");
        } else {
            for (int i = 0; i < nSequence; i++) {
                System.out.print("(" + s1[i].x + "/" + s1[i].y + "), ");
            }
            System.out.println();
        }
        System.out.print("Quick sort...");
        long then = System.currentTimeMillis();
        qs.inPlaceQuickSort(s1, comp, 0, nSequence - 1);
        long now = System.currentTimeMillis();
        long d1 = now - then;
        System.out.println("done.");
        if (nSequence > 300) {
            System.out.println("Too many elements, not printing to stdout.");
        } else {
            for (int i = 0; i < nSequence; i++) {
                System.out.print("(" + s1[i].x + "/" + s1[i].y + "), ");
            }
            System.out.println();
        }
        System.out.println("Time quick sort [ms]: " + d1);
    }
}

class PointComparator implements Comparator<Point> {

    /**
     * Total order relation for points: p1 > p2 | p1.x > p2.x p1 > p2 | p1.x =
     * p2.x && p1.y > p2.y p1 = p2 | p1.x = p2.x && p1.y = p2.y else p1 < p2
     * 
     * @return p1 > p2 : +1, p1 == p2 : 0, p1 < p2 : -1
     * 
     * @author tbeeler
     */
    @Override
    public int compare(Point a, Point b) {
        // TODO Implement here...
        return 0;
    }
}

/*
 * Session-Log:
 * 
 * (91/39), (48/23), (3/69), (38/2), (29/21), (74/3), (2/42), (28/61), (59/65),
 * (94/44), (10/16), (56/28), (3/78), (20/2), (59/19), (68/43), (79/37),
 * (32/34), (39/97), (82/24), (57/68), (90/75), (52/38), (74/17), (9/30),
 * (1/88), (76/90), (25/82), (66/13), (27/52), (49/49), (62/67), (79/75),
 * (30/8), (6/83), (63/27), (75/66), (77/74), (17/21), (7/84), (9/9), (71/9),
 * (34/27), (94/51), (88/41), (68/83), (24/40), (67/58), (12/39), (92/69), Quick
 * sort...done. (1/88), (2/42), (3/69), (3/78), (6/83), (7/84), (9/9), (9/30),
 * (10/16), (12/39), (17/21), (20/2), (24/40), (25/82), (27/52), (28/61),
 * (29/21), (30/8), (32/34), (34/27), (38/2), (39/97), (48/23), (49/49),
 * (52/38), (56/28), (57/68), (59/19), (59/65), (62/67), (63/27), (66/13),
 * (67/58), (68/43), (68/83), (71/9), (74/3), (74/17), (75/66), (76/90),
 * (77/74), (79/37), (79/75), (82/24), (88/41), (90/75), (91/39), (92/69),
 * (94/44), (94/51), Time quick sort [ms]: 0
 */

