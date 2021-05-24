package de.iplytics.codingchallenge_backend_webapp.api.v1.interceptors.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardIDAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardLinkedToDeclarationsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.standard.StandardNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.ExceptionResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.ControllerInterceptorUtils;

@ControllerAdvice
public class StandardControllerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(StandardControllerInterceptor.class);

	@ExceptionHandler(StandardNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleStandardNotFoundException(StandardNotFoundException standardNotFoundException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, standardNotFoundException, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(StandardIDAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleStandardAlreadyExistsException(StandardIDAlreadyExistsException standardAlreadyExistsException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, standardAlreadyExistsException, HttpStatus.CONFLICT);
    }
    
    @ExceptionHandler(StandardLinkedToDeclarationsException.class)
    public ResponseEntity<ExceptionResponse> handleStandardLinkedToDeclarationsException(StandardLinkedToDeclarationsException standardLinkedToDeclarationsException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, standardLinkedToDeclarationsException, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(StandardEmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleStandardEmptyFieldException(StandardEmptyFieldException standardEmptyFieldException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, standardEmptyFieldException, HttpStatus.BAD_REQUEST);
    }
    
}
