package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request;

import java.time.LocalDate;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PatentRequest {

    private String publicationNumber;
    
    private String publicationDate;
    
    private String title;
    
    private String description;

	public Patent toPatentEntity() {
		return Patent.builder()
				.publicationNumber(publicationNumber)
				.publicationDate(publicationDate == null ? null : LocalDate.parse(publicationDate))
				.title(title)
				.description(description)
				.build();
	}
    
}
