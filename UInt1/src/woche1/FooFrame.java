package woche1;

/**
 * @author msyfrig
 */
public class FooFrame {

    public static void main(String[] args) {
        double sum = 0.1;
        for (int i = 0; i < 42; i++) {
            sum += sum * 2;
        }
        System.out.println(sum);
    }
}
