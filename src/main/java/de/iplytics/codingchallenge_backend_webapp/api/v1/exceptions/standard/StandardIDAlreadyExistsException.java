package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard;

public class StandardIDAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public StandardIDAlreadyExistsException() {
		super();
	}
	
	public StandardIDAlreadyExistsException(String standardID) {
		super("Duplicate Standard ID: " + standardID);
	}
	
}
