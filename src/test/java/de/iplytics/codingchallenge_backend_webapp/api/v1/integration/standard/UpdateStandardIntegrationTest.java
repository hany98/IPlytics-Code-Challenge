package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.standard;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.StandardController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(StandardController.class)
public class UpdateStandardIntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private StandardService standardService;

    public final String standardEndpoint = "/standards/";
    
    @Test
    public void updateStandard_allFieldsValid_201_updated() throws Exception {
    	StandardRequest standardRequest = StandardRequest.builder()
    			.standardId("Standard1")
    			.name("Standard1 Name")
    			.description("Standard1 Description")
    			.build();
    	
        StandardResponse standardResponse = standardRequest.toStandardEntity().toStandardResponse();

        given(standardService.updateStandard(any())).willReturn(standardResponse);

        String requestBody = new Gson().toJson(standardRequest);
        
        mvc.perform(put(standardEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("standardId", is(standardResponse.getStandardId())))
                .andExpect(jsonPath("name", is(standardResponse.getName())))
                .andExpect(jsonPath("description", is(standardResponse.getDescription())));
    }
    
    @Test
    public void updateStandard_missingNonRequiredField_201_updated() throws Exception {
    	// Missing Non Required Field ->  Description
    	StandardRequest standardRequest = StandardRequest.builder()
    			.standardId("Standard1")
    			.name("Standard1 Name")
    			.description("")
    			.build();
    	
        StandardResponse standardResponse = standardRequest.toStandardEntity().toStandardResponse();
        
        given(standardService.updateStandard(any())).willReturn(standardResponse);

        String requestBody = new Gson().toJson(standardRequest);
        
        mvc.perform(put(standardEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.characterEncoding("utf-8"))
				.andExpect(status().isCreated())
				.andExpect(jsonPath("standardId", is(standardResponse.getStandardId())))
				.andExpect(jsonPath("name", is(standardResponse.getName())))
				.andExpect(jsonPath("description", is(standardResponse.getDescription())));
    }
    
    @Test
    public void updateStandard_missingRequiredField_400_badRequest() throws Exception {
    	// Missing Required Field Title
    	StandardRequest standardRequest = StandardRequest.builder()
    			.standardId("Standard1")
    			.name("")
    			.description("Standard1 Description")
    			.build();
    	
    	StandardEmptyFieldException standardEmptyFieldException = new StandardEmptyFieldException("name", String.class);
    	
        given(standardService.updateStandard(any())).willThrow(standardEmptyFieldException);

        String requestBody = new Gson().toJson(standardRequest);
        
        mvc.perform(put(standardEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is(standardEmptyFieldException.getMessage())));
    }

    @Test
    public void updateStandard_invalidID_404_notFound() throws Exception {
    	String standardId = "invalid";
    	
    	StandardRequest standardRequest = StandardRequest.builder()
    			.standardId(standardId)
    			.name("Standard1 Name")
    			.description("Standard1 Description")
    			.build();
    	
    	StandardNotFoundException standardNotFoundException = new StandardNotFoundException(standardId);
    	
        given(standardService.updateStandard(any())).willThrow(standardNotFoundException);

        String requestBody = new Gson().toJson(standardRequest);
        
        mvc.perform(put(standardEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(standardNotFoundException.getMessage())));
    }
    
}