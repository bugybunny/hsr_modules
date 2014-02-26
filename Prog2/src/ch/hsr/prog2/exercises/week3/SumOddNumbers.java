package ch.hsr.prog2.exercises.week3;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class SumOddNumbers {

    public int perLoop(int n) {
        int sum = 0;
        for (int i = 1; i <= n; i += 2) {
            sum += i;
        }

        return sum;
    }

    public int perRecursion(int n) {
        if (n % 2 == 0) {
            n--;
        }
        if (n <= 1) {
            return n;
        } else {
            return n + perRecursion(n - 2);
        }
    }

    public int recursion(int n) {
        if (n >= 2) {
            if (n % 2 != 0) {
                return n + recursion(n - 2);
            } else {
                return recursion(n - 1);
            }
        } else {
            return n;
        }
    }

    /**
     * Creates a new instance of this class.
     * 
     */
    public SumOddNumbers() {
        // TODO Auto-generated constructor stub
    }

    public static void main(String[] args) {
        int n = 10000;

        long start = System.nanoTime();
        // new SumOddNumbers().perRecursion(n);
        // System.out.println(System.nanoTime() - start);
        // start = System.nanoTime();

        new SumOddNumbers().recursion(n);
        System.out.println(System.nanoTime() - start);
        start = System.nanoTime();
        //
        // new SumOddNumbers().perLoop(n);
        // System.out.println(System.nanoTime() - start);
        // start = System.nanoTime();
    }
}
