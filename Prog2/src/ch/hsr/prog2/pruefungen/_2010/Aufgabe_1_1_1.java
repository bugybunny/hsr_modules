package ch.hsr.prog2.pruefungen._2010;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Aufgabe_1_1_1 {

    public static void main(String[] args) {
        int x[] = new int[5];
        int[] y = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        x = y;
        x[8] = 88;
        System.out.println(x[8]);

    }
}
