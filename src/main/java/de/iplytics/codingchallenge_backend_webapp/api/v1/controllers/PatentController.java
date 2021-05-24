package de.iplytics.codingchallenge_backend_webapp.api.v1.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
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
    public ResponseEntity<List<Patent>> getAllPatents(){
    	List<Patent> patents = patentService.getAllPatents();
        return new ResponseEntity<List<Patent>>(patents, HttpStatus.OK);
    }

    @PostMapping("/")
    @ApiOperation(value = "Create Patent")
    public ResponseEntity<Patent> createPatent(@RequestBody Patent patent){
    	Patent createdPatent = patentService.createPatent(patent);
        return new ResponseEntity<Patent>(createdPatent, HttpStatus.CREATED);
    }
    
    @PutMapping("/")
    @ApiOperation(value = "Update Patent")
    public ResponseEntity<Patent> updatePatent(@RequestBody Patent patent){
    	Patent updatedPatent = patentService.updatePatent(patent);
        return new ResponseEntity<Patent>(updatedPatent, HttpStatus.CREATED);
    }
    
    @GetMapping("/{publicationNumber}")
    @ApiOperation(value = "Get Patent By ID")
    public ResponseEntity<Patent> getPatent(@PathVariable("publicationNumber") String id){
    	Patent patent = patentService.getPatent(id);
        return new ResponseEntity<Patent>(patent, HttpStatus.OK);
    }
    
    @DeleteMapping("/{publicationNumber}")
    @ApiOperation(value = "Delete Patent")
    public ResponseEntity<GlobalResponse> deletePatent(@PathVariable("publicationNumber") String id){
    	GlobalResponse response = patentService.deletePatent(id);
        return new ResponseEntity<GlobalResponse>(response, HttpStatus.ACCEPTED);
    }
    
}
