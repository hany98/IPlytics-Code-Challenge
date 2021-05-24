package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StandardRequest {

	private String standardId;

    private String name;
    
    private String description;
    
	public Standard toStandardEntity() {
		return Standard.builder()
				.standardId(standardId)
				.name(name)
				.description(description == null ? "" : description)
				.build();
	}
    
}
