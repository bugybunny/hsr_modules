package week5;
public class UserInputException extends RuntimeException {
	private static final long serialVersionUID = -265406059058153211L;

	public UserInputException(String message) {
		super(message);
	}

	public UserInputException(String message, Exception inner) {
		super(message, inner);
	}
}
