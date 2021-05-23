package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.StandardRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.StandardUtils;

@Service
public class StandardService {

	@Autowired
    private StandardRepository standardRepository;

	public List<Standard> getAllStandards() {
		// Initiate a List to return the standards
		List<Standard> standards = new ArrayList<Standard>();
		
		// Fetch all the Standards and fill them in the List
		standardRepository.findAll().forEach(standard -> standards.add(standard));
		
		return standards;
	}
	
	public Standard createSingleStandard(Standard standard) {
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardRequiredFields(standard);
		
		// Check for Duplicate Standard ID
		if(standardRepository.findById(standard.getStandardId()).isPresent()) 
			throw new StandardIDAlreadyExistsException(standard.getStandardId());
		
		// Create Standard
		return standardRepository.save(standard);
	}

	public Standard updateSingleStandard(Standard standard) {
		// Parse and Check Empty Required Fields
		StandardUtils.checkStandardRequiredFields(standard);
		
		// Check if Standard exists (by ID)
		getStandard(standard.getStandardId());
		
		// Update Standard
		return standardRepository.save(standard);
	}
	
	public Standard getStandard(String standardId){
    	// Fetch Standard By ID with NOT_FOUND Error Handling
        return standardRepository.findById(standardId)
        		.orElseThrow(() -> new StandardNotFoundException(standardId));
    }
    
	public void deleteSingleStandard(String id) {
		// Check if Standard exists (by ID) and Fetch
		Standard standard = getStandard(id);
		
		// Delete Standard By ID
		standardRepository.delete(standard);
	}
    
}
