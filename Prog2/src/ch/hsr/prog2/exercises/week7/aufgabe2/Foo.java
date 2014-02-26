package ch.hsr.prog2.exercises.week7.aufgabe2;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Foo {
    /**
     * TODO COMMENT ME!
     * 
     * @param args
     */
    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder("h1234");
        StringBuilder sb2 = new StringBuilder("h1234");
        sb.setLength(8);
        sb.append("iuaeiuea");
        System.out.println(sb);
        sb.setLength(8);

        sb.trimToSize();
        System.out.println(sb);
        sb2.trimToSize();
        System.out.println(sb.equals(sb2));

    }
}
