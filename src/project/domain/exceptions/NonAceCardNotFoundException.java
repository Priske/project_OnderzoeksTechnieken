package project.domain.exceptions;

public class NonAceCardNotFoundException extends RuntimeException {

	public NonAceCardNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	public NonAceCardNotFoundException(Throwable cause) {
		super(cause);
	}

	public NonAceCardNotFoundException(String message) {
		super(message);
	}

	public NonAceCardNotFoundException() {
	}
}
