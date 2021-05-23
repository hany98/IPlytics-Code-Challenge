package de.iplytics.codingchallenge_backend_webapp.api.v1.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GlobalResponse {

	private String status;
	
	private int statusCode;
	
	private String message;
	
}
