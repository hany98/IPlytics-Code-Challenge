package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import java.time.LocalDate;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.custom.request.DeclarationRequest;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentEmptyFieldException;

public class DeclarationUtils {
	
	public static void checkDeclarationCreationRequiredFields(DeclarationRequest declarationRequest) {
		// Parse Required Fields (if necessary)
		String publicationNumber = declarationRequest.getPublicationNumber();
		String standardId = declarationRequest.getStandardId();
		String description = declarationRequest.getDescription();

		// Check Empty Field publicationNumber
		if (publicationNumber == null || publicationNumber.isEmpty())
			throw new DeclarationEmptyFieldException("publicationNumber", String.class);

		// Check Empty Field title
		if (standardId == null || standardId.isEmpty())
			throw new DeclarationEmptyFieldException("standardId", String.class);

		// Check Empty Field description
		if (description == null || description.isEmpty())
			declarationRequest.setDescription("");
	}

	public static void checkDeclarationUpdatingRequiredFields(DeclarationRequest declarationRequest) {
		// Parse Required Fields (if necessary)
		String publicationNumber = declarationRequest.getPublicationNumber();
		String standardId = declarationRequest.getStandardId();

		// Check Empty Field publicationNumber
		if (publicationNumber == null || publicationNumber.isEmpty())
			throw new DeclarationEmptyFieldException("publicationNumber", String.class);

		// Check Empty Field title
		if (standardId == null || standardId.isEmpty())
			throw new DeclarationEmptyFieldException("standardId", String.class);
	}

	public static void updateExistingFields(Declaration declaration, DeclarationRequest declarationRequest) {
		// Parse Fields
		String description = declarationRequest.getDescription();

		// Update Fields
		if (description != null && !description.isEmpty())
			declaration.setDescription(description);

		// Add Modification Date
		declaration.setModificationDate(LocalDate.now());
	}

}
