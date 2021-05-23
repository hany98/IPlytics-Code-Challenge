package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;

public class DeclarationAlreadyExistsException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DeclarationAlreadyExistsException() {
		super();
	}
	
	public DeclarationAlreadyExistsException(DeclarationRequest declarationRequest) {
		super("Duplicate Declaration: {publicationNumber : " + declarationRequest.getPublicationNumber() + ", standardId : " + declarationRequest.getStandardId() + "}.");
	}
	
}
