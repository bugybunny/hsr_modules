package woche1;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class GridBagLayoutTest extends JFrame {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        new GridBagLayoutTest();
    }

    public GridBagLayoutTest() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        JPanel contentPane = new JPanel();
        setContentPane(contentPane);
        GridBagLayout gbl_contentpane = new GridBagLayout();
        gbl_contentpane.columnWidths = new int[] { 100, 0, 0 };
        gbl_contentpane.rowHeights = new int[] { 0, 0, 0, 100 };
        gbl_contentpane.columnWeights = new double[] { 0.0, 1.0, 2.0 };
        gbl_contentpane.rowWeights = new double[] { 1, 1, 2.0, 0.0 };
        contentPane.setLayout(gbl_contentpane);

        JButton btn3 = new JButton("3");
        GridBagConstraints gbc_btn3 = new GridBagConstraints();
        gbc_btn3.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn3.gridheight = 2;
        gbc_btn3.gridwidth = 2;
        gbc_btn3.insets = new Insets(0, 0, 0, 0);
        gbc_btn3.gridx = 1;
        gbc_btn3.gridy = 0;
        contentPane.add(btn3, gbc_btn3);

        JButton btn1 = new JButton("1");
        GridBagConstraints gbc_btn1 = new GridBagConstraints();
        gbc_btn1.anchor = GridBagConstraints.SOUTH;
        gbc_btn1.gridheight = 2;
        gbc_btn1.insets = new Insets(0, 0, 0, 0);
        gbc_btn1.gridx = 0;
        gbc_btn1.gridy = 1;
        contentPane.add(btn1, gbc_btn1);

        JButton btn2 = new JButton("2");
        GridBagConstraints gbc_btn2 = new GridBagConstraints();
        gbc_btn2.fill = GridBagConstraints.VERTICAL;
        gbc_btn2.anchor = GridBagConstraints.EAST;
        gbc_btn2.insets = new Insets(0, 0, 0, 0);
        gbc_btn2.gridx = 1;
        gbc_btn2.gridy = 2;
        contentPane.add(btn2, gbc_btn2);

        JButton btn5 = new JButton("5");
        GridBagConstraints gbc_btn5 = new GridBagConstraints();
        gbc_btn5.fill = GridBagConstraints.HORIZONTAL;
        gbc_btn5.anchor = GridBagConstraints.NORTH;
        gbc_btn5.insets = new Insets(0, 0, 0, 0);
        gbc_btn5.gridx = 2;
        gbc_btn5.gridy = 2;
        contentPane.add(btn5, gbc_btn5);

        JButton btn4 = new JButton("4");
        GridBagConstraints gbc_btn4 = new GridBagConstraints();
        gbc_btn4.insets = new Insets(0, 0, 0, 0);
        gbc_btn4.gridx = 2;
        gbc_btn4.gridy = 3;
        contentPane.add(btn4, gbc_btn4);
        setVisible(true);
        // try {
        // Thread.sleep(5000);
        // }
        // catch (InterruptedException anEx) {
        // anEx.printStackTrace();
        // }
        // setSize(100, 100);
    }
}
