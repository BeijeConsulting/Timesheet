package it.beije.mgmt.restcontroller.exception;

import it.beije.mgmt.exception.MasterException;

public class NoContentException extends MasterException{

	private static final long serialVersionUID = 1L;

	public NoContentException(String message) {
		super(message);
	}
}
