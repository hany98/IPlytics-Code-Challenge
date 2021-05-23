package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.List;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;

public interface StandardService {

	public List<Standard> getAllStandards();
	
	public Standard createStandard(Standard standard);

	public Standard updateStandard(Standard modifiedStandard);
	
	public Standard getStandard(String standardId);
    
	public SuccessResponse deleteStandard(String standardId);
    
}
