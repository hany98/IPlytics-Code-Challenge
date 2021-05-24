package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.List;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;

public interface StandardService {

	public List<StandardResponse> getAllStandards();
	
	public StandardResponse createStandard(StandardRequest standardRequest);

	public StandardResponse updateStandard(StandardRequest standardRequest);
	
	public StandardResponse getStandardResponse(String standardId);
	
	public Standard getStandard(String standardId);
    
	public SuccessResponse deleteStandard(String standardId);
    
}
