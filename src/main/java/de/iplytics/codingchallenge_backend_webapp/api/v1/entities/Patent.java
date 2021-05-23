package de.iplytics.codingchallenge_backend_webapp.api.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

import java.time.LocalDate;

@Data
@Builder
@Entity(name = "patent")
@AllArgsConstructor
@NoArgsConstructor
public class Patent {

    @Id
    private String publicationNumber;
    
    private LocalDate publicationDate;
    
    private String title;
    
    private String description;
    
    private LocalDate creationDate;
	
	private LocalDate modificationDate;
    
}
