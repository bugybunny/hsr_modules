package week5;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ExchangeRates {
	private final String inputFilePath = "data/rates.txt";
	private HashMap<Currency, Double> rates = new HashMap<Currency, Double>();

	public ExchangeRates() throws ImportException {
		rates.put(Currency.USD, 1.0);
		try {
			readRatesFromFile();
		} catch (Exception e) {
			throw new ImportException("Error on exchange rate import", e);
		}
		checkAllCurrenciesLoaded();
	}

	public double getRateToUSD(Currency currency) {
		return rates.get(currency);
	}

	private void readRatesFromFile() throws IOException, ParseException {
		InputStream stream;
		stream = new FileInputStream(inputFilePath);
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(
				stream))) {
			String line;
			line = reader.readLine();
			int lineNumber = 0;
			while (line != null) {
				StringTokenizer tokenizer = new StringTokenizer(line);
				String label = tokenizer.nextToken();
				Currency currency = Currency.valueOf(label);
				String value = tokenizer.nextToken();
				double amount = Double.parseDouble(value);
				if (amount <= 0.0) {
					throw new ParseException("Non-positive exchange rate for "
							+ currency, lineNumber);
				}
				if (tokenizer.hasMoreTokens()) {
					throw new ParseException("Invalid line format for "
							+ currency, lineNumber);
				}
				rates.put(currency, amount);
				line = reader.readLine();
				lineNumber++;
			}
		}
	}

	private void checkAllCurrenciesLoaded() throws ImportException {
		for (Currency c : Currency.values()) {
			if (!rates.containsKey(c)) {
				throw new ImportException("Missing exchange rate " + c);
			}
		}
	}
}
