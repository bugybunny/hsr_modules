/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Mar 21 16:34:08 CET 2013
 */

package ch.hsr.prog2.exercises.week6.aufgabe3;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.prog2.exercises.week6.aufgabe2.Entry;

public class Frequency {

    public List<Entry<Long, Character>> countFrequency(String text) {
        List<Entry<Long, Character>> frequencyList = new ArrayList<>(
                text.length() / 2 * 3);
        while (text.length() > 0) {
            char nextChar = text.charAt(0);
            long oldCount = text.length();
            text = text.replaceAll(nextChar + "", "");
            long count = oldCount - text.length();
            frequencyList.add(new Entry<Long, Character>(Long.valueOf(count),
                    Character.valueOf(nextChar)));
        }
        return frequencyList;
    }

    public static void main(String[] args) {
        Frequency freq = new Frequency();
        List<Entry<Long, Character>> list = freq
                .countFrequency("today's sunny weather - go out, have fun!");
        for (int i = 0; i < list.size(); i++) {
            Entry<Long, Character> entry = list.get(i);
            System.out.println("Element '" + entry.getValue()
                    + "' has an occurence of " + entry.getKey());
        }
    }

}

/*
 * Session-Log (SOLL):
 * 
 * Element 't' has an occurence of 3 Element 'o' has an occurence of 3 Element
 * 'd' has an occurence of 1 Element 'a' has an occurence of 3 Element 'y' has
 * an occurence of 2 Element ''' has an occurence of 1 Element 's' has an
 * occurence of 2 Element ' ' has an occurence of 7 Element 'u' has an occurence
 * of 3 Element 'n' has an occurence of 3 Element 'w' has an occurence of 1
 * Element 'e' has an occurence of 3 Element 'h' has an occurence of 2 Element
 * 'r' has an occurence of 1 Element '-' has an occurence of 1 Element 'g' has
 * an occurence of 1 Element ',' has an occurence of 1 Element 'v' has an
 * occurence of 1 Element 'f' has an occurence of 1 Element '!' has an occurence
 * of 1
 */

