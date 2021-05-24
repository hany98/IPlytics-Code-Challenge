package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.patent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.PatentController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.SuccessResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
public class DeletePatentIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatentService patentService;
    
    public final String patentEndpoint = "/patents/";

    @Test
    public void deletePatent_valid_202_accepted() throws Exception {
    	String patentId = "US1234A1";
    	
    	SuccessResponse successResponse = new SuccessResponse("Deleted Patent of ID: " + patentId);
        		
        given(patentService.deletePatent(any())).willReturn(successResponse);
        
        mvc.perform(delete(patentEndpoint + patentId))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("message", is(successResponse.getMessage())));
    }
    
    @Test
    public void deletePatent_invalidId_404_notFound() throws Exception {
    	String patentId = "invalid";
    	
    	PatentNotFoundException patentNotFoundException = new PatentNotFoundException(patentId);
        		
    	given(patentService.deletePatent(any())).willThrow(patentNotFoundException);
        
        mvc.perform(delete(patentEndpoint + patentId))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(patentNotFoundException.getMessage())));
    }

}