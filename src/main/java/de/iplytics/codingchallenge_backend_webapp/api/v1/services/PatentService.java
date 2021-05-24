package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.List;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;

public interface PatentService {

	public List<PatentResponse> getAllPatents();
	
	public PatentResponse createPatent(PatentRequest patent);

	public PatentResponse updatePatent(PatentRequest patentRequest);
	
    public PatentResponse getPatentResponse(String publicationNumber);
    
    public Patent getPatent(String publicationNumber);
    
	public SuccessResponse deletePatent(String patentId);

}
