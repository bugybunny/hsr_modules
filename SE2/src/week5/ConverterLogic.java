package week5;
public class ConverterLogic {
	private ExchangeRates exchangeRates;

	// precondition: exchangeRates != null
	public ConverterLogic(ExchangeRates exchangeRates) {
		if (exchangeRates == null) {
			throw new AssertionError("Must not be null");
		}
		this.exchangeRates = exchangeRates;
	}

	// precondition: amount not NaN && amount not INF
	public double convert(double amount, Currency from, Currency to) {
		if (Double.isNaN(amount) || Double.isInfinite(amount)) {
			throw new AssertionError("Must be a valid number");
		}
		return amount * exchangeRates.getRateToUSD(from)
				/ exchangeRates.getRateToUSD(to);
	}
}
