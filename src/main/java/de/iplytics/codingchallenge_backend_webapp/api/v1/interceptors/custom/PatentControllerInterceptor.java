package de.iplytics.codingchallenge_backend_webapp.api.v1.interceptors.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentLinkedToDeclarationsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.patent.PatentNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.ExceptionResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.ControllerInterceptorUtils;

@ControllerAdvice
public class PatentControllerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(PatentControllerInterceptor.class);

	@ExceptionHandler(PatentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handlePatentNotFoundException(PatentNotFoundException patentNotFoundException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, patentNotFoundException, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(PatentIDAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handlePatentIDAlreadyExistsException(PatentIDAlreadyExistsException patentIDAlreadyExistsException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, patentIDAlreadyExistsException, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(PatentLinkedToDeclarationsException.class)
    public ResponseEntity<ExceptionResponse> handlePatentLinkedToDeclarationsException(PatentLinkedToDeclarationsException patentLinkedToDeclarationsException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, patentLinkedToDeclarationsException, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(PatentEmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handlePatentEmptyFieldException(PatentEmptyFieldException patentEmptyFieldException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, patentEmptyFieldException, HttpStatus.BAD_REQUEST);
    }
    
}
