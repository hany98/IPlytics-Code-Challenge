package de.iplytics.codingchallenge_backend_webapp.api.v1.interceptors.custom;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationAlreadyExistsException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationEmptyFieldException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.exceptions.declaration.DeclarationNotFoundException;
import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.ExceptionResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.ControllerInterceptorUtils;

@ControllerAdvice
public class DeclarationControllerInterceptor {
	
	Logger logger = LoggerFactory.getLogger(DeclarationControllerInterceptor.class);

	@ExceptionHandler(DeclarationNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleDeclarationNotFoundException(DeclarationNotFoundException declarationNotFoundException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, declarationNotFoundException, HttpStatus.NOT_FOUND);
    }
	
	@ExceptionHandler(DeclarationAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleDeclarationAlreadyExistsException(DeclarationAlreadyExistsException declarationAlreadyExistsException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, declarationAlreadyExistsException, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(DeclarationEmptyFieldException.class)
    public ResponseEntity<ExceptionResponse> handleDeclarationEmptyFieldException(DeclarationEmptyFieldException declarationEmptyFieldException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, declarationEmptyFieldException, HttpStatus.BAD_REQUEST);
    }
    
}
