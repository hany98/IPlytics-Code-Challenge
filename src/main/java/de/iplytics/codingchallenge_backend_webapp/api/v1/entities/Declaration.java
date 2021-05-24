package de.iplytics.codingchallenge_backend_webapp.api.v1.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.response.DeclarationResponse;

@Data
@Builder
@Entity(name = "declaration")
@AllArgsConstructor
@NoArgsConstructor
public class Declaration {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@OneToOne
	@JoinColumn(name = "publication_number")
    private Patent patent;
	
	@OneToOne
	@JoinColumn(name = "standard_id")
	private Standard standard;
	
	private String description;
	
	private LocalDateTime creationDate;
	
	private LocalDateTime modificationDate;
	
	public DeclarationResponse toDeclarationResponse() {
		return DeclarationResponse.builder()
				.id(this.getId())
				.patent(this.getPatent())
				.standard(this.getStandard())
				.description(this.getDescription())
				.creationDate(this.getCreationDate())
				.modificationDate(this.getModificationDate())
				.build();
	}
    
}