package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration;

public class DeclarationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DeclarationNotFoundException() {
		super();
	}
	
	public DeclarationNotFoundException(int declarationID) {
		super("Cannot Find Declaration ID: " + declarationID);
	}
	
}
