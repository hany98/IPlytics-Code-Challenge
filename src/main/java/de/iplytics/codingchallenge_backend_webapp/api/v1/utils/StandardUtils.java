package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardEmptyFieldException;

public class StandardUtils {
	
	public static void checkStandardRequiredFields(Standard standard) {
		// Parse Required Fields (if necessary)
		String standardId = standard.getStandardId();
		String name = standard.getName();
		String description = standard.getDescription();
		
		// Check Empty Field standardId
		if(standardId == null || standardId.isEmpty())
			throw new StandardEmptyFieldException("standardId", String.class);
		
		// Check Empty Field name
		if(name == null || (name + "").isEmpty())
			throw new StandardEmptyFieldException("name", String.class);
		
		// Check Empty Field description
		if(description == null || description.isEmpty())
			throw new StandardEmptyFieldException("description", String.class);
	}

}
