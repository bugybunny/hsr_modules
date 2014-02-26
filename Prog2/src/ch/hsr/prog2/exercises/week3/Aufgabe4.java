/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-28 11:30:54 +0100 (Do, 28 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week3;

public class Aufgabe4 {

    public void solveHanoi(int n, String to, String from, String u) {
        if (n > 0) {
            solveHanoi(n - 1, u, from, to);
            moveIt(n, from, to);
            solveHanoi(n - 1, to, u, from);
        }
    }

    public void moveIt(int nr, String from, String to) {
        System.out.println("Move disc number " + nr + " from " + from + " to "
                + to);
    }

    public static void main(String args[]) {
        Aufgabe4 hanoi = new Aufgabe4();

        if (args.length != 1) {
            System.err.println("Error: a single integer argument needed");
            System.exit(1);
        }

        Integer N = Integer.valueOf(args[0]);
        hanoi.solveHanoi(N.intValue(), "Sink", "Source", "Workplace");
    }

}

/*
 * Session-Log:
 * 
 * $ java uebung03.ml.aufgabe4.TowerOfHanoi 2 Move disc number 1 from source to
 * workplace Move disc number 2 from source to sink Move disc number 1 from
 * workplace to sink
 */

