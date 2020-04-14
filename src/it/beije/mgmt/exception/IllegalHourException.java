package it.beije.mgmt.exception;

public class IllegalHourException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public IllegalHourException(String message) {
		super(message);
	}
}
