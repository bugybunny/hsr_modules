package ch.hsr.prog2.exercises.week1;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class ArithmeticSequence {

    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {

        }
        int start = 5;

        getIterative(start);
    }

    private static int getRecursive(int n) {
        if (n == 1) {
            return 5;
        }
        return getRecursive(n - 1 + 8);
    }

    private static void getIterative(int n) {
        for (int i = 0; i < 5; i++) {
            System.out.println(n + (i * 8));
        }
    }

    private static int getExplicit(int n, int start) {
        return start + (n * 8);
    }
}
