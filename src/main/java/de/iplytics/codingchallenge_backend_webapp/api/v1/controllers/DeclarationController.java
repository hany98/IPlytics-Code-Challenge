package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.GlobalResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.services.DeclarationService;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/declarations")
public class DeclarationController {
	
	Logger logger = LoggerFactory.getLogger(DeclarationController.class);

    @Autowired
    DeclarationService declarationService;

    @GetMapping("/")
    @ApiOperation(value = "Get All Declarations")
    public ResponseEntity<List<Declaration>> getAllDeclarations(){
    	List<Declaration> declarations = declarationService.getAllDeclarations();
        return new ResponseEntity<List<Declaration>>(declarations, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Declaration")
    public ResponseEntity<DeclarationResponse> createDeclaration(@RequestBody DeclarationRequest declarationRequest){
    	DeclarationResponse declarationResponse = declarationService.createDeclaration(declarationRequest);
        return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Declaration")
    public ResponseEntity<DeclarationResponse> updateDeclaration(@RequestBody DeclarationRequest declarationRequest){
    	DeclarationResponse declarationResponse = declarationService.updateDeclaration(declarationRequest);
        return new ResponseEntity<DeclarationResponse>(declarationResponse, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Declaration by ID")
    public ResponseEntity<Declaration> getDeclarationById(@PathVariable("id") int id){
    	Declaration declaration = declarationService.getDeclarationById(id);
        return new ResponseEntity<Declaration>(declaration, HttpStatus.OK);
    }
    
    @GetMapping("/patent/{publicationNumber}")
    @ApiOperation(value = "Get Declaration by Patent")
    public ResponseEntity<List<Declaration>> getDeclarationByPatent(@PathVariable("publicationNumber") String publicationNumber){
    	List<Declaration> declarations = declarationService.getDeclarationsByPatent(publicationNumber);
        return new ResponseEntity<List<Declaration>>(declarations, HttpStatus.OK);
    }
    
    @GetMapping("/standard/{standardId}")
    @ApiOperation(value = "Get Declaration by Standard")
    public ResponseEntity<List<Declaration>> getDeclarationByStandard(@PathVariable("standardId") String standardId){
    	List<Declaration> declarations = declarationService.getDeclarationsByStandard(standardId);
        return new ResponseEntity<List<Declaration>>(declarations, HttpStatus.OK);
    }
    
    @GetMapping("/patent/{publicationNumber}/standard/{standardId}")
    @ApiOperation(value = "Get Declaration by Patent and Standard")
    public ResponseEntity<Declaration> getDeclarationByPatentAndStandard(@PathVariable("publicationNumber") String publicationNumber, @PathVariable("standardId") String standardId){
    	Declaration declaration = declarationService.getDeclarationByPatentAndStandard(publicationNumber, standardId);
        return new ResponseEntity<Declaration>(declaration, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Declaration")
    public ResponseEntity<GlobalResponse> deleteDeclarationById(@PathVariable("id") int id){
    	GlobalResponse response = declarationService.deleteDeclarationById(id);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
