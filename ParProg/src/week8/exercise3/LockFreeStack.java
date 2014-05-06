package week8.exercise3;

import java.util.concurrent.atomic.AtomicReference;

public class LockFreeStack<T> implements Stack<T> {
    private AtomicReference<Node<T>> top = new AtomicReference<>();

    @Override
    public void push(final T value) {
        //@formatter:off
        /*
         * für mich selbst, da ich etwas Mühe habe den Code einfach so zu lesen Q.Q
         * Erstelle neuen Topnode mit value
         * Erstelle temp Variable für den aktuellen Topnode
         * Solange der aktuelle Topnode verändert wird zwischen unserer Aufnahme und dem neu Setzen
         *      speichere Referenz auf aktuellen Topnode
         *      füge neuen Topnode vorne an die Liste und setze als nächsten den aktuellen Topnode
         *      versuche es erneut
         */
        //@formatter:on
        Node<T> newNode = new Node<>(value);
        Node<T> current;
        do {
            current = top.get();
            newNode.setNext(current);
        }
        while (!top.compareAndSet(current, newNode));
    }

    @Override
    public T pop() {
        Node<T> newTop;
        Node<T> currentTop;
        boolean retry = false;
        do {
            // speedup is over 9000!
            if (retry) {
                Thread.yield();
            }
            currentTop = top.get();
            if (currentTop == null) {
                return null;
            }
            newTop = currentTop.getNext();
            retry = true;
        }
        while (!top.compareAndSet(currentTop, newTop));

        return currentTop.getValue();
    }
}