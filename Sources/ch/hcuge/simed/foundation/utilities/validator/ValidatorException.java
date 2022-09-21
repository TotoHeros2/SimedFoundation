package ch.hcuge.simed.foundation.utilities.validator;

public class ValidatorException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	public ValidatorException() {
	}

	/**
	 * @param message
	 */
	public ValidatorException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ValidatorException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ValidatorException(String message, Throwable cause) {
		super(message, cause);
	}

}
