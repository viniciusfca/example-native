package com.examplenative.errors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.examplenative.util.SerializerError;

import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {
	
	private final String url = "http://www.developer.apiluiza.com.br/";
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(value = {IllegalArgumentException.class})
	public ResponseEntity<ErrorMessage> illegalArgumentException(IllegalArgumentException e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	@ExceptionHandler(value = {ErrorNotFound.class})
	public ResponseEntity<ErrorMessage> notFoundError(ErrorNotFound e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	
	@ExceptionHandler(value = {ErrorBusiness.class})
	public ResponseEntity<ErrorMessage> businessError(ErrorBusiness e, HttpServletRequest request, WebRequest webRequest){
		e.printStackTrace();
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	@ExceptionHandler(value = {ErrorAuthorization.class})
	public ResponseEntity<ErrorMessage> notAuthorizationError(ErrorAuthorization e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	@ExceptionHandler(value = {MalformedJwtException.class})
	public ResponseEntity<ErrorMessage> malformedJwtError(MalformedJwtException e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<ErrorMessage> internalServerError(Exception e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	@ExceptionHandler(value = {NullPointerException.class})
	public ResponseEntity<ErrorMessage> internalServerErrorNullException(NullPointerException e, HttpServletRequest request, WebRequest webRequest){
		logger.error(SerializerError.serializerErrorBody(request, webRequest, e.getMessage()));
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(generateMessageError(e.getMessage(), e.getLocalizedMessage()));
	}
	
	public ErrorMessage generateMessageError(String errorMessage, String localizedMessage) {
		var error = new ErrorMessage();
		error.setDeveloperMessage(errorMessage);
		error.setUserMessage(localizedMessage);
		error.setUrl(url);
		
		return error;
	}

}
