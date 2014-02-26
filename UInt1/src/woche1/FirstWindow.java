package woche1;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 * @author msyfrig
 */
public class FirstWindow {

    private JFrame     frame;
    private JTextField textField;
    private JButton    btnCancel;
    private JButton    btnSendFeedback;
    private JTextPane  textPane;
    private JComboBox  comboBox;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    FirstWindow window = new FirstWindow();
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
    public FirstWindow() {
        initialize();
    }

    /**
     * Initialize the contents of the frame.
     */
    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 387, 351);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        JLabel lblNewLabel = new JLabel("Feedback");
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
        lblNewLabel.setBounds(127, 26, 166, 25);
        frame.getContentPane().add(lblNewLabel);

        textField = new JTextField();
        textField.setBounds(127, 83, 86, 20);
        frame.getContentPane().add(textField);
        textField.setColumns(10);

        JLabel lblNewLabel_1 = new JLabel("Your Name:");
        lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblNewLabel_1.setBounds(22, 84, 86, 14);
        frame.getContentPane().add(lblNewLabel_1);

        JLabel lblYourText = new JLabel("Your Text");
        lblYourText.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblYourText.setBounds(22, 127, 78, 14);
        frame.getContentPane().add(lblYourText);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(127, 127, 232, 74);
        frame.getContentPane().add(scrollPane);

        textPane = new JTextPane();
        scrollPane.setViewportView(textPane);

        JLabel lblYourRating = new JLabel("Your Rating:");
        lblYourRating.setFont(new Font("Tahoma", Font.PLAIN, 14));
        lblYourRating.setBounds(22, 219, 78, 14);
        frame.getContentPane().add(lblYourRating);

        comboBox = new JComboBox();
        comboBox.addItem("Foo");
        comboBox.addItem("Yolo");
        comboBox.setBounds(127, 218, 232, 20);
        frame.getContentPane().add(comboBox);

        btnSendFeedback = new JButton("Send Feedback");
        btnSendFeedback.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("name:" + textField.getText() + "\ntext: "
                        + textPane.getText() + "\nrating: "
                        + comboBox.getSelectedItem().toString());
            }
        });
        btnSendFeedback.setBounds(22, 268, 121, 23);
        frame.getContentPane().add(btnSendFeedback);

        btnCancel = new JButton("Cancel");
        btnCancel.setEnabled(false);
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                frame.dispose();
            }
        });
        // enable cancel button only if name is "admin"
        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void checkAdmin() {
                if (textField.getText().equalsIgnoreCase("admin")) {
                    btnCancel.setEnabled(true);
                } else {
                    btnCancel.setEnabled(false);
                }
            }

            @Override
            public void changedUpdate(DocumentEvent aE) {
                checkAdmin();
            }

            @Override
            public void insertUpdate(DocumentEvent aE) {
                checkAdmin();
            }

            @Override
            public void removeUpdate(DocumentEvent aE) {
                checkAdmin();
            }
        });

        btnCancel.setBounds(250, 268, 107, 23);
        frame.getContentPane().add(btnCancel);
    }
}
