package ch.hsr.prog2.exercises.week2.aufgabe7;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class PairAlgorithm {

    public static void main(String[] args) {
        List<Integer> numberList = new ArrayList<>();

        numberList.add(Integer.valueOf(27));
        numberList.add(Integer.valueOf(83));
        numberList.add(Integer.valueOf(7));
        numberList.add(Integer.valueOf(55));
        numberList.add(Integer.valueOf(99));
        numberList.add(Integer.valueOf(10));
        numberList.add(Integer.valueOf(4));
        numberList.add(Integer.valueOf(38));
        numberList.add(Integer.valueOf(14));
        numberList.add(Integer.valueOf(13));

        int sum = 0;

        for (int i = 0; i < numberList.size(); i++) {
            for (int j = i; j < numberList.size(); j++) {
                sum += numberList.get(i).intValue()
                        + numberList.get(j).intValue();
            }
        }
        System.out.println(sum);
    }
}
