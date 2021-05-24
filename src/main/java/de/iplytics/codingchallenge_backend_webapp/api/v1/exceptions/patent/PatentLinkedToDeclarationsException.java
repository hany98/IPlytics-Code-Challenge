package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent;

public class PatentLinkedToDeclarationsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PatentLinkedToDeclarationsException() {
		super("Patent Linked to other Declarations");
	}
	
}
