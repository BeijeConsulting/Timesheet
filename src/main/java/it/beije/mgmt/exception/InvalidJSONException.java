package it.beije.mgmt.exception;

import it.beije.mgmt.exception.MasterException;

public class InvalidJSONException extends MasterException {

	public InvalidJSONException(String message) {
		super(message);
	}
}
