package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard;

public class StandardEmptyFieldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public StandardEmptyFieldException() {
		super();
	}
	
	public StandardEmptyFieldException(String field, Class<?> fieldClass) {
		super("Missing Standard Field: " + field + " [" + fieldClass.getTypeName() + "].");
	}
	
}
