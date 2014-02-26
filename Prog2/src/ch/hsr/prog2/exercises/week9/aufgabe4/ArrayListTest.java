/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * Version: Thu Apr 18 14:19:46 CEST 2013
 */

package ch.hsr.prog2.exercises.week9.aufgabe4;

import java.util.ArrayList;

public class ArrayListTest {

    private ArrayList<Integer> arrayList;

    public void clear() {
        arrayList = new ArrayList<Integer>();
    }

    public void generateTree(int nodes) {
        int e;
        for (int i = 0; i < nodes; i++) {
            e = (int) (Math.random() * Integer.MAX_VALUE);
            if (arrayList.isEmpty()) {
                arrayList.add(Integer.valueOf(e));
            } else {
                add(0, arrayList.size() - 1, e);
            }
        }
    }

    public void add(int lower, int upper, int content) {
        int index = (lower + upper) / 2;
        if (content > arrayList.get(index).intValue()) {
            if (index + 1 >= arrayList.size()
                    || arrayList.get(index + 1).intValue() > content) {
                arrayList.add(index + 1, Integer.valueOf(content));
            } else {
                add(index + 1, upper, content);
            }
        } else {
            if (index == 0 || (arrayList.get(index - 1).intValue() < content)) {
                arrayList.add(index, Integer.valueOf(content));
            } else {
                add(lower, index - 1, content);
            }
        }
    }

    public void print() {
        System.out.print("{ ");
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.print(arrayList.get(i));
            if (i != arrayList.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("}");
    }

    public static void main(String[] args) {
        System.out.println("ARRAYLIST based TEST");
        System.out
                .println("Please be patient, the following operations may take some time...");
        final int TESTRUNS = 100;
        final int BEGINSIZE = 10000;
        final int VARYSIZE = 10;

        ArrayListTest arrayListTest = new ArrayListTest();
        double avgTime = 0;
        long startTime;
        for (int i = 0; i < TESTRUNS; i++) {
            arrayListTest.clear();
            startTime = System.currentTimeMillis();
            arrayListTest.generateTree(BEGINSIZE + i * VARYSIZE);
            avgTime = ((avgTime * i) + (System.currentTimeMillis() - startTime))
                    / (i + 1);
        }

        System.out.println("Test successful, result is as follows:");
        System.out.println("Average time for generation is: " + avgTime + "ms");
    }

}

/*
 * Session-Log:
 * 
 * ARRAYLIST based TEST Please be patient, the following operations may take
 * some time... Test successful, result is as follows: Average time for
 * generation is: 55.16ms
 */

