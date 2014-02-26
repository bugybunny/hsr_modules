package ch.hsr.prog2.exercises.week7.aufgabe2;

import java.util.Arrays;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class HashTableCollission {
    public static void main(String[] args) {
        String s = "12,44,13,88,23,94,11,39,20,16,5";

        List<String> fillValues = Arrays.asList(s.split(","));
        for (String tempString : fillValues) {
            int i = Integer.valueOf(tempString).intValue();
            i = (2 * i + 5) % 11;
            System.out.println(tempString + ":" + i);

        }

    }

}