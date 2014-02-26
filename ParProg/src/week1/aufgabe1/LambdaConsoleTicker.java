package week1.aufgabe1;

public class LambdaConsoleTicker {

    public static void startTicker(final char sign, final int intervallMillis) {
        new Thread(() -> {
            while (true) {
                System.out.print(sign);
                try {
                    Thread.sleep(intervallMillis);
                }
                catch (InterruptedException anEx) {
                    anEx.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        startTicker('.', 10);
        startTicker('*', 20);
    }
}