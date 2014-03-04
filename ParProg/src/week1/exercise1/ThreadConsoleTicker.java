package week1.exercise1;

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

    public static void main(String[] args) throws InterruptedException {
        new ThreadConsoleTicker('.', 10).start();
        new ThreadConsoleTicker('*', 20).start();
    }
}