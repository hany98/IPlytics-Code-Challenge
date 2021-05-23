package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent;

public class PatentEmptyFieldException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PatentEmptyFieldException() {
		super();
	}
	
	public PatentEmptyFieldException(String field, Class<?> fieldClass) {
		super("Missing Patent Field: " + field + " [" + fieldClass.getTypeName() + "].");
	}
	
}
