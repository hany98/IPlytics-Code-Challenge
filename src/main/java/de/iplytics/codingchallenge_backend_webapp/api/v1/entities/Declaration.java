package de.iplytics.codingchallenge_backend_webapp.api.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Data
@Builder
@Entity(name = "declaration")
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "standard_id")
	private Standard standard;
    
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "publication_number")
    private Patent patent;
	
	private String description;
	
	private LocalDate creationDate;
	
	private LocalDate modificationDate;
    
}