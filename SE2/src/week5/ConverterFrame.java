package week5;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.InputVerifier;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ConverterFrame extends JFrame {
	private static final long serialVersionUID = 9163427800892086619L;

	private JLabel amountLabel;
	private JTextField amountTextBox;
	private JLabel fromCurrencyLabel;
	private JComboBox<Currency> fromCurrencyCombobox;
	private JLabel toCurrencyLabel;
	private JComboBox<Currency> toCurrencyCombobox;
	private JButton convertButton;
	private JLabel resultLabel;

	private ConverterLogic converter;

	public ConverterFrame(ConverterLogic converter) {
		super();
		this.converter = converter;
		initializeComponents();
		initializeListeners();
	}

	private void initializeListeners() {
		convertButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				double amount = Double.parseDouble(amountTextBox.getText());
				Currency from = (Currency) fromCurrencyCombobox
						.getSelectedItem();
				Currency to = (Currency) toCurrencyCombobox.getSelectedItem();
				double result = converter.convert(amount, from, to);
				resultLabel.setText(String.format("%.2f", result));
			}
		});
		amountTextBox.setInputVerifier(new InputVerifier() {
			@Override
			public boolean verify(JComponent arg0) {
				JTextField textField = (JTextField) arg0;
				String text = textField.getText();
				if (!isNumber(text)) {
					textField.setText("");
					resultLabel.setText("");
					if (!text.isEmpty()) {
						throw new UserInputException(
								"Must be a valid number (fraction sign \".\")");
					}
				}
				return isNumber(text);
			}
		});
	}

	private void initializeComponents() {
		setTitle("FOREX");
		setResizable(false);
		JPanel rootPanel = new JPanel(new GridLayout(4, 1));

		JPanel amountPanel = new JPanel();
		amountLabel = new JLabel("Amount");
		amountPanel.add(amountLabel);
		amountTextBox = new JTextField(10);
		amountPanel.add(amountTextBox);
		rootPanel.add(amountPanel);

		JPanel currencyPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		fromCurrencyLabel = new JLabel("From");
		currencyPanel.add(fromCurrencyLabel);
		fromCurrencyCombobox = new JComboBox<Currency>(Currency.values());
		currencyPanel.add(fromCurrencyCombobox);
		toCurrencyLabel = new JLabel("To");
		currencyPanel.add(toCurrencyLabel);
		toCurrencyCombobox = new JComboBox<Currency>(Currency.values());
		currencyPanel.add(toCurrencyCombobox);
		rootPanel.add(currencyPanel);

		convertButton = new JButton("Convert");
		rootPanel.add(convertButton);

		JPanel resultPanel = new JPanel();
		resultLabel = new JLabel();
		resultPanel.add(resultLabel);
		rootPanel.add(resultPanel);

		add(rootPanel);
	}

	private boolean isNumber(String text) {
		try {
			Double.parseDouble(text);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
