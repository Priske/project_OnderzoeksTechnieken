package project.domain.exceptions;

public class PlayerNotFoundException extends RuntimeException {

	public PlayerNotFoundException() {
	}

	public PlayerNotFoundException(String message) {
		super(message);
	}

	public PlayerNotFoundException(Throwable cause) {
		super(cause);
	}

	public PlayerNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
