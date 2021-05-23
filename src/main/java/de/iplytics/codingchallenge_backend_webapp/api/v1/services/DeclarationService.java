package de.iplytics.codingchallenge_backend_webapp.api.v1.services;

import java.util.List;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;

public interface DeclarationService {

	public List<Declaration> getAllDeclarations();
    
	public DeclarationResponse createDeclaration(DeclarationRequest declarationRequest);
	
	public DeclarationResponse updateDeclaration(DeclarationRequest declarationRequest);
	
	public Declaration getDeclarationById(int id);
	
	public Declaration getDeclarationByPatentAndStandard(Patent patent, Standard standard);
	
	public List<Declaration> getDeclarationsByPatent(String publicationNumber);

	public List<Declaration> getDeclarationsByStandard(String standardId);

	public Declaration getDeclarationByPatentAndStandard(String publicationNumber, String standardId);
	
	public SuccessResponse deleteDeclarationById(int declarationId);

}
