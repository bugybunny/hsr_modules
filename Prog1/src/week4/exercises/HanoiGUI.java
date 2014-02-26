package week4.exercises;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class HanoiGUI {
    private JFrame      frame          = new JFrame();
    private HanoiCanvas canvas         = new HanoiCanvas();
    private int         DELAY_PER_STEP = 100;              // ms

    public HanoiGUI(int nofDisks) {
        showGUI(nofDisks);
    }

    public void move(final int diskNo, final int fromPileNo, final int toPileNo) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                canvas.pop(fromPileNo, diskNo);
                canvas.push(toPileNo, diskNo);
            }
        });
        try {
            Thread.sleep(DELAY_PER_STEP);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void showGUI(final int nofDisks) {
        frame.setSize(800, 400);
        frame.setTitle("Towers of Hanoi");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(canvas);
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                for (int i = nofDisks; i > 0; i--) {
                    canvas.push(0, i);
                }
                frame.setVisible(true);
            }
        });
    }

    private class HanoiCanvas extends Canvas {
        private static final long serialVersionUID = 8058203105304498165L;
        private static final int  NOF_PILES        = 3;
        private static final int  TOP_GAP          = 100;
        private static final int  PILE_DISTANCE    = 200;
        private static final int  PILE_THICKNESS   = 10;
        private static final int  PILE_HEIGHT      = 200;
        private static final int  BASE_THICKNESS   = 5;
        private static final int  DISK_MINSIZE     = 30;
        private static final int  DISK_INCSIZE     = 10;
        private static final int  DISK_THICKNESS   = 15;
        private static final int  DISK_GAP         = 1;

        private Pile[]            piles            = new Pile[NOF_PILES];

        public HanoiCanvas() {
            for (int i = 0; i < NOF_PILES; i++) {
                piles[i] = new Pile();
            }
        }

        public void push(int pileNo, int diskNo) {
            piles[pileNo].push(diskNo);
            update();
        }

        public void pop(int pileNo, int diskNo) {
            piles[pileNo].pop(diskNo);
            update();
        }

        private void update() {
            invalidate();
            repaint();
        }

        @Override
        public void paint(Graphics graphics) {
            drawBaseLine(graphics);
            for (int i = 0; i < NOF_PILES; i++) {
                drawPile(graphics, i);
            }
        }

        private void drawPile(Graphics graphics, int pileNo) {
            graphics.setColor(Color.BLACK);
            graphics.fillRect(
                    PILE_DISTANCE * (pileNo + 1) - PILE_THICKNESS / 2, TOP_GAP,
                    PILE_THICKNESS, PILE_HEIGHT);
            drawDisks(graphics, pileNo);
        }

        private void drawDisks(Graphics graphics, int pileNo) {
            int[] disks = piles[pileNo].inspect();
            for (int i = 0; i < disks.length; i++) {
                int width = disks[i] * DISK_INCSIZE + DISK_MINSIZE;
                int top = TOP_GAP + PILE_HEIGHT - DISK_THICKNESS * (i + 1)
                        - DISK_GAP * i;
                graphics.setColor(getDiskColor(disks[i]));
                graphics.fillRect(PILE_DISTANCE * (pileNo + 1) - width / 2,
                        top, width, DISK_THICKNESS);
            }
        }

        private Color getDiskColor(int diskNo) {
            switch (diskNo % 8) {
                case 0:
                    return Color.BLUE;
                case 1:
                    return Color.CYAN;
                case 2:
                    return Color.GREEN;
                case 3:
                    return Color.MAGENTA;
                case 4:
                    return Color.ORANGE;
                case 5:
                    return Color.PINK;
                case 6:
                    return Color.RED;
                case 7:
                    return Color.DARK_GRAY;
                default:
                    return Color.YELLOW;
            }
        }

        private void drawBaseLine(Graphics graphics) {
            graphics.fillRect(0, TOP_GAP + PILE_HEIGHT, (NOF_PILES + 1)
                    * PILE_DISTANCE, BASE_THICKNESS);
        }

        private class Pile {
            private Stack<Integer> stack = new Stack<Integer>();

            public void push(int diskNo) {
                if (stack.isEmpty() || stack.peek().intValue() > diskNo) {
                    stack.push(Integer.valueOf(diskNo));
                } else {
                    throw new RuntimeException("Invalid disk move!");
                }
            }

            public void pop(int diskNo) {
                if (stack.isEmpty() || stack.pop().intValue() != diskNo) {
                    throw new RuntimeException("Invalid disk move!");
                }
            }

            public int[] inspect() {
                int[] result = new int[stack.size()];
                for (int i = 0; i < stack.size(); i++) {
                    result[i] = stack.get(i).intValue();
                }
                return result;
            }
        }
    }
}
