package ch.hsr.prog2.exercises.week3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public final class Aufgabe2 {
    private static final List<String> stringList = new ArrayList<>(5);
    static {
        stringList.add("Alge");
        stringList.add("Ding");
        stringList.add("Lang");
        stringList.add("Politik");
        stringList.add("Spiel");
        stringList.add("Text");
        stringList.add("Welt");
        stringList.add("Zimmer");

        Collections.sort(stringList);
    }

    private static final int searchString(String aString, int aStartIndex,
            int anEndIndex) {
        if (aStartIndex == anEndIndex) {
            return 0;
        }

        int midIndex = (anEndIndex + aStartIndex) / 2;
        String middle = stringList.get(midIndex);

        int compareValue = aString.compareTo(middle);

        if (compareValue < 0) {
            return searchString(aString, 0, midIndex - 1);
        } else if (compareValue == 0) {
            return midIndex + 1;
        } else {
            return searchString(aString, midIndex + 1, anEndIndex);
        }
    }

    public static void main(String[] args) {
        System.out.println(searchString("Welt", 0, stringList.size()));
    }
}
