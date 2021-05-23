package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.DeclarationRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.DeclarationUtils;

@Service
public class DeclarationService {

	@Autowired
    PatentService patentService;
	
	@Autowired
	StandardService standardService;
	
	@Autowired
    private DeclarationRepository declarationRepository;

	public List<Declaration> getAllDeclarations() {
		// Initiate a List to return the declarations
		List<Declaration> declarations = new ArrayList<Declaration>();
		
		// Fetch all the Declarations and fill them in the List
		declarationRepository.findAll().forEach(declaration -> declarations.add(declaration));
		
		return declarations;
	}
    
	public Declaration createDeclaration(String publicationNumber, String standardId) {
		// Validate Patent
		Patent patent = patentService.getPatent(publicationNumber);
		
		// Validate Standard
		Standard standard = standardService.getStandard(standardId);
		
		// Check for Duplicate Declaration by publicationNumber and standardId
		if(declarationRepository.findByPatentAndStandard(patent, standard).isPresent()) 
			throw new DeclarationAlreadyExistsException(publicationNumber, standardId);
		
		// Build Declaration
		Declaration declaration = Declaration.builder()
										.patent(patent)
										.standard(standard)
										.build();
		// Create Declaration
		return declarationRepository.save(declaration);
	}
	
	public Declaration updateDeclaration(Declaration declaration) {
		// Parse and Check Empty Required Fields
		DeclarationUtils.checkDeclarationRequiredFields(declaration);
		
		// Check if Patent exists (by ID)
		getDeclaration(declaration.getId());
		
		// Update Patent
		return declarationRepository.save(declaration);
	}
	
	public Declaration getDeclaration(int id){
    	// Fetch Declaration By ID with NOT_FOUND Error Handling
        return declarationRepository.findById(id)
        		.orElseThrow(() -> new DeclarationNotFoundException(id));
    }
	
	public void deleteDeclaration(int id) {
		// Check if Declaration exists (by ID) and Fetch
		Declaration declaration = getDeclaration(id);
		
		// Delete Patent By ID
		declarationRepository.delete(declaration);
	}

}
