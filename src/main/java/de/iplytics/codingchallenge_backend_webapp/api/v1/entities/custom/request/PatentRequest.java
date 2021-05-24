package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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
		LocalDate publicationDateValue = null;
		
		try {
			if(publicationDate != null)
				publicationDateValue = LocalDate.parse(publicationDate);
		} catch(DateTimeParseException e) {
			publicationDateValue = null;
		}
		
		return Patent.builder()
				.publicationNumber(publicationNumber)
				.publicationDate(publicationDateValue)
				.title(title)
				.description(description == null ? "" : description)
				.build();
	}
    
}
