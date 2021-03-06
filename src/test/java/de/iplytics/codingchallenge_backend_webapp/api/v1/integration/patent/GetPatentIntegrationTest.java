package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.patent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.PatentController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
public class GetPatentIntegrationTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private PatentService patentService;
    
    public final String patentEndpoint = "/patents/";

    @Test
    public void getPatent_valid_200_success() throws Exception {
    	String patentId = "DE1234A1";
    	
        PatentResponse patentResponse = PatentResponse.builder()
                .publicationDate("2019-01-01")
                .publicationNumber("DE1234A1")
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();

        given(patentService.getPatentResponse(any())).willReturn(patentResponse);

        mvc.perform(get(patentEndpoint + patentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("title", is(patentResponse.getTitle())));
    }

    @Test
    public void getPatent_invalidID_404_notFound() throws Exception {
    	String patentId = "invalid";
    	
    	PatentNotFoundException patentNotFoundException = new PatentNotFoundException(patentId);
    	
        given(patentService.getPatentResponse(any())).willThrow(patentNotFoundException);

        mvc.perform(get(patentEndpoint + patentId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(patentNotFoundException.getMessage())));
    }
    
}