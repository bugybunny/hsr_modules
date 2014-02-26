package woche2;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;

/**
 * @author msyfrig
 */
public class Teilaufgabe3 extends JFrame {

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
                    Teilaufgabe3 frame = new Teilaufgabe3();
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
    public Teilaufgabe3() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JPanel firstRow = new JPanel();
        firstRow.add(new JCheckBox("Include Images"));
        firstRow.add(new JCheckBox("Flatten Layers"));
        firstRow.add(new JCheckBox("Rasterize"));
        JPanel secondRow = new JPanel(new GridLayout());
        secondRow.add(new JLabel("Filename: "));
        secondRow.add(new JTextField("Some Text"));

        JPanel northPanel = new JPanel(new GridLayout(2, 1));
        northPanel.add(firstRow);
        northPanel.add(secondRow);

        contentPane.add(northPanel, BorderLayout.NORTH);
    }
}
