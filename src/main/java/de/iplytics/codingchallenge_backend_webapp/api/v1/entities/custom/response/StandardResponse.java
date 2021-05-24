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
public class StandardResponse {

	private String standardId;

    private String name;
    
    private String description;
    
    private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
    
}
