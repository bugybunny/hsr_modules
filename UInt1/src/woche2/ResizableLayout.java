package woche2;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

/**
 * @author msyfrig
 */
public class ResizableLayout extends JFrame {

    private JPanel     contentPane;
    private JTextField isbnTextfield;
    private JTextField titleTextfield;
    private JLabel     authorLabel;
    private JTextField authorTextfield;
    private JLabel     publisherLabel;
    private JTextField publisherTextfield;
    private JLabel     conditionLabel;
    private JLabel     labelCopies;
    private JLabel     labelAvailable;
    private JButton    buttonAddCopy;
    private JComboBox  comboBox;
    private Component  glue;
    private Component  rigidArea;

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
            e.printStackTrace();
        }
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    ResizableLayout frame = new ResizableLayout();
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
    public ResizableLayout() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 482, 358);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(new BorderLayout());

        JPanel northPanel = new JPanel();
        northPanel.setBorder(new TitledBorder(UIManager
                .getBorder("TitledBorder.border"), "Title Information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(northPanel, BorderLayout.CENTER);
        GridBagLayout gbl_panel = new GridBagLayout();
        gbl_panel.columnWidths = new int[] { 0, 0, 0, 0, 0 };
        gbl_panel.rowHeights = new int[] { 30, 0, 0, 0, 0, 0, 0 };
        gbl_panel.columnWeights = new double[] { 0.0, 0.0, 0.0, 1.0,
                Double.MIN_VALUE };
        gbl_panel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
                Double.MIN_VALUE };
        northPanel.setLayout(gbl_panel);

        JLabel isbnLabel = new JLabel("ISBN:");
        GridBagConstraints gbc_isbnLabel = new GridBagConstraints();
        gbc_isbnLabel.insets = new Insets(0, 0, 5, 5);
        gbc_isbnLabel.gridx = 1;
        gbc_isbnLabel.gridy = 1;
        northPanel.add(isbnLabel, gbc_isbnLabel);

        isbnTextfield = new JTextField();
        GridBagConstraints gbc_isbnTextfield = new GridBagConstraints();
        gbc_isbnTextfield.insets = new Insets(0, 0, 5, 0);
        gbc_isbnTextfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_isbnTextfield.gridx = 3;
        gbc_isbnTextfield.gridy = 1;
        northPanel.add(isbnTextfield, gbc_isbnTextfield);
        isbnTextfield.setColumns(10);

        JLabel titleLabel = new JLabel("Title:");
        GridBagConstraints gbc_titleLabel = new GridBagConstraints();
        gbc_titleLabel.insets = new Insets(0, 0, 5, 5);
        gbc_titleLabel.gridx = 1;
        gbc_titleLabel.gridy = 2;
        northPanel.add(titleLabel, gbc_titleLabel);

        titleTextfield = new JTextField();
        GridBagConstraints gbc_titleTextfield = new GridBagConstraints();
        gbc_titleTextfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_titleTextfield.insets = new Insets(0, 0, 5, 0);
        gbc_titleTextfield.gridx = 3;
        gbc_titleTextfield.gridy = 2;
        northPanel.add(titleTextfield, gbc_titleTextfield);
        titleTextfield.setColumns(10);

        authorLabel = new JLabel("Author:");
        GridBagConstraints gbc_authorLabel = new GridBagConstraints();
        gbc_authorLabel.anchor = GridBagConstraints.NORTH;
        gbc_authorLabel.insets = new Insets(0, 0, 5, 5);
        gbc_authorLabel.gridx = 1;
        gbc_authorLabel.gridy = 3;
        northPanel.add(authorLabel, gbc_authorLabel);

        authorTextfield = new JTextField();
        GridBagConstraints gbc_authorTextfield = new GridBagConstraints();
        gbc_authorTextfield.insets = new Insets(0, 0, 5, 0);
        gbc_authorTextfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_authorTextfield.gridx = 3;
        gbc_authorTextfield.gridy = 3;
        northPanel.add(authorTextfield, gbc_authorTextfield);
        authorTextfield.setColumns(10);

        publisherLabel = new JLabel("Publisher:");
        GridBagConstraints gbc_publisherLabel = new GridBagConstraints();
        gbc_publisherLabel.insets = new Insets(0, 0, 5, 5);
        gbc_publisherLabel.gridx = 1;
        gbc_publisherLabel.gridy = 4;
        northPanel.add(publisherLabel, gbc_publisherLabel);

        publisherTextfield = new JTextField();
        GridBagConstraints gbc_publisherTextfield = new GridBagConstraints();
        gbc_publisherTextfield.insets = new Insets(0, 0, 5, 0);
        gbc_publisherTextfield.fill = GridBagConstraints.HORIZONTAL;
        gbc_publisherTextfield.gridx = 3;
        gbc_publisherTextfield.gridy = 4;
        northPanel.add(publisherTextfield, gbc_publisherTextfield);
        publisherTextfield.setColumns(10);

        conditionLabel = new JLabel("Condition:");
        GridBagConstraints gbc_conditionLabel = new GridBagConstraints();
        gbc_conditionLabel.insets = new Insets(0, 0, 0, 5);
        gbc_conditionLabel.gridx = 1;
        gbc_conditionLabel.gridy = 5;
        northPanel.add(conditionLabel, gbc_conditionLabel);

        comboBox = new JComboBox();
        comboBox.setModel(new DefaultComboBoxModel(
                new String[] { "New", "Used" }));
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx = 3;
        gbc_comboBox.gridy = 5;
        northPanel.add(comboBox, gbc_comboBox);

        JPanel southPanel = new JPanel();
        southPanel.setBorder(new TitledBorder(null, "Inventory Information",
                TitledBorder.LEADING, TitledBorder.TOP, null, null));
        contentPane.add(southPanel, BorderLayout.SOUTH);
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.X_AXIS));

        labelCopies = new JLabel("Total Copies: 8");
        labelCopies.setAlignmentX(Component.RIGHT_ALIGNMENT);
        southPanel.add(labelCopies);

        rigidArea = Box.createRigidArea(new Dimension(20, 20));
        southPanel.add(rigidArea);

        labelAvailable = new JLabel("Total available: 2");
        southPanel.add(labelAvailable);

        glue = Box.createGlue();
        southPanel.add(glue);

        buttonAddCopy = new JButton("Add a copy");
        buttonAddCopy.setAlignmentX(Component.RIGHT_ALIGNMENT);
        southPanel.add(buttonAddCopy);
    }

}
