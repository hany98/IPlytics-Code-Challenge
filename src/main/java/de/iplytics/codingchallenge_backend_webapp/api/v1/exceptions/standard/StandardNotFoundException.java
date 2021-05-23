package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard;

public class StandardNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public StandardNotFoundException() {
		super();
	}
	
	public StandardNotFoundException(String standardID) {
		super("Cannot Find Standard ID: " + standardID);
	}
	
}
