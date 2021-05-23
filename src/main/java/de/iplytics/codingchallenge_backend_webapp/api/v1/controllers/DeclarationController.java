package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
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
    public ResponseEntity<Declaration> createDeclaration(
    		@RequestParam(required=true) String publicationNumber, 
    		@RequestParam(required=true) String standardId){
    	Declaration createdDeclaration = declarationService.createDeclaration(publicationNumber, standardId);
        return new ResponseEntity<Declaration>(createdDeclaration, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Declaration")
    public ResponseEntity<Declaration> updateDeclaration(@RequestBody Declaration declaration){
    	Declaration updatedDeclaration = declarationService.updateDeclaration(declaration);
        return new ResponseEntity<Declaration>(updatedDeclaration, HttpStatus.CREATED);
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "Get Declaration by ID")
    public ResponseEntity<Declaration> getDeclaration(@PathVariable("id") int id){
    	Declaration declaration = declarationService.getDeclaration(id);
        return new ResponseEntity<Declaration>(declaration, HttpStatus.OK);
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete Declaration")
    public ResponseEntity<Void> deleteDeclaration(@PathVariable("id") int id){
        declarationService.deleteDeclaration(id);
        return new ResponseEntity<Void>(HttpStatus.ACCEPTED);
    }
    
}
