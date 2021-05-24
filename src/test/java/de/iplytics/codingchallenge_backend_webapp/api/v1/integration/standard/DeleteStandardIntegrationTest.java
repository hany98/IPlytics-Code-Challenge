package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.standard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.StandardController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StandardController.class)
public class DeleteStandardIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StandardService standardService;
    
    public final String standardEndpoint = "/standards/";

    @Test
    public void deleteStandard_valid_202_accepted() throws Exception {
    	String standardId = "US1234A1";
    	
    	SuccessResponse successResponse = new SuccessResponse("Deleted Standard of ID: " + standardId);
        		
        given(standardService.deleteStandard(any())).willReturn(successResponse);
        
        mvc.perform(delete(standardEndpoint + standardId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("message", is(successResponse.getMessage())));
    }
    
    @Test
    public void deleteStandard_invalidId_404_notFound() throws Exception {
    	String standardId = "invalid";
    	
    	StandardNotFoundException standardNotFoundException = new StandardNotFoundException(standardId);
        		
    	given(standardService.deleteStandard(any())).willThrow(standardNotFoundException);
        
        mvc.perform(delete(standardEndpoint + standardId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(standardNotFoundException.getMessage())));
    }

}