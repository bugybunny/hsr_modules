package woche1;

import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.LinearGradientPaint;
import java.awt.RenderingHints;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class LinearGradientPaintExample {
    public static void main(String[] args) {
        Frame frame = new Frame("LinearGradientPaintExample");
        frame.setSize(400, 400);
        frame.add(new CanvasToDisplay());
        frame.setVisible(true);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}

class CanvasToDisplay extends Component {
    @Override
    public void paint(Graphics g) {
        final float[] FRACTIONS = { 0.0f, 0.5f, 1.0f };
        final Color[] DARK_COLORS = { Color.GREEN.darker(),
                Color.YELLOW.darker(), Color.RED.darker() };
        final Rectangle2D r2d = new Rectangle2D.Double(0, 200, 100, 50);

        Graphics2D g2D = (Graphics2D) g;
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);
        LinearGradientPaint DARK_GRADIENT = new LinearGradientPaint(
                new Point2D.Double(0, 0), new Point2D.Double(100, 0),
                FRACTIONS, DARK_COLORS);
        g2D.setPaint(DARK_GRADIENT);
        g2D.fill(r2d);
    }
}
