package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.GlobalResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.Constants;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {
	
	Logger logger = LoggerFactory.getLogger(DeclarationController.class);

    @Autowired
    DeclarationService declarationService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Declarations")
    public ResponseEntity<List<DeclarationResponse>> getAllDeclarations(){
    	List<DeclarationResponse> declarations = declarationService.getAllDeclarations();
    	if(Constants.DEBUG) logger.debug("getAllDeclarations: " + declarations.toString());
        return new ResponseEntity<List<DeclarationResponse>>(declarations, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Declaration")
    public ResponseEntity<DeclarationResponse> createDeclaration(@RequestBody DeclarationRequest declarationRequest){
    	DeclarationResponse declarationResponse = declarationService.createDeclaration(declarationRequest);
    	if(Constants.DEBUG) logger.debug("createDeclaration: " + declarationResponse.toString());
        return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Declaration")
    public ResponseEntity<DeclarationResponse> updateDeclaration(@RequestBody DeclarationRequest declarationRequest){
    	DeclarationResponse declarationResponse = declarationService.updateDeclaration(declarationRequest);
    	if(Constants.DEBUG) logger.debug("updateDeclaration: " + declarationResponse.toString());
        return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Declaration by ID")
    public ResponseEntity<DeclarationResponse> getDeclarationById(@PathVariable("id") int id){
    	DeclarationResponse declarationResponse = declarationService.getDeclarationResponseById(id);
    	if(Constants.DEBUG) logger.debug("getDeclarationById: " + declarationResponse.toString());
        return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.OK);
    }
    
    @GetMapping("/patent/{publicationNumber}")
    @ApiOperation(value = "Get Declaration by Patent")
    public ResponseEntity<List<DeclarationResponse>> getDeclarationByPatent(@PathVariable("publicationNumber") String publicationNumber){
    	List<DeclarationResponse> declarations = declarationService.getDeclarationsResponseByPatent(publicationNumber);
    	if(Constants.DEBUG) logger.debug("getDeclarationByPatent: " + declarations.toString());
    	return new ResponseEntity<List<DeclarationResponse>>(declarations, HttpStatus.OK);
    }
    
    @GetMapping("/standard/{standardId}")
    @ApiOperation(value = "Get Declaration by Standard")
    public ResponseEntity<List<DeclarationResponse>> getDeclarationByStandard(@PathVariable("standardId") String standardId){
    	List<DeclarationResponse> declarations = declarationService.getDeclarationsResponseByStandard(standardId);
    	if(Constants.DEBUG) logger.debug("getDeclarationByStandard: " + declarations.toString());
    	return new ResponseEntity<List<DeclarationResponse>>(declarations, HttpStatus.OK);
    }
    
    @GetMapping("/patent/{publicationNumber}/standard/{standardId}")
    @ApiOperation(value = "Get Declaration by Patent and Standard")
    public ResponseEntity<DeclarationResponse> getDeclarationByPatentAndStandard(@PathVariable("publicationNumber") String publicationNumber, @PathVariable("standardId") String standardId){
    	DeclarationResponse declarationResponse = declarationService.getDeclarationResponseByPatentAndStandard(publicationNumber, standardId);
    	if(Constants.DEBUG) logger.debug("getDeclarationByPatentAndStandard: " + declarationResponse.toString());
    	return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Declaration")
    public ResponseEntity<GlobalResponse> deleteDeclarationById(@PathVariable("id") int id){
    	GlobalResponse response = declarationService.deleteDeclarationById(id);
    	response.setStatus(HttpStatus.ACCEPTED.name());
    	response.setStatusCode(HttpStatus.ACCEPTED.value());
    	if(Constants.DEBUG) logger.debug("deleteDeclarationById: " + response.toString());
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
