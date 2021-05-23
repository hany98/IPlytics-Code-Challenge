package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationRequest {

	private String publicationNumber;
	
	private String standardId;
    
	private String description;
    
}