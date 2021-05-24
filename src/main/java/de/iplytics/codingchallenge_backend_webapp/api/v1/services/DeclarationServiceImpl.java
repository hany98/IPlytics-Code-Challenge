package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.DeclarationRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.DeclarationUtils;

@Service
public class DeclarationServiceImpl implements DeclarationService {

	@Autowired
    PatentService patentService;
	
	@Autowired
	StandardService standardService;
	
	@Autowired
    private DeclarationRepository declarationRepository;

	public List<DeclarationResponse> getAllDeclarations() {
		// Initiate a List to return the declarations
		List<DeclarationResponse> declarations = new ArrayList<DeclarationResponse>();
		
		// Fetch all the Declarations and fill them in the List
		declarationRepository.findAll().forEach(declaration -> declarations.add(declaration.toDeclarationResponse()));
		
		return declarations;
	}
    
	public DeclarationResponse createDeclaration(DeclarationRequest declarationRequest) {
		// Parse and Check Empty Required Fields
		DeclarationUtils.checkDeclarationCreationRequiredFields(declarationRequest);
		
		// Validate Patent
		Patent patent = patentService.getPatent(declarationRequest.getPublicationNumber());
		
		// Validate Standard
		Standard standard = standardService.getStandard(declarationRequest.getStandardId());
		
		// Check for Duplicate Declaration by publicationNumber and standardId
		if(declarationRepository.findByPatentAndStandard(patent, standard).isPresent()) 
			throw new DeclarationAlreadyExistsException(declarationRequest);
		
		// Build Declaration
		Declaration declaration = Declaration.builder()
										.patent(patent)
										.standard(standard)
										.description(declarationRequest.getDescription())
										.creationDate(LocalDateTime.now())
										.modificationDate(null)
										.build();
		// Create Declaration
		return declarationRepository.save(declaration).toDeclarationResponse();
	}
	
	public DeclarationResponse updateDeclaration(DeclarationRequest declarationRequest) {
		// Parse and Check Empty Required Fields
		DeclarationUtils.checkDeclarationUpdatingRequiredFields(declarationRequest);
		
		// Validate Patent
		Patent patent = patentService.getPatent(declarationRequest.getPublicationNumber());
		
		// Validate Standard
		Standard standard = standardService.getStandard(declarationRequest.getStandardId());
		
		// Check if Declaration exists (by ID)
		Declaration declaration = getDeclarationByPatentAndStandard(patent, standard);
		
		// Update Declaration from DeclarationRequest
		DeclarationUtils.updateExistingFields(declaration, declarationRequest);
		
		// Update Declaration
		return declarationRepository.save(declaration).toDeclarationResponse();
	}
	
	public DeclarationResponse getDeclarationResponseById(int id) {
		return getDeclarationById(id).toDeclarationResponse();
    }
	
	public Declaration getDeclarationById(int id) {
    	// Fetch Declaration By ID with NOT_FOUND Error Handling
        return declarationRepository.findById(id)
        		.orElseThrow(() -> new DeclarationNotFoundException(id));
    }
	
	public Declaration getDeclarationByPatentAndStandard(Patent patent, Standard standard) {
    	// Fetch Declaration By Patent and Standard with NOT_FOUND Error Handling
        return declarationRepository.findByPatentAndStandard(patent, standard)
        		.orElseThrow(() -> new DeclarationNotFoundException(patent, standard));
    }
	
	public List<DeclarationResponse> getDeclarationsResponseByPatent(String publicationNumber) {
		// Initiate a List to return the declarations
		List<DeclarationResponse> declarations = new ArrayList<DeclarationResponse>();
		
		// Fetch from DB
		getDeclarationsByPatent(publicationNumber).forEach(declaration -> declarations.add(declaration.toDeclarationResponse()));
		
		return declarations;
	}
	
	public List<Declaration> getDeclarationsByPatent(String publicationNumber) {
		// Validate Patent
		Patent patent = patentService.getPatent(publicationNumber);
		
		// Fetch from DB
		return declarationRepository.findByPatent(patent);
	}

	public List<DeclarationResponse> getDeclarationsResponseByStandard(String standardId) {
		// Initiate a List to return the declarations
		List<DeclarationResponse> declarations = new ArrayList<DeclarationResponse>();
		
		// Fetch from DB
		getDeclarationsByStandard(standardId).forEach(declaration -> declarations.add(declaration.toDeclarationResponse()));
		
		return declarations;
	}
	
	public List<Declaration> getDeclarationsByStandard(String standardId) {
		// Validate Standard
		Standard standard = standardService.getStandard(standardId);
		
		// Fetch from DB
		return declarationRepository.findByStandard(standard);
	}

	public DeclarationResponse getDeclarationResponseByPatentAndStandard(String publicationNumber, String standardId) {
		return getDeclarationByPatentAndStandard(publicationNumber, standardId).toDeclarationResponse();
	}
	
	public Declaration getDeclarationByPatentAndStandard(String publicationNumber, String standardId) {
		// Validate Patent
		Patent patent = patentService.getPatent(publicationNumber);
		
		// Validate Standard
		Standard standard = standardService.getStandard(standardId);
		
		return getDeclarationByPatentAndStandard(patent, standard);
	}
	
	public SuccessResponse deleteDeclarationById(int declarationId) {
		// Check if Declaration exists (by ID) and Fetch
		Declaration declaration = getDeclarationById(declarationId);
		
		// Delete Patent By ID
		declarationRepository.delete(declaration);
		
		return new SuccessResponse("Deleted Declaration of ID: " + declarationId);
	}

}
