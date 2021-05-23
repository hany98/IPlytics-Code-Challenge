package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.List;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;

public interface PatentService {

	public List<Patent> getAllPatents();
	
	public Patent createPatent(Patent patent);

	public Patent updatePatent(Patent modifiedPatent);
	
    public Patent getPatent(String publicationNumber);
    
	public SuccessResponse deletePatent(String patentId);

}
