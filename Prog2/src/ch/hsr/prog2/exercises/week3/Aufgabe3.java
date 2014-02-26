/*
 * HSR - Uebungen Programmieren 2: Algorithmen & Datenstrukturen
 * $LastChangedDate: 2013-02-28 11:30:54 +0100 (Do, 28 Feb 2013) $
 */

package ch.hsr.prog2.exercises.week3;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Event;
import java.awt.Graphics;

public class Aufgabe3 extends Applet {

    private static final long serialVersionUID = 1L;

    int                       level            = 1;

    @Override
    public void init() {
        setBackground(new Color(255, 255, 255));
        setSize(420, 550);
    }

    @Override
    public boolean mouseDown(Event ev, int x, int y) {
        if (!ev.metaDown()) {
            level += 1;
        } else if (level > 1) {
            level -= 1;
        }
        repaint();
        return true;
    }

    @Override
    public void paint(Graphics g) {
        koch(g, 10, 310, 400, 310, level); // initial values
    }

    private void koch(Graphics g, double x1, double y1, double x2, double y2,
            int level) {
        double a1, b1, a2, b2, a3, b3;

        if (level > 1) {
            a1 = (2 * x1 + x2) / 3;
            b1 = (2 * y1 + y2) / 3;
            a2 = (x1 + x2) / 2 + (y2 - y1) * Math.sqrt(3) / 6;
            b2 = (y1 + y2) / 2 + (x1 - x2) * Math.sqrt(3) / 6;
            a3 = (2 * x2 + x1) / 3;
            b3 = (2 * y2 + y1) / 3;

            koch(g, x1, y1, a1, b1, level - 1);
            koch(g, a1, b1, a2, b2, level - 1);
            koch(g, a2, b2, a3, b3, level - 1);
            koch(g, a3, b3, x2, y2, level - 1);

        } else {
            // draw a section
            g.drawLine((int) Math.round(x1), (int) Math.round(y1),
                    (int) Math.round(x2), (int) Math.round(y2));
        }
    }
}