package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent;

public class PatentIDAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PatentIDAlreadyExistsException() {
		super();
	}
	
	public PatentIDAlreadyExistsException(String patentID) {
		super("Duplicate Patent ID: " + patentID);
	}
	
}
