package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response;

import java.time.LocalDateTime;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationResponse {
	
	private int id;
	
	private Patent patent;
	
	private Standard standard;
	
	private String description;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
    
}