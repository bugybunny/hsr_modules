package ch.hsr.prog2.exercises.week1;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-16 10:02:41 +0100 (Sa, 16 Feb 2013) $
 */
public class ImprovedTextAnalyse {

    public static String s = "Das Studium an der HSR kann manchmal nerven, speziell beim Programmieren!";

    public static void main(String[] args) {
        char[] characs = { 'a', 'o', 'i', 'e', 'u' };
        s = s.toLowerCase();

        Map<Character, AtomicInteger> countingMap = new HashMap<>(
                characs.length);
        for (char c : characs) {
            countingMap.put(Character.valueOf(c), new AtomicInteger());
        }

        for (int i = 0; i < s.length(); i++) {
            char charToCheck = s.charAt(i);

            AtomicInteger count = countingMap.get(Character
                    .valueOf(charToCheck));
            if (count != null) {
                count.incrementAndGet();
            }
        }

        for (Map.Entry<Character, AtomicInteger> entries : countingMap
                .entrySet()) {
            System.out.println("Output: " + entries.getKey() + " = "
                    + entries.getValue());
        }
    }
}
