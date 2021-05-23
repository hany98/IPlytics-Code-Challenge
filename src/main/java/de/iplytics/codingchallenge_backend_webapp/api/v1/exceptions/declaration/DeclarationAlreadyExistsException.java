package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration;

public class DeclarationAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DeclarationAlreadyExistsException() {
		super();
	}
	
	public DeclarationAlreadyExistsException(String publicationNumber, String standardId) {
		super("Duplicate Declaration: {publicationNumber : " + publicationNumber + ", standardId : " + standardId + "}.");
	}
	
}
