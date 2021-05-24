package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.GlobalResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/standards")
public class StandardController {

    @Autowired
    StandardService standardService;
    
    @GetMapping("/")
    @ApiOperation(value = "Get All Standards")
    public ResponseEntity<List<StandardResponse>> getAllStandards(){
    	List<StandardResponse> standards = standardService.getAllStandards();
        return new ResponseEntity<List<StandardResponse>>(standards, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Standard")
    public ResponseEntity<StandardResponse> createStandard(@RequestBody StandardRequest standardRequest){
    	StandardResponse standardResponse = standardService.createStandard(standardRequest);
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Standard")
    public ResponseEntity<StandardResponse> updateStandard(@RequestBody StandardRequest standardRequest){
    	StandardResponse standardResponse = standardService.updateStandard(standardRequest);
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/{standardId}")
    @ApiOperation(value = "Get Standard By ID")
    public ResponseEntity<StandardResponse> getStandard(@PathVariable("standardId") String standardId){
    	StandardResponse standardResponse = standardService.getStandardResponse(standardId);
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{standardId}")
    @ApiOperation(value = "Delete Standard")
    public ResponseEntity<GlobalResponse> deleteStandard(@PathVariable("standardId") String standardId){
    	GlobalResponse response = standardService.deleteStandard(standardId);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
