package de.iplytics.codingchallenge_backend_webapp.api.v1.responses;

public class ExceptionResponse extends GlobalResponse {
	
	public ExceptionResponse(String status, int statusCode, String message) {
		super(status, statusCode, message);
	}
	
}
