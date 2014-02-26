package ch.hsr.prog2.pruefungen._2012.aufgabe1_1;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class GenerischeListe<V> {
    private V[] vArray;

    @SuppressWarnings("unchecked")
    GenerischeListe(int capacity) {
        vArray = (V[]) new Object[capacity];
    }

    public void size() {
        System.out.println(vArray.length);
    }

    public static void main(String[] args) {
        GenerischeListe<Integer> iList = new GenerischeListe<>(10);
        iList.size();
    }
}