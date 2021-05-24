package de.iplytics.codingchallenge_backend_webapp.api.v1.integration.declaration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.google.gson.Gson;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.DeclarationController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;

import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@WebMvcTest(DeclarationController.class)
public class CreateDeclarationIntegrationTest {

    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private DeclarationService declarationService;

    public final String declarationEndpoint = "/declarations/";
    
    @Test
    public void createDeclaration_allFieldsValid_201_created() throws Exception {
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
    	
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
                .publicationNumber(patent.getPublicationNumber())
                .standardId(standard.getStandardId())
                .description("Declaration1 Description")
                .build();
        
        DeclarationResponse declarationResponse = DeclarationResponse.builder()
        		.patent(patent)
        		.standard(standard)
        		.description(declarationRequest.getDescription())
                .build();

        given(declarationService.createDeclaration(any())).willReturn(declarationResponse);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
                .characterEncoding("utf-8"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("patent.publicationNumber", is(declarationRequest.getPublicationNumber())))
                .andExpect(jsonPath("standard.standardId", is(declarationRequest.getStandardId())))
                .andExpect(jsonPath("description", is(declarationRequest.getDescription())));
    }
    
    @Test
    public void createDeclaration_missingNonRequiredField_201_created() throws Exception {
    	// Missing Non Required Field ->  Description
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
    	
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
                .publicationNumber(patent.getPublicationNumber())
                .standardId(standard.getStandardId())
                .description("")
                .build();
        
        DeclarationResponse declarationResponse = DeclarationResponse.builder()
        		.patent(patent)
        		.standard(standard)
        		.description(declarationRequest.getDescription())
                .build();
        
        given(declarationService.createDeclaration(any())).willReturn(declarationResponse);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
				.content(requestBody)
				.characterEncoding("utf-8"))
				.andExpect(status().isCreated())
                .andExpect(jsonPath("patent.publicationNumber", is(declarationRequest.getPublicationNumber())))
                .andExpect(jsonPath("standard.standardId", is(declarationRequest.getStandardId())))
                .andExpect(jsonPath("description", is(declarationRequest.getDescription())));
    }
    
    @Test
    public void createDeclaration_missingRequiredField_400_badRequest() throws Exception {
    	// Missing Required Field Title
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
                .publicationNumber("")
                .standardId("Standard1")
                .description("Declaration1 Description")
                .build();
        
    	DeclarationEmptyFieldException declarationEmptyFieldException = new DeclarationEmptyFieldException("publicationNumber", String.class);
    	
        given(declarationService.createDeclaration(any())).willThrow(declarationEmptyFieldException);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("message", is(declarationEmptyFieldException.getMessage())));
    }

    @Test
    public void createDeclaration_duplicatePatentAndStandard_409_conflict() throws Exception {
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
                .publicationNumber("Patent1")
                .standardId("Standard1")
                .description("Declaration1 Description")
                .build();
        
    	DeclarationAlreadyExistsException declarationAlreadyExistsException = new DeclarationAlreadyExistsException(declarationRequest);
        given(declarationService.createDeclaration(any())).willThrow(declarationAlreadyExistsException);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isConflict())
                .andExpect(jsonPath("message", is(declarationAlreadyExistsException.getMessage())));
    }
    
    @Test
    public void createDeclaration_invalidPatentID_404_notFound() throws Exception {
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
    			.publicationNumber("Invalid")
    			.standardId("Standard1")
    			.description("Declaration1 Description")
    			.build();
    	
    	PatentNotFoundException patentNotFoundException = new PatentNotFoundException(declarationRequest.getPublicationNumber());
    	
        given(declarationService.createDeclaration(declarationRequest)).willThrow(patentNotFoundException);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(patentNotFoundException.getMessage())));
    }
    
    @Test
    public void createDeclaration_invalidStandardID_404_notFound() throws Exception {
    	DeclarationRequest declarationRequest = DeclarationRequest.builder()
    			.publicationNumber("Patent1")
    			.standardId("Invalid")
    			.description("Declaration1 Description")
    			.build();
    	
    	StandardNotFoundException standardNotFoundException = new StandardNotFoundException(declarationRequest.getStandardId());
    	
        given(declarationService.createDeclaration(any())).willThrow(standardNotFoundException);

        String requestBody = new Gson().toJson(declarationRequest);
        
        mvc.perform(post(declarationEndpoint)
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody)
				.characterEncoding("utf-8"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("message", is(standardNotFoundException.getMessage())));
    }
    
}