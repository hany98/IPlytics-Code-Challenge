package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatentResponse {

    private String publicationNumber;
    
    private String publicationDate;
    
    private String title;
    
    private String description;
    
    private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
    
}
