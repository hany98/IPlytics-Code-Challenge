package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.standard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.StandardController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StandardController.class)
public class GetStandardIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private StandardService standardService;
    
    public final String standardEndpoint = "/standards/";

    @Test
    public void getStandard_valid_200_success() throws Exception {
    	String standardId = "Standard1";
    	
        StandardResponse standardResponse = StandardResponse.builder()
        		.standardId("Standard1")
        		.name("Standard1 name")
        		.description("Standard1 Description")
                .build();

        given(standardService.getStandardResponse(any())).willReturn(standardResponse);

        mvc.perform(get(standardEndpoint + standardId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("name", is(standardResponse.getName())));
    }

    @Test
    public void getStandard_invalidID_404_notFound() throws Exception {
    	String standardId = "invalid";
    	
    	StandardNotFoundException standardNotFoundException = new StandardNotFoundException(standardId);
    	
        given(standardService.getStandardResponse(any())).willThrow(standardNotFoundException);

        mvc.perform(get(standardEndpoint + standardId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(standardNotFoundException.getMessage())));
    }
    
}