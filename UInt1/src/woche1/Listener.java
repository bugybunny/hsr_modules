package woche1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import javax.swing.border.LineBorder;

public class Listener extends javax.swing.JFrame {

    private final Color up      = new Color(240, 60, 60);
    private final Color down    = new Color(70, 190, 100);
    private final Color resized = new Color(220, 190, 40);
    private final Color white   = new Color(255, 255, 255);

    private JLabel      lblDemo;
    private JButton     add;
    private JPanel      detector;

    private int         counter = 0;

    /**
     * Auto-generated main method to display this JFrame
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Listener inst = new Listener();
                inst.setLocationRelativeTo(null);
                inst.setVisible(true);
            }
        });
    }

    public Listener() {
        super();
        initGUI();
    }

    private void initGUI() {
        try {
            BorderLayout thisLayout = new BorderLayout();
            getContentPane().setLayout(thisLayout);
            setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent aE) {
                    detector.setBackground(resized);
                }
            });

            {
                lblDemo = new JLabel();
                getContentPane().add(lblDemo, BorderLayout.NORTH);
                lblDemo.setText("ListenerDemo");
            }
            {
                add = new JButton("add Button");
                getContentPane().add(add, BorderLayout.SOUTH);
                add.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent aE) {
                        addNewButton();
                    }
                });
            }
            {
                detector = new JPanel();

                getContentPane().add(detector, BorderLayout.CENTER);
                detector.setBorder(new LineBorder(new java.awt.Color(0, 0, 0),
                        1, false));
                detector.setPreferredSize(new java.awt.Dimension(200, 200));
                detector.setSize(200, 200);

                detector.addMouseMotionListener(new MouseColorListener());
                detector.addContainerListener(new ContainerAdapter() {
                    @Override
                    public void componentAdded(ContainerEvent aE) {
                        detector.setBackground(white);
                    }
                });

                add.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyReleased(KeyEvent aKeyEvent) {
                        if (aKeyEvent.getKeyCode() == KeyEvent.VK_UP) {
                            detector.setBackground(detector.getBackground()
                                    .darker());
                        }
                    }
                });
            }
            for (Component tempComp : this.getContentPane().getComponents()) {
                System.out.println(tempComp.getName());
                tempComp.addKeyListener(new KeyAdapter() {
                    @Override
                    public void keyPressed(KeyEvent aKeyEvent) {
                        if (aKeyEvent.getKeyCode() == KeyEvent.VK_UP) {
                            detector.setBackground(detector.getBackground()
                                    .darker());
                        } else if (aKeyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                            detector.setBackground(detector.getBackground()
                                    .brighter());
                        }
                    }
                });
            }
            pack();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addNewButton() {
        detector.add(new JButton(counter++ + ""));
        detector.validate();
    }

    private class MouseColorListener extends MouseAdapter {
        private int oldLocation = 0;

        @Override
        public void mouseMoved(MouseEvent aMouseEvent) {
            if (aMouseEvent.getY() > oldLocation) {
                detector.setBackground(up);
            } else if (aMouseEvent.getY() < oldLocation) {
                detector.setBackground(down);
            }
            oldLocation = aMouseEvent.getY();
        }
    }
}