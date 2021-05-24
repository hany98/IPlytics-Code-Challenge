package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.declaration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.DeclarationController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DeclarationController.class)
public class DeleteDeclarationIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private DeclarationService declarationService;
    
    public final String declarationEndpoint = "/declarations/";

    @Test
    public void deleteDeclaration_valid_202_accepted() throws Exception {
    	int declarationId = 1;
    	
    	SuccessResponse successResponse = new SuccessResponse("Deleted Declaration of ID: " + declarationId);
        		
        given(declarationService.deleteDeclarationById(anyInt())).willReturn(successResponse);
        
        mvc.perform(delete(declarationEndpoint + declarationId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("message", is(successResponse.getMessage())));
    }
    
    @Test
    public void deleteDeclaration_invalidId_404_notFound() throws Exception {
    	int declarationId = -1;
    	
    	DeclarationNotFoundException declarationNotFoundException = new DeclarationNotFoundException(declarationId);
        		
    	given(declarationService.deleteDeclarationById(anyInt())).willThrow(declarationNotFoundException);
        
        mvc.perform(delete(declarationEndpoint + declarationId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(declarationNotFoundException.getMessage())));
    }

}