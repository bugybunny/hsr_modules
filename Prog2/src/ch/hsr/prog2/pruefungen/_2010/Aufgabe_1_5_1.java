package ch.hsr.prog2.pruefungen._2010;

import java.util.Stack;
import java.util.Vector;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Aufgabe_1_5_1 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();
        Vector<String> expr = new Vector<String>();

        expr.add("23");
        expr.add("21");
        expr.add("+");
        expr.add("4");
        expr.add("8");
        expr.add("*");
        expr.add("-");

        for (String s : expr) {
            try {
                int i = Integer.parseInt(s);
                stack.push(i);
            }
            catch (NumberFormatException anEx) {
                int n2 = stack.pop();
                int n1 = stack.pop();
                switch (s) {
                    case "+":
                        stack.push(n1 + n2);
                        break;
                    case "-":
                        stack.push(n1 - n2);
                        break;
                    case "*":
                        stack.push(n1 * n2);
                        break;
                }
            }
            System.out.println(stack);
        }
        System.out.println(stack.pop());

    }
}
