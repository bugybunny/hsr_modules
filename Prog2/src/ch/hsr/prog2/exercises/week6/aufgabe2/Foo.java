package ch.hsr.prog2.exercises.week6.aufgabe2;

public class Foo {

    private static boolean foo() {
        System.out.println("foo");
        return true;
    }

    private static boolean miau() {
        System.out.println("miau");
        return false;
    }

    private static void foo2() {
        int i, j, t;
        int[] b = { 5, 3 };
        int length = b.length;
        for (i = 1; i < length; i++) {
            t = b[i];
            j = i;
            while (j > 0 & b[j - 1] > t) {
                j = j - 1;
            }

        }
    }

    public static void main(String[] args) {
        Foo.foo2();
        // System.out.println(Foo.miau() & Foo.foo());

    }

}
