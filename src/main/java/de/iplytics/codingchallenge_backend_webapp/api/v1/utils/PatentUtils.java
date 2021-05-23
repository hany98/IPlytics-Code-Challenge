package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import java.time.LocalDate;

import de.iplytics.codingchallenge_backend_webapp.api.v1.entities.Patent;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentEmptyFieldException;

public class PatentUtils {

	public static void checkPatentCreationRequiredFields(Patent patent) {
		// Parse Required Fields (if necessary)
		String publicationNumber = patent.getPublicationNumber();
		LocalDate publicationDate = patent.getPublicationDate();
		String title = patent.getTitle();
		String description = patent.getDescription();

		// Check Empty Field publicationNumber
		if (publicationNumber == null || publicationNumber.isEmpty())
			throw new PatentEmptyFieldException("publicationNumber", String.class);

		// Check Empty Field publicationDate
		if (publicationDate == null || (publicationDate + "").isEmpty())
			throw new PatentEmptyFieldException("publicationDate", LocalDate.class);

		// Check Empty Field title
		if (title == null || title.isEmpty())
			throw new PatentEmptyFieldException("title", String.class);

		// Check Empty Field description
		if (description == null || description.isEmpty())
			patent.setDescription("");
		
		// Add Creation Date
//		String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
//		Date date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").parse(date);
		patent.setCreationDate(LocalDate.now());
		patent.setModificationDate(null);
	}

	public static void checkPatentUpdatingRequiredFields(Patent patent) {
		// Parse Required Fields (if necessary)
		String publicationNumber = patent.getPublicationNumber();

		// Check Empty Field publicationNumber
		if (publicationNumber == null || publicationNumber.isEmpty())
			throw new PatentEmptyFieldException("publicationNumber", String.class);
	}

	public static void updateExistingFields(Patent oldPatent, Patent modifiedPatent) {
		// Parse Fields
		LocalDate publicationDate = modifiedPatent.getPublicationDate();
		String title = modifiedPatent.getTitle();
		String description = modifiedPatent.getDescription();

		// Update Fields
		if (publicationDate != null && !(publicationDate + "").isEmpty())
			oldPatent.setPublicationDate(publicationDate);

		if (title != null && !title.isEmpty())
			oldPatent.setTitle(title);

		if (description != null && !description.isEmpty())
			oldPatent.setDescription(description);

		// Add Modification Date
		oldPatent.setModificationDate(LocalDate.now());
	}

}
