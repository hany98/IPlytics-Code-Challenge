package de.iplytics.codingchallenge_backend_webapp.api.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Builder
@Entity(name = "patent")
@AllArgsConstructor
@NoArgsConstructor
public class Patent {

    @Id
    private String publicationNumber;
    
    private LocalDate publicationDate;
    
    private String title;
    
    private String description;
    
    private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
	
	public PatentResponse toPatentResponse() {
		return PatentResponse.builder()
				.publicationNumber(publicationNumber)
				.publicationDate(publicationDate == null ? "" : publicationDate.toString())
				.title(title)
				.description(description)
				.creationDate(creationDate)
				.modificationDate(modificationDate)
				.build();
	}
    
}
