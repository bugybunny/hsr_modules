package ch.hsr.prog2.pruefungen._2012.aufgabe1_3;

public class RekursiveAusgabe {
    public static void main(String[] args) {
        print(5);
    }

    public static void print(int k) {
        printChar(k, '!');
        printChar(k, '*');
    }

    public static void printChar(int k, char z) {
        if (k >= 1) {
            System.out.print(z);
            printChar(k - 1, z);
        }
    }

}
