package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardLinkedToDeclarationsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.StandardRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.StandardUtils;

@Service
public class StandardServiceImpl implements StandardService {

	@Autowired
    DeclarationService declarationService;
	
	@Autowired
    private StandardRepository standardRepository;

	public List<StandardResponse> getAllStandards() {
		// Initiate a List to return the standards
		List<StandardResponse> standards = new ArrayList<StandardResponse>();
		
		// Fetch all the Standards and fill them in the List
		standardRepository.findAll().forEach(standard -> standards.add(standard.toStandardResponse()));
		
		return standards;
	}
	
	public StandardResponse createStandard(StandardRequest standardRequest) {
		// Convert to Entity
		Standard standard = standardRequest.toStandardEntity();
		
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardCreationRequiredFields(standard);
		
		// Check for Duplicate Standard ID
		if(standardRepository.findById(standard.getStandardId()).isPresent()) 
			throw new StandardIDAlreadyExistsException(standard.getStandardId());
		
		// Create Standard
		return standardRepository.save(standard).toStandardResponse();
	}

	public StandardResponse updateStandard(StandardRequest standardRequest) {
		// Convert to Entity
		Standard standard = standardRequest.toStandardEntity();
		
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardUpdatingRequiredFields(standard);
		
		// Check if Standard exists (by ID)
		Standard oldStandard = getStandard(standard.getStandardId());
		
		// Update Old Patent
		StandardUtils.updateExistingFields(oldStandard, standard);
		
		// Update Patent
		return standardRepository.save(oldStandard).toStandardResponse();
	}
	
	public StandardResponse getStandardResponse(String standardId) {
        return getStandard(standardId).toStandardResponse();
    }
	
	public Standard getStandard(String standardId){
    	// Fetch Standard By ID with NOT_FOUND Error Handling
        return standardRepository.findById(standardId)
        		.orElseThrow(() -> new StandardNotFoundException(standardId));
    }
    
	public SuccessResponse deleteStandard(String standardId) {
		// Check Existing Declarations using this patentId
		List<Declaration> declarations = declarationService.getDeclarationsByStandard(standardId);
		
		// Throw PatentLinkedToDeclarationException
		if(declarations.size() > 0)
			throw new StandardLinkedToDeclarationsException();
		
		// Check if Standard exists (by ID) and Fetch
		Standard standard = getStandard(standardId);
		
		// Delete Standard By ID
		standardRepository.delete(standard);
		
		return new SuccessResponse("Deleted Standard of ID: " + standardId);
	}
    
}
