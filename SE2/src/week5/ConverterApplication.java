package week5;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ConverterApplication {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Thread.setDefaultUncaughtExceptionHandler((thread, exception)
                // -> {
                // System.err.println("Thread " + thread.getId() + " caused");
                // exception.printStackTrace();
                // });
                ExchangeRates exchangeRates = new ExchangeRates();
                ConverterLogic converterLogic = new ConverterLogic(
                        exchangeRates);
                ConverterFrame frame = new ConverterFrame(converterLogic);
                frame.pack();
                frame.setVisible(true);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            }
        });
    }
}
