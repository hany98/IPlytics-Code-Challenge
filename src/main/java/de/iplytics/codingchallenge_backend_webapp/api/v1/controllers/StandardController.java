package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.StandardRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.StandardResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.GlobalResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.StandardService;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.Constants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/standards")
public class StandardController {

	Logger logger = LoggerFactory.getLogger(StandardController.class);
	
    @Autowired
    StandardService standardService;
    
    @GetMapping("/")
    @ApiOperation(value = "Get All Standards")
    public ResponseEntity<List<StandardResponse>> getAllStandards(){
    	List<StandardResponse> standards = standardService.getAllStandards();
    	if(Constants.DEBUG) logger.debug("getAllStandards: " + standards.toString());
        return new ResponseEntity<List<StandardResponse>>(standards, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Standard")
    public ResponseEntity<StandardResponse> createStandard(@RequestBody StandardRequest standardRequest){
    	StandardResponse standardResponse = standardService.createStandard(standardRequest);
    	if(Constants.DEBUG) logger.debug("createStandard: " + standardResponse.toString());
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Standard")
    public ResponseEntity<StandardResponse> updateStandard(@RequestBody StandardRequest standardRequest){
    	StandardResponse standardResponse = standardService.updateStandard(standardRequest);
    	if(Constants.DEBUG) logger.debug("updateStandard: " + standardResponse.toString());
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/{standardId}")
    @ApiOperation(value = "Get Standard By ID")
    public ResponseEntity<StandardResponse> getStandard(@PathVariable("standardId") String standardId){
    	StandardResponse standardResponse = standardService.getStandardResponse(standardId);
    	if(Constants.DEBUG) logger.debug("getStandard: " + standardResponse.toString());
        return new ResponseEntity<StandardResponse>(standardResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{standardId}")
    @ApiOperation(value = "Delete Standard")
    public ResponseEntity<GlobalResponse> deleteStandard(@PathVariable("standardId") String standardId){
    	GlobalResponse response = standardService.deleteStandard(standardId);
    	response.setStatus(HttpStatus.ACCEPTED.name());
    	response.setStatusCode(HttpStatus.ACCEPTED.value());
    	if(Constants.DEBUG) logger.debug("deleteStandard: " + response.toString());
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
