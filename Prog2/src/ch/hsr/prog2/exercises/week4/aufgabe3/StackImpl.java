/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-03-07 10:48:01 +0100 (Do, 07 Mrz 2013) $
 */

package ch.hsr.prog2.exercises.week4.aufgabe3;

import java.util.LinkedList;

public class StackImpl<T> implements Stack<T> {

    private LinkedList<T> list = new LinkedList<>();

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public T top() throws EmptyStackException {
        return list.getLast();
    }

    @Override
    public void push(T element) {
        list.addLast(element);
    }

    @Override
    public T pop() throws EmptyStackException {
        return list.removeLast();
    }

    @Override
    public void print() {
        if (list.size() == 0) {
            System.out.println("The stack is empty.");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.print(list.get(i));
            }
        }
    }

    public static void main(String[] args) {
        StackImpl<Integer> stack = new StackImpl<Integer>();
        for (int i = 0; i < 20; i++) {
            System.out.println("push(): " + i);
            stack.push(Integer.valueOf(i));
        }
        System.out.println("top(): " + stack.top());
        while (!stack.isEmpty()) {
            System.out.println("pop(): " + stack.pop());
        }
    }
}

/*
 * Session-Log (SOLL):
 * 
 * push(): 0 push(): 1 push(): 2 push(): 3 push(): 4 push(): 5 push(): 6 push():
 * 7 push(): 8 push(): 9 push(): 10 push(): 11 push(): 12 push(): 13 push(): 14
 * push(): 15 push(): 16 push(): 17 push(): 18 push(): 19 top(): 19 pop(): 19
 * pop(): 18 pop(): 17 pop(): 16 pop(): 15 pop(): 14 pop(): 13 pop(): 12 pop():
 * 11 pop(): 10 pop(): 9 pop(): 8 pop(): 7 pop(): 6 pop(): 5 pop(): 4 pop(): 3
 * pop(): 2 pop(): 1 pop(): 0
 */

