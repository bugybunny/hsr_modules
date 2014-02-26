package exams;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * TODO COMMENT ME!
 * 
 * @author msyfrig
 */
public class Foobar extends JPanel {

    private JFrame frame;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Foobar window = new Foobar();
                    window.frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the application.
     */
    public Foobar() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        {
            JPanel jpanel1 = new JPanel();
            FlowLayout jpanel1layout = new FlowLayout();
            jpanel1layout.setAlignment(FlowLayout.RIGHT);
            jpanel1.setLayout(jpanel1layout);
            frame.getContentPane().add(jpanel1, BorderLayout.SOUTH);
            {
                JButton jbutton1 = new JButton();
                jpanel1.add(jbutton1);
                jbutton1.setText("Cancel");
                jbutton1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mousePressed(MouseEvent evt) {
                        frame.dispose();
                    }
                });
            }
        }
        frame.pack();
        frame.setSize(400, 300);
    }
}
