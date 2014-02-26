package woche2;

import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

/**
 * @author msyfrig
 */
public class Teilaufgabe2 extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look
            // and feel.
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Teilaufgabe2 frame = new Teilaufgabe2();
                    frame.setVisible(true);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Teilaufgabe2() {
        setTitle("Teilaufgabe 2");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new GridLayout(3, 3));

        // contentPane.add(new JPanel());
        contentPane.add(Box.createGlue());
        contentPane.add(new JButton("Up"));
        contentPane.add(Box.createGlue());
        contentPane.add(new JButton("Left"));
        contentPane.add(new JButton("Fire"));
        contentPane.add(new JButton("Right"));
        contentPane.add(Box.createGlue());
        contentPane.add(new JButton("Down"));
        contentPane.add(Box.createGlue());
    }
}
