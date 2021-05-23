package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Declaration;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Standard;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationEmptyFieldException;

public class DeclarationUtils {
	
	public static void checkDeclarationRequiredFields(Declaration declaration) {
		// Parse Patent and Standard
		Patent patent = declaration.getPatent();
		Standard standard = declaration.getStandard();
		
		// Check Empty Field Patent
		if(patent == null)
			throw new DeclarationEmptyFieldException("patent", Patent.class);
		
		// Check Empty Field Standard
		if(standard == null)
			throw new DeclarationEmptyFieldException("standard", Standard.class);
				
		// Parse and Check Empty Required Fields for Patent
//		PatentUtils.checkPatentRequiredFields(patent);
		
		// Parse and Check Empty Required Fields for Standard
//		StandardUtils.checkStandardRequiredFields(standard);
	}

}
