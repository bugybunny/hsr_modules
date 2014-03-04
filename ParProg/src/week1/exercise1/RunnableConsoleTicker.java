package week1.exercise1;

public class RunnableConsoleTicker implements Runnable {
    private char sign;
    private int  intervallMillis;

    public RunnableConsoleTicker(char aSign, int anIntervallMillis) {
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
        new Thread(new RunnableConsoleTicker('.', 10)).start();
        new Thread(new RunnableConsoleTicker('*', 20)).start();
    }
}