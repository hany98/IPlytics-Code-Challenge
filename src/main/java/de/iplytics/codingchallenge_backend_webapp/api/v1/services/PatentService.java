package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.PatentUtils;

@Service
public class PatentService {

	@Autowired
    private PatentRepository patentRepository;

	public List<Patent> getAllPatents() {
		// Initiate a List to return the patents
		List<Patent> patents = new ArrayList<Patent>();
		
		// Fetch all the Patents and fill them in the List
		patentRepository.findAll().forEach(patent -> patents.add(patent));
		
		return patents;
	}
	
	public Patent createPatent(Patent patent) {
		// Parse and Check Empty Required Fields
		PatentUtils.checkPatentCreationRequiredFields(patent);
		
		// Check for Duplicate Patent ID
		if(patentRepository.findById(patent.getPublicationNumber()).isPresent()) 
			throw new PatentIDAlreadyExistsException(patent.getPublicationNumber());
		
		// Create Patent
		return patentRepository.save(patent);
	}

	public Patent updatePatent(Patent modifiedPatent) {
		// Parse and Check Empty Required Fields
		PatentUtils.checkPatentUpdatingRequiredFields(modifiedPatent);
		
		// Check if Patent exists (by ID)
		Patent oldPatent = getPatent(modifiedPatent.getPublicationNumber());
		
		// Update Old Patent
		PatentUtils.updateExistingFields(oldPatent, modifiedPatent);
		
		// Update Patent
		return patentRepository.save(oldPatent);
	}
	
    public Patent getPatent(String publicationNumber){
    	// Fetch Patent By ID with NOT_FOUND Error Handling
        return patentRepository.findById(publicationNumber)
        		.orElseThrow(() -> new PatentNotFoundException(publicationNumber));
    }
    
	public SuccessResponse deletePatent(String patentId) {
		// Check if Patent exists (by ID) and Fetch
		Patent patent = getPatent(patentId);
		
		// Delete Patent By ID
		patentRepository.delete(patent);
		
		return new SuccessResponse("Deleted Patent of ID: " + patentId);
	}

}
