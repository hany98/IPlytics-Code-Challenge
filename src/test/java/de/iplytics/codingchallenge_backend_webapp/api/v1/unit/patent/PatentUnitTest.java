package de.iplytics.codingchallenge_backend_webapp.api.v1.unit.patent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.PatentController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.PatentRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@WebMvcTest(PatentController.class)
public class PatentUnitTest {

    @MockBean
    private PatentService patentService;
    
    @MockBean
    private PatentRepository patentRepository;

    @Test
    public void getPatent() {
    	String patentId = "DE1234A1";
    	
    	Patent patent = Patent.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1))
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
    	
    	Mockito.when(patentService.getPatent(patentId)).thenReturn(patent);
    	
    	Patent testPatent = patentService.getPatent(patentId);
    	
    	Assert.assertEquals(patent.getPublicationNumber(), testPatent.getPublicationNumber());
    }
    
    @Test
    public void createPatent() throws Exception {
    	PatentRequest patentRequest = PatentRequest.builder()
                .publicationNumber("DE1234A1")
                .publicationDate(LocalDate.of(2019,1,1).toString())
                .description("Description of how to make cheese")
                .title("Method of making cheese")
                .build();
    	
    	Patent patent = patentRequest.toPatentEntity();
    	
    	PatentResponse patentResponse = patent.toPatentResponse();
    	
    	Mockito.when(patentService.createPatent(patentRequest)).thenReturn(patentResponse);
    	
    	PatentResponse patentResponseTest = patentService.createPatent(patentRequest);
    	
    	Assert.assertEquals(patentResponseTest.getPublicationNumber(), patentRequest.getPublicationNumber());
    }
    
}