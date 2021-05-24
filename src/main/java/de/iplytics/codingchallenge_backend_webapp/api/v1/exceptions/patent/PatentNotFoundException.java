package de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent;

public class PatentNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	public PatentNotFoundException() {
		super();
	}
	
	public PatentNotFoundException(String patentId) {
		super("Cannot Find Patent ID: " + patentId);
	}
	
}
