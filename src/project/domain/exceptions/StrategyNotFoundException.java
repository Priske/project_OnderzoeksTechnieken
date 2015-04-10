package project.domain.exceptions;

public class StrategyNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public StrategyNotFoundException() {
	}

	public StrategyNotFoundException(String message) {
		super(message);
	}

	public StrategyNotFoundException(Throwable cause) {
		super(cause);
	}

	public StrategyNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
