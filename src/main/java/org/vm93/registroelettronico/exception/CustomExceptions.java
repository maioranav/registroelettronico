package org.vm93.registroelettronico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;

@ControllerAdvice
public class CustomExceptions extends ResponseEntityExceptionHandler {
	@ExceptionHandler(EntityExistsException.class)
	ResponseEntity<String> manageEntityExistsException(EntityExistsException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.FOUND);
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	ResponseEntity<String> manageEntityNotFoundException(EntityNotFoundException e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(Exception.class)
	ResponseEntity<String> manageException(Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
}
