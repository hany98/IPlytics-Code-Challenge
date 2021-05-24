package de.iplytics.codingchallenge_backend_webapp.api.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;

@Data
@Builder
@Entity(name = "standard")
@AllArgsConstructor
@NoArgsConstructor
public class Standard {

    @Id
    private String standardId;

    private String name;
    
    private String description;
    
    private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
	
	public StandardResponse toStandardResponse() {
		return StandardResponse.builder()
				.standardId(standardId)
				.name(name)
				.description(description)
				.creationDate(creationDate)
				.modificationDate(modificationDate)
				.build();
	}
}
