package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.patent;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.PatentController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;

import java.time.LocalDate;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
public class CreatePatentIntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private PatentService patentService;

    public final String patentEndpoint = "/patents/";
    
    @Test
    public void createPatent_allFieldsValid_201_created() throws Exception {
    	PatentRequest patentRequest = PatentRequest.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1).toString())
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
        
        PatentResponse patentResponse = patentRequest.toPatentEntity().toPatentResponse();

        given(patentService.createPatent(any())).willReturn(patentResponse);

        String requestBody = new Gson().toJson(patentRequest);
        
        mvc.perform(post(patentEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("publicationDate", is(patentResponse.getPublicationDate())))
                .andExpect(jsonPath("publicationNumber", is(patentResponse.getPublicationNumber())))
                .andExpect(jsonPath("description", is(patentResponse.getDescription())))
        		.andExpect(jsonPath("title", is(patentResponse.getTitle())));
    }
    
    @Test
    public void createPatent_missingNonRequiredField_201_created() throws Exception {
    	// Missing Non Required Field ->  Description
    	PatentRequest patentRequest = PatentRequest.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1).toString())
                .description("")
                .title("Method of making cheese")
                .build();
        
        PatentResponse patentResponse = patentRequest.toPatentEntity().toPatentResponse();

        given(patentService.createPatent(any())).willReturn(patentResponse);

        String requestBody = new Gson().toJson(patentRequest);
        
        mvc.perform(post(patentEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.characterEncoding("utf-8"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("publicationDate", is(patentResponse.getPublicationDate())))
				.andExpect(jsonPath("publicationNumber", is(patentResponse.getPublicationNumber())))
				.andExpect(jsonPath("description", is(patentResponse.getDescription())))
				.andExpect(jsonPath("title", is(patentResponse.getTitle())));
    }
    
    @Test
    public void createPatent_missingRequiredField_400_badRequest() throws Exception {
    	// Missing Required Field Title
    	PatentRequest patentRequest = PatentRequest.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1).toString())
                .description("Description of how to make cheese")
                .title("")
                .build();
        
    	PatentEmptyFieldException patentEmptyFieldException = new PatentEmptyFieldException("title", String.class);
    	
        given(patentService.createPatent(any())).willThrow(patentEmptyFieldException);

        String requestBody = new Gson().toJson(patentRequest);
        
        mvc.perform(post(patentEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is(patentEmptyFieldException.getMessage())));
    }

    @Test
    public void createPatent_invalidID_404_notFound() throws Exception {
    	String patentId = "invalid";
    	
    	PatentRequest patentRequest = PatentRequest.builder()
                .publicationNumber(patentId)
                .publicationDate(LocalDate.of(2019,1,1).toString())
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
        
    	PatentNotFoundException patentNotFoundException = new PatentNotFoundException(patentId);
    	
        given(patentService.createPatent(any())).willThrow(patentNotFoundException);

        String requestBody = new Gson().toJson(patentRequest);
        
        mvc.perform(post(patentEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(patentNotFoundException.getMessage())));
    }
    
}