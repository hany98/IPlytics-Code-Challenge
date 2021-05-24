package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.declaration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.DeclarationController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@WebMvcTest(DeclarationController.class)
public class GetDeclarationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeclarationService declarationService;
    
    public final String declarationEndpoint = "/declarations/";

    @Test
    public void getDeclaration_valid_200_success() throws Exception {
    	String declarationId = "1";
    	
    	Patent patent = Patent.builder()
    			.publicationNumber("Patent1")
    			.publicationDate(LocalDate.of(2019,1,1))
    			.title("Patent1 Title")
    			.description("Patent1 Description")
    			.build();
    	
    	Standard standard = Standard.builder()
                .standardId("Standard1")
                .name("Standard1 Name")
                .description("Standard1 Description")
                .build();
    	
        DeclarationResponse declarationResponse = DeclarationResponse.builder()
        		.patent(patent)
        		.standard(standard)
        		.description("Declaration1 Description")
                .build();

        given(declarationService.getDeclarationResponseById(anyInt())).willReturn(declarationResponse);

        mvc.perform(get(declarationEndpoint + declarationId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("description", is(declarationResponse.getDescription())))
                .andExpect(jsonPath("patent.publicationNumber", is(declarationResponse.getPatent().getPublicationNumber())))
                .andExpect(jsonPath("standard.standardId", is(declarationResponse.getStandard().getStandardId())));
    }

    @Test
    public void getDeclaration_invalidID_404_notFound() throws Exception {
    	int declarationId = -1;
    	
    	DeclarationNotFoundException declarationNotFoundException = new DeclarationNotFoundException(declarationId);
    	
        given(declarationService.getDeclarationResponseById(anyInt())).willThrow(declarationNotFoundException);

        mvc.perform(get(declarationEndpoint + declarationId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(declarationNotFoundException.getMessage())));
    }
    
}