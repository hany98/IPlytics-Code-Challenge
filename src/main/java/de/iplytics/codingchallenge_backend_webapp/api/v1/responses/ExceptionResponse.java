package de.iplytics.codingchallenge_backend_webapp.api.v1.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

	private String status;
	
	private int status_code;
	
	private String message;
	
}
