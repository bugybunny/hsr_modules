package ch.hsr.prog2.exercises.week1;

/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-16 10:02:41 +0100 (Sa, 16 Feb 2013) $
 */
public class TextAnalyse {

    public static String s = "Test string to exercise....";

    public static void main(String[] args) {
        char[] characs = { 'a', 'o', 'i', 'e', 'u' };

        s = s.toLowerCase();

        for (int i = 0; i < characs.length; i++) {
            System.out.println("Output: " + characs[i] + " = "
                    + doIt(characs[i], s.length()));
        }
    }

    public static int doIt(char v, int len) {
        if (len > 1) {
            if (s.charAt(len - 1) == v) {
                return doIt(v, len - 1) + 1;
            } else {
                return doIt(v, len - 1);
            }
        } else {
            if (s.charAt(len - 1) == v) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}
