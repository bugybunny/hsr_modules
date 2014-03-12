package week4.aufgabe2.b;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

class ConsoleLogger implements Observer {
    static final String eating   = "starts eating";
    static final String thinking = "starts thinking";
    static final String hungry   = "is getting hungry";

    public ConsoleLogger(PhilosopherTable table) {
        table.addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (arg == null) {
            System.out.println("Application starting");
        } else {
            Philosopher philo = (Philosopher) arg;
            System.out.println("Philosopher: " + philo.getName() + " "
                    + getStateString(philo));
        }
    }

    private String getStateString(Philosopher philo) {
        switch (philo.getPhiloState()) {
            case PhiloState.eating:
                return eating;
            case PhiloState.thinking:
                return thinking;
            case PhiloState.hungry:
                return hungry;
        }
        return "";
    }
}

class PhilosopherPanel extends JPanel implements Observer {
    private static final long serialVersionUID = 5113281871592746242L;
    private PhilosopherTable  table;
    private int               philCount        = 5;                   // greater
                                                                       // than 2
    private javax.swing.Timer timer;

    public PhilosopherPanel(PhilosopherTable table, int philosopherCount) {
        philCount = philosopherCount;
        this.table = table;
        table.addObserver(this);
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    @Override
    public void paint(Graphics g) {
        Insets insets = getInsets();
        Dimension dim = getSize();
        int length = Math.min(dim.width, dim.height);
        double teta;
        double tetaIn;
        double phi = 2 * Math.PI / philCount;
        int plateRadius = (int) (Math.sqrt(Math.pow(length / 2, 2.0)
                - Math.pow(Math.cos(phi) * (length / 2), 2.0) + Math.sin(phi)
                * (length / 2)) * 0.25);
        int tableRadius = length / 2 - plateRadius - 10;
        int halfStickLength = (int) (plateRadius * 1.25);
        int centerX = length / 2 + insets.left;
        int centerY = length / 2 + insets.top;

        super.paint(g);

        for (int i = 0; i < philCount; i++) {
            int transCenterX = centerX - plateRadius;
            int transCenterY = centerY - plateRadius;

            teta = 0;
            switch (table.getPhilo(i).getPhiloState()) {
                case PhiloState.thinking:
                    g.setColor(Color.black);
                    break;
                case PhiloState.hungry:
                    g.setColor(Color.red);
                    break;
                case PhiloState.eating:
                    g.setColor(Color.yellow);
                    break;
            }
            g.fillArc(
                    (int) Math.round(transCenterX + tableRadius
                            * Math.cos(i * phi)),
                    (int) Math.round(transCenterY + tableRadius
                            * Math.sin(i * phi)), 2 * plateRadius,
                    2 * plateRadius, 0, 360);

            g.setColor(Color.black);
            if (table.getPhilo(i).getPhiloState() == PhiloState.eating) {
                teta = (-phi / 7);
            }
            if (table.getPhilo((i + 1) % philCount).getPhiloState() == PhiloState.eating) {
                teta = phi / 7;
            }

            tetaIn = teta * 1.75;

            g.drawLine(
                    (int) Math.round(centerX + (tableRadius - halfStickLength)
                            * Math.cos(i * phi + phi / 2 + tetaIn)),
                    (int) Math.round(centerY + (tableRadius - halfStickLength)
                            * Math.sin(i * phi + phi / 2 + tetaIn)),
                    (int) Math.round(centerX + (tableRadius + halfStickLength)
                            * Math.cos(i * phi + phi / 2 + teta)),
                    (int) Math.round(centerY + (tableRadius + halfStickLength)
                            * Math.sin(i * phi + phi / 2 + teta)));
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}

public class PhilosopherGui extends JFrame {
    private static final long serialVersionUID = 1L;

    public PhilosopherGui(int philosopherCount) {
        setTitle("Philosopher");
        setVisible(true);
        setVisible(false);
        Insets insets = getInsets();
        setSize(insets.left + insets.right + 400, insets.top + insets.bottom
                + 400);

        PhilosopherTable table = new PhilosopherTable(philosopherCount);
        PhilosopherPanel panel = new PhilosopherPanel(table, philosopherCount);
        new ConsoleLogger(table);
        table.start();

        setContentPane(panel);
        setVisible(true);
        repaint();

        Thread.currentThread().setPriority(Thread.MAX_PRIORITY);

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String args[]) {
        new PhilosopherGui(5);
    }
}
