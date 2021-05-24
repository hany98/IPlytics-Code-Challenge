package de.iplytics.codingchallenge_backend_webapp.api.v1.unit.patent;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import de.iplytics.codingchallenge_backend_webapp.api.v1.controllers.StandardController;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.repositories.StandardRepository;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;

@RunWith(SpringRunner.class)
@WebMvcTest(StandardController.class)
public class StandardUnitTest {

    @MockBean
    private StandardService standardService;
    
    @MockBean
    private StandardRepository standardRepository;

    @Test
    public void getStandard() {
    	String standardId = "DE1234A1";
    	
    	Standard standard = Standard.builder()
                .standardId("Standard1")
                .name("Standard1 Name")
                .description("Standard1 Description")
                .build();
    	
    	Mockito.when(standardService.getStandard(standardId)).thenReturn(standard);
    	
    	Standard testStandard = standardService.getStandard(standardId);
    	
    	Assert.assertEquals(standard.getStandardId(), testStandard.getStandardId());
    }
    
    @Test
    public void createStandard() throws Exception {
    	StandardRequest standardRequest = StandardRequest.builder()
                .standardId("Standard1")
                .name("Standard1 Name")
                .description("Standard1 Description")
                .build();
    	
    	Standard standard = standardRequest.toStandardEntity();
    	
    	StandardResponse standardResponse = standard.toStandardResponse();
    	
    	Mockito.when(standardService.createStandard(standardRequest)).thenReturn(standardResponse);
    	
    	StandardResponse standardResponseTest = standardService.createStandard(standardRequest);
    	
    	Assert.assertEquals(standardResponseTest.getStandardId(), standardRequest.getStandardId());
    }
    
}