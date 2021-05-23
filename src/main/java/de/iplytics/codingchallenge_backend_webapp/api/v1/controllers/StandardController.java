package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
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
    public ResponseEntity<List<Standard>> getAllStandards(){
    	List<Standard> standards = standardService.getAllStandards();
        return new ResponseEntity<List<Standard>>(standards, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Standard")
    public ResponseEntity<Standard> createStandard(@RequestBody Standard standard){
    	Standard createdStandard = standardService.createStandard(standard);
        return new ResponseEntity<Standard>(createdStandard, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Standard")
    public ResponseEntity<Standard> updateStandard(@RequestBody Standard standard){
    	Standard updatedStandard = standardService.updateStandard(standard);
        return new ResponseEntity<Standard>(updatedStandard, HttpStatus.CREATED);
    }
    
    @GetMapping("/{standardId}")
    @ApiOperation(value = "Get Standard By ID")
    public ResponseEntity<Standard> getStandard(@PathVariable("standardId") String id){
    	Standard standard = standardService.getStandard(id);
        return new ResponseEntity<Standard>(standard, HttpStatus.OK);
    }
    
    @DeleteMapping("/{standardId}")
    @ApiOperation(value = "Delete Standard")
    public ResponseEntity<GlobalResponse> deleteStandard(@PathVariable("standardId") String id){
    	GlobalResponse response = standardService.deleteStandard(id);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
