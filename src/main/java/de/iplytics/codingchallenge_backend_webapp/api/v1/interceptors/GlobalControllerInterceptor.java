package de.iplytics.codingchallenge_backend_webapp.api.v1.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import de.iplytics.codingchallenge_backend_webapp.api.v1.responses.ExceptionResponse;
import de.iplytics.codingchallenge_backend_webapp.api.v1.utils.ControllerInterceptorUtils;

@ControllerAdvice
public class GlobalControllerInterceptor {

	Logger logger = LoggerFactory.getLogger(GlobalControllerInterceptor.class);
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException illegalArgumentException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, illegalArgumentException, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionResponse> handleHttpMessageNotReadableException(HttpMessageNotReadableException httpMessageNotReadableException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, httpMessageNotReadableException, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException httpRequestMethodNotSupportedException) {
		return ControllerInterceptorUtils.handleExceptionResponse(logger, httpRequestMethodNotSupportedException, HttpStatus.BAD_REQUEST);
	}
	
}
