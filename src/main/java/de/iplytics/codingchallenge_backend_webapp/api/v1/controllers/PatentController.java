package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.PatentRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.PatentResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.GlobalResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.PatentService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/patents")
public class PatentController {

    @Autowired
    PatentService patentService;
    
    @GetMapping("/")
    @ApiOperation(value = "Get All Patents")
    public ResponseEntity<List<PatentResponse>> getAllPatents(){
    	List<PatentResponse> patents = patentService.getAllPatents();
        return new ResponseEntity<List<PatentResponse>>(patents, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Patent")
    public ResponseEntity<PatentResponse> createPatent(@RequestBody PatentRequest patentRequest){
    	PatentResponse patentResponse = patentService.createPatent(patentRequest);
        return new ResponseEntity<PatentResponse>(patentResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Patent")
    public ResponseEntity<PatentResponse> updatePatent(@RequestBody PatentRequest patentRequest){
    	PatentResponse patentResponse = patentService.updatePatent(patentRequest);
        return new ResponseEntity<PatentResponse>(patentResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/{publicationNumber}")
    @ApiOperation(value = "Get Patent By ID")
    public ResponseEntity<PatentResponse> getPatent(@PathVariable("publicationNumber") String publicationNumber){
    	PatentResponse patentResponse = patentService.getPatentResponse(publicationNumber);
        return new ResponseEntity<PatentResponse>(patentResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{publicationNumber}")
    @ApiOperation(value = "Delete Patent")
    public ResponseEntity<GlobalResponse> deletePatent(@PathVariable("publicationNumber") String publicationNumber){
    	GlobalResponse response = patentService.deletePatent(publicationNumber);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
