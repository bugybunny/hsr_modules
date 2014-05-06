package week8.exercise3;

public class Node<T> {
    private Node<T> next;
    private T       value;

    public Node(T aValue) {
        super();
        value = aValue;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> aNext) {
        next = aNext;
    }

}
