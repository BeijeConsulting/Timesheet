package it.beije.mgmt.exception;

public class IllegalDateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public IllegalDateException(String message) {
		super(message);
	}
}
