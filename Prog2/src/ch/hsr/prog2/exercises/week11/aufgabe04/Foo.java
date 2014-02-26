package ch.hsr.prog2.exercises.week11.aufgabe04;

public class Foo {

    private static double Power(double x, int n) {
        double y;
        if (n == 0) {
            return 1;
        }
        if (n % 2 != 0) {
            y = Power(x, (n - 1) / 2);
            return x * y * y;
        } else {
            y = Power(x, n / 2);
            return y * y;
        }
    }

    public static void main(String[] args) {
        int sum = 0;
        for (int i = 1; i <= 100; i++) {
            sum += i;
        }
        System.out.println(sum);
    }
}
