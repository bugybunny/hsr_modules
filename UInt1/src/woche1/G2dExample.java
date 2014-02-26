package woche1;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JComponent;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class G2dExample extends JComponent {

    public G2dExample() {
        setVisible(true);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Color.BLACK);
        g2d.fillRect(0, this.getHeight() - 150, this.getWidth(), 150);
        // g2d.dispose();
        super.paintComponent(g);
    }

    public static void main(String[] args) {
        new G2dExample();
    }
}
