package com.company.consultant.web;

import javax.xml.ws.http.HTTPBinding;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.company.consultant.exceptions.ErrorCodes;
import com.company.consultant.exceptions.GcsException;

@ControllerAdvice
public class RestExceptionHandler {

	
	@ExceptionHandler(GcsException.class)
	public ResponseEntity<Error> handleError(GcsException gcs){
		Error error = new Error();
		error.setErrorCode(gcs.code);
		error.setMessage(gcs.getMessage());
		return new ResponseEntity<Error>(error,HttpStatus.INTERNAL_SERVER_ERROR);
	}
			
}
