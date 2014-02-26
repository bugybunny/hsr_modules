package week1.aufgabe2;

import java.util.Scanner;

public class ThreadConsoleTicker extends Thread {
    private char sign;
    private int  intervallMillis;

    public ThreadConsoleTicker(char aSign, int anIntervallMillis) {
        super();
        sign = aSign;
        intervallMillis = anIntervallMillis;
    }

    @Override
    public void run() {
        while (true) {
            System.out.print(sign);
            try {
                Thread.sleep(intervallMillis);
            }
            catch (InterruptedException anEx) {
                anEx.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        Thread t1 = new ThreadConsoleTicker('.', 10);
        t1.setDaemon(true);
        t1.start();
        Thread t2 = new ThreadConsoleTicker('*', 20);
        t2.setDaemon(true);
        t2.start();

        String s = "run";
        while (s.equals("run")) {
            Scanner in = new Scanner(System.in);
            s = in.next();
            in.close();
        }
        System.out.println("main");
    }
}