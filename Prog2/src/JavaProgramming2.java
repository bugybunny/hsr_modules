class A {
    public A() {
        System.out.println("A.A()");
        meth();
    }

    public void meth() {
        System.out.println("A.meth()");
    }
}

class B extends A {
    public int attr = 1;

    public B() {
        System.out.println("B.B()");
        attr = 2;
    }

    @Override
    public void meth() {
        System.out.println("B.meth()");
        System.out.println("B.attr = " + attr);
    }
}

public class JavaProgramming2 {
    public static void main(String[] args) {
        B b = new B();
    }
}