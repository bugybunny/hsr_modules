package ch.hsr.prog2.exercises.week3;

import java.util.Stack;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Aufgabe6 {
    private static final int[] spans1(int[] aHeightArray) {
        int[] spanArray = new int[aHeightArray.length];
        for (int i = 0; i < aHeightArray.length; i++) {
            int s = 1;
            while (s <= i && aHeightArray[i - s] <= aHeightArray[i]) {
                s++;
            }
            spanArray[i] = s;
        }
        return spanArray;
    }

    private static final int[] spans2(int[] aHeightArray) {
        int[] spanArray = new int[aHeightArray.length];
        Stack<Integer> spanHeightStack = new Stack<Integer>();

        for (int i = 0; i < aHeightArray.length; i++) {
            while (!spanHeightStack.isEmpty()
                    && aHeightArray[spanHeightStack.peek().intValue()] <= aHeightArray[i]) {
                spanHeightStack.pop();
            }
            if (spanHeightStack.isEmpty()) {
                spanArray[i] = i + 1;
            } else {
                spanArray[i] = i - spanHeightStack.peek().intValue();
            }
            spanHeightStack.push(Integer.valueOf(i));
        }

        return spanArray;
    }

    public static void main(String[] args) {
        int[] heights = new int[5];
        heights[0] = 6;
        heights[1] = 3;
        heights[2] = 4;
        heights[3] = 5;
        heights[4] = 2;

        for (int i : spans1(heights)) {
            System.out.println(i);
        }

        for (int i : spans2(heights)) {
            System.out.println(i);
        }
    }
}
