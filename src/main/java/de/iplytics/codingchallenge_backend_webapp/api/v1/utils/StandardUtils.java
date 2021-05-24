package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import java.time.LocalDateTime;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardEmptyFieldException;

public class StandardUtils {
	
	public static void checkStandardCreationRequiredFields(Standard standard) {
		// Parse Required Fields (if necessary)
		String standardId = standard.getStandardId();
		String name = standard.getName();
		String description = standard.getDescription();

		// Check Empty Field standardId
		if (standardId == null || standardId.isEmpty())
			throw new StandardEmptyFieldException("standardId", String.class);

		// Check Empty Field name
		if (name == null || name.isEmpty())
			throw new StandardEmptyFieldException("name", String.class);

		// Check Empty Field description
		if (description == null || description.isEmpty())
			standard.setDescription("");
		
		// Add Creation Date
		standard.setCreationDate(LocalDateTime.now());
		
		// Add Modification Date
		standard.setModificationDate(null);
	}

	public static void checkStandardUpdatingRequiredFields(Standard standard) {
		// Parse Required Fields (if necessary)
		String standardId = standard.getStandardId();

		// Check Empty Field standardId
		if (standardId == null || standardId.isEmpty())
			throw new StandardEmptyFieldException("standardId", String.class);
	}

	public static void updateExistingFields(Standard oldStandard, Standard modifiedStandard) {
		// Parse Fields
		String name = modifiedStandard.getName();
		String description = modifiedStandard.getDescription();

		// Update Fields
		if (name != null && !name.isEmpty())
			oldStandard.setName(name);

		if (description != null && !description.isEmpty())
			oldStandard.setDescription(description);

		// Add Modification Date
		oldStandard.setModificationDate(LocalDateTime.now());
	}

}
