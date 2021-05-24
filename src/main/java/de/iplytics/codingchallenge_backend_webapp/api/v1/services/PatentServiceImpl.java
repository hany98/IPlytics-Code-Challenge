package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentLinkedToDeclarationsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.PatentUtils;

@Service
public class PatentServiceImpl implements PatentService {

	@Autowired
    DeclarationService declarationService;
	
	@Autowired
    private PatentRepository patentRepository;

	public List<PatentResponse> getAllPatents() {
		// Initiate a List to return the patents
		List<PatentResponse> patents = new ArrayList<PatentResponse>();
		
		// Fetch all the Patents and fill them in the List
		patentRepository.findAll().forEach(patent -> patents.add(patent.toPatentResponse()));
		
		return patents;
	}
	
	public PatentResponse createPatent(PatentRequest patentRequest) {
		// Convert to Entity
		Patent patent = patentRequest.toPatentEntity();
		
		// Parse and Check Empty Required Fields
		PatentUtils.checkPatentCreationRequiredFields(patent);
		
		// Check for Duplicate Patent ID
		if(patentRepository.findById(patent.getPublicationNumber()).isPresent()) 
			throw new PatentIDAlreadyExistsException(patent.getPublicationNumber());
		
		// Create Patent
		return patentRepository.save(patent).toPatentResponse();
	}

	public PatentResponse updatePatent(PatentRequest patentRequest) {
		// Convert to Entity
		Patent patent = patentRequest.toPatentEntity();
		
		// Parse and Check Empty Required Fields
		PatentUtils.checkPatentUpdatingRequiredFields(patent);
		
		// Check if Patent exists (by ID)
		Patent oldPatent = getPatent(patent.getPublicationNumber());
		
		// Update Old Patent
		PatentUtils.updateExistingFields(oldPatent, patent);
		
		// Update Patent
		return patentRepository.save(oldPatent).toPatentResponse();
	}
	
	public PatentResponse getPatentResponse(String publicationNumber) {
        return getPatent(publicationNumber).toPatentResponse();
    }
	
    public Patent getPatent(String publicationNumber) {
    	// Fetch Patent By ID with NOT_FOUND Error Handling
        return patentRepository.findById(publicationNumber)
        		.orElseThrow(() -> new PatentNotFoundException(publicationNumber));
    }
    
	public SuccessResponse deletePatent(String patentId) {
		// Check Existing Declarations using this patentId
		List<Declaration> declarations = declarationService.getDeclarationsByPatent(patentId);
		
		// Throw PatentLinkedToDeclarationException
		if(declarations.size() > 0)
			throw new PatentLinkedToDeclarationsException();
		
		// Check if Patent exists (by ID) and Fetch
		Patent patent = getPatent(patentId);
		
		// Delete Patent By ID
		patentRepository.delete(patent);
		
		return new SuccessResponse("Deleted Patent of ID: " + patentId);
	}

}
