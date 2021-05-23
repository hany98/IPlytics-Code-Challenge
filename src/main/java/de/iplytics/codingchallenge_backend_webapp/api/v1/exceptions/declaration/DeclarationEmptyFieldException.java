package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration;

public class DeclarationEmptyFieldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public DeclarationEmptyFieldException() {
		super();
	}
	
	public DeclarationEmptyFieldException(String field, Class<?> fieldClass) {
		super("Missing Declaration Field: " + field + " [" + fieldClass.getTypeName() + "].");
	}
	
}
