package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;

public class DeclarationNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DeclarationNotFoundException() {
		super();
	}
	
	public DeclarationNotFoundException(int declarationID) {
		super("Cannot Find Declaration ID: " + declarationID);
	}
	
	public DeclarationNotFoundException(Patent patent, Standard standard) {
		super("Cannot Find Declaration of Patend ID: " + patent.getPublicationNumber() + " and Standard ID: " + standard.getStandardId());
	}
	
}
