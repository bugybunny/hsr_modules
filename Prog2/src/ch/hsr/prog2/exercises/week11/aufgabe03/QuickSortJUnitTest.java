/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu May  2 11:09:45 CEST 2013
 */

package ch.hsr.prog2.exercises.week11.aufgabe03;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Point;
import java.util.Comparator;

import org.junit.Test;

public class QuickSortJUnitTest {

    @Test
    public void testQuickSort() {

        // Test-Points(x*,y*):
        int[] x = { 7, 5, 5, 1, 5, 3 };
        int[] y = { 7, 6, 5, 9, 4, 3 };
        // Sorted:
        int[] xSorted = { 1, 3, 5, 5, 5, 7 };
        int[] ySorted = { 9, 3, 4, 5, 6, 7 };

        QuickSort qs = new QuickSort();
        Comparator<Point> comp = new PointComparator();
        Point[] sequence = new Point[x.length];
        for (int i = 0; i < x.length; i++) {
            sequence[i] = new Point(x[i], y[i]);
        }
        Point[] sequenceSorted = new Point[ySorted.length];
        for (int i = 0; i < xSorted.length; i++) {
            sequenceSorted[i] = new Point(xSorted[i], ySorted[i]);
        }

        qs.inPlaceQuickSort(sequence, comp, 0, sequence.length - 1);

        for (int i = 0; i < sequenceSorted.length; i++) {
            assertEquals(sequenceSorted[i], sequence[i]);
        }
    }

    @Test
    public void stressTestQuickSort() {

        final int LENGTH = 100000;

        QuickSort qs = new QuickSort();
        Comparator<Point> comp = new PointComparator();
        Point[] sequence = new Point[LENGTH];
        for (int i = 0; i < sequence.length; i++) {
            sequence[i] = new Point((int) (Math.random() * 101),
                    (int) (Math.random() * 101));
        }

        qs.inPlaceQuickSort(sequence, comp, 0, sequence.length - 1);

        Point point = sequence[0];
        double xBefore = point.getX();
        double yBefore = point.getY();
        for (int i = 1; i < sequence.length; i++) {
            point = sequence[i];
            double xActual = point.getX();
            double yActual = point.getY();
            assertTrue(xBefore <= xActual);
            if (xBefore == xActual) {
                assertTrue(yBefore <= yActual);
            }
            xBefore = xActual;
            yBefore = yActual;
        }

    }

}
