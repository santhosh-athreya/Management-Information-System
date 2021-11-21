package com.mis.app.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		
		//create our exception bean. 
		
	    ExceptionResponse response  =	new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		
	    return new ResponseEntity(response,HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
   
	
	@ExceptionHandler(MyCustomException.class)
	public final ResponseEntity<Object> handleCustomException(Exception ex, WebRequest request) throws Exception {
		
		//create our exception bean. 
		
	    ExceptionResponse response  =	new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		
	    return new ResponseEntity(response,HttpStatus.BAD_REQUEST);
		
	}

}
