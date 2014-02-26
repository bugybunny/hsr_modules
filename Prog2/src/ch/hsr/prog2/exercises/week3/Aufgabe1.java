package ch.hsr.prog2.exercises.week3;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Aufgabe1 {
    static int           result;
    static StringBuilder foo = new StringBuilder();

    public static final int sumSquare(int n) {
        if (n < 0) {
            return result;
        } else {
            result = result + pow(2, n) + sumSquare(--n);
        }
        return result;
    }

    public static int pow(int d, int i) {
        if (i == 0) {
            return 1;
        }
        int v = d;
        for (int j = 1; j < i; j++) {
            v *= d;
        }
        return v;
    }

    public static final int sumSquare2(int n) {
        if (n >= 0) {
            foo.append("1");
            sumSquare2(--n);
        }
        return Integer.parseInt(foo.toString(), 2);
    }

    public static void main(String[] args) {
        System.out.println(sumSquare2(3));
        System.out.println(sumSquare(3));
    }
}
