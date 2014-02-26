package ch.hsr.prog2.pruefungen._2012.aufgabe1_1;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Test {

    public static void main(String[] args) {
        Banana[] bananas = new Banana[5];
        Fruit[] fruit = bananas;
        fruit[0] = new Apple();
    }
}
