package woche2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

/**
 * @author msyfrig
 */
public class Teilaufgabe1 extends JFrame {

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
                    Teilaufgabe1 frame = new Teilaufgabe1();
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
    public Teilaufgabe1() {
        setTitle("Teilaufgabe 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout(0, 0));

        JPanel panel = new JPanel();
        contentPane.add(panel, BorderLayout.WEST);
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton button = new JButton("Preview");
        button.setAlignmentY(Component.BOTTOM_ALIGNMENT);
        panel.add(button);

    }

}
