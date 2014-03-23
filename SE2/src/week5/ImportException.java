package week5;
public class ImportException extends RuntimeException {
	private static final long serialVersionUID = 6744091312227573510L;

	public ImportException(String message) {
		super(message);
	}

	public ImportException(String message, Exception inner) {
		super(message, inner);
	}
}
