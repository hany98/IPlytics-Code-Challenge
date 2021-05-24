package de.iplytics.codingchallenge_backend_webapp.api.v1.unit.patent;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.DeclarationController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.DeclarationRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;

@RunWith(SpringRunner.class)
@WebMvcTest(DeclarationController.class)
public class DeclarationUnitTest {

    @MockBean
    private DeclarationService declarationService;
    
    @MockBean
    private DeclarationRepository declarationRepository;

    @Test
    public void getDeclaration() {
    	int declarationId = 1;
    	
    	Patent patent = Patent.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1))
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
    	
    	Standard standard = Standard.builder()
                .standardId("Standard1")
                .name("Standard1 Name")
                .description("Standard1 Description")
                .build();
    	
    	Declaration declaration = Declaration.builder()
                .patent(patent)
                .standard(standard)
                .description("Declaration1 Description")
                .build();
    	
    	Mockito.when(declarationService.getDeclarationById(declarationId)).thenReturn(declaration);
    	
    	Declaration testDeclaration = declarationService.getDeclarationById(declarationId);
    	
    	Assert.assertEquals(declaration.getPatent().getPublicationNumber(), testDeclaration.getPatent().getPublicationNumber());
    	Assert.assertEquals(declaration.getStandard().getStandardId(), testDeclaration.getStandard().getStandardId());
    }
    
    @Test
    public void createDeclaration() throws Exception {
    	Patent patent = Patent.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1))
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
    	
    	Standard standard = Standard.builder()
                .standardId("Standard1")
                .name("Standard1 Name")
                .description("Standard1 Description")
                .build();
    	
    	Declaration declaration = Declaration.builder()
                .patent(patent)
                .standard(standard)
                .description("Declaration1 Description")
                .build();
    	
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
                .publicationNumber(patent.getPublicationNumber())
                .standardId(standard.getStandardId())
                .description(declaration.getDescription())
                .build();
    	
    	DeclarationResponse declarationResponse = declaration.toDeclarationResponse();
    	
    	Mockito.when(declarationService.createDeclaration(declarationRequest)).thenReturn(declarationResponse);
    	
    	DeclarationResponse declarationResponseTest = declarationService.createDeclaration(declarationRequest);
    	
    	Assert.assertEquals(declarationResponseTest.getPatent().getPublicationNumber(), declarationResponse.getPatent().getPublicationNumber());
    	Assert.assertEquals(declarationResponseTest.getStandard().getStandardId(), declarationResponse.getStandard().getStandardId());
    }
    
}