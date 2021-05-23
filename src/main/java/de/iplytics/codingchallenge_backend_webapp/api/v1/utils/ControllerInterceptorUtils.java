package de.iplytics.codingchallenge_backend_webapp.api.v1.utils;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.ExceptionResponse;

public class ControllerInterceptorUtils {
	
	public static ResponseEntity<ExceptionResponse> handleExceptionResponse(Logger logger, Exception exception, HttpStatus httpStatus) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(httpStatus.name(), httpStatus.value(), exception.getMessage());
		logger.error(exceptionResponse.toString());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, httpStatus);
    }

}
