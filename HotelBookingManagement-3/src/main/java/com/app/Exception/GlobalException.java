package com.app.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException 
{

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<String>handleResourceNotFoundException(Exception ex)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
	}


@ExceptionHandler(DuplicateException.class)
public ResponseEntity<String>handleDuplicateException(DuplicateException ex)
{
	return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
}
}