package it.beije.mgmt.restcontroller.exception;

public class WrongDateException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public WrongDateException(String message) {
		super(message);
	}
}
