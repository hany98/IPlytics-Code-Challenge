package de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request;

import org.springframework.beans.factory.annotation.Autowired;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeclarationRequest {
	
	@Autowired
    PatentService patentService;
	
	@Autowired
    StandardService standardService;

	private String publicationNumber;
	
	private String standardId;
    
	private String description;
	
	public Declaration toDeclarationEntity() {
		return Declaration.builder()
				.patent(patentService.getPatent(publicationNumber))
				.standard(standardService.getStandard(standardId))
				.description(description)
				.build();
	}
    
}