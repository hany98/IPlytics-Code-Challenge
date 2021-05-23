package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.StandardRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.StandardUtils;

@Service
public class StandardServiceImpl implements StandardService {

	@Autowired
    private StandardRepository standardRepository;

	public List<Standard> getAllStandards() {
		// Initiate a List to return the standards
		List<Standard> standards = new ArrayList<Standard>();
		
		// Fetch all the Standards and fill them in the List
		standardRepository.findAll().forEach(standard -> standards.add(standard));
		
		return standards;
	}
	
	public Standard createStandard(Standard standard) {
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardCreationRequiredFields(standard);
		
		// Check for Duplicate Standard ID
		if(standardRepository.findById(standard.getStandardId()).isPresent()) 
			throw new StandardIDAlreadyExistsException(standard.getStandardId());
		
		// Create Standard
		return standardRepository.save(standard);
	}

	public Standard updateStandard(Standard modifiedStandard) {
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardUpdatingRequiredFields(modifiedStandard);
		
		// Check if Standard exists (by ID)
		Standard oldStandard = getStandard(modifiedStandard.getStandardId());
		
		// Update Old Patent
		StandardUtils.updateExistingFields(oldStandard, modifiedStandard);
		
		// Update Patent
		return standardRepository.save(oldStandard);
	}
	
	public Standard getStandard(String standardId){
    	// Fetch Standard By ID with NOT_FOUND Error Handling
        return standardRepository.findById(standardId)
        		.orElseThrow(() -> new StandardNotFoundException(standardId));
    }
    
	public SuccessResponse deleteStandard(String standardId) {
		// Check if Standard exists (by ID) and Fetch
		Standard standard = getStandard(standardId);
		
		// Delete Standard By ID
		standardRepository.delete(standard);
		
		return new SuccessResponse("Deleted Standard of ID: " + standardId);
	}
    
}
