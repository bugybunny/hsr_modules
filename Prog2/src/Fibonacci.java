/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Fibonacci {

    static int i = 0;

    static int rekursiv(int n) {
        i++;
        if (n < 2) {
            return n;
        } else {
            return rekursiv(n - 1) + rekursiv(n - 2);
        }
    }

    /**
     * TODO COMMENT ME!
     * 
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(rekursiv(5));
        System.out.println(i);
    }

}
