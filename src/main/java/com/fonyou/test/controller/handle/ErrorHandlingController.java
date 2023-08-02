package com.fonyou.test.controller.handle;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fonyou.test.dto.Response;

public class ErrorHandlingController {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ResponseBody
	public Response validationError(MethodArgumentNotValidException ex) {
	       
	    FieldError fieldError = ex.getBindingResult().getFieldError();
	    String errorMessage = new StringBuilder("Field '")
			.append( fieldError.getField() )
			.append("' ")
			.append(fieldError.getDefaultMessage())
			.toString();
	    return new Response( HttpStatus.BAD_REQUEST.value(),
	    						HttpStatus.BAD_REQUEST.getReasonPhrase(),
	    						errorMessage);
	}
	
	@ExceptionHandler(InvalidFormatException.class)
    public Response validationFormat(InvalidFormatException e) {

		return new Response( HttpStatus.BAD_REQUEST.value(),
    						HttpStatus.BAD_REQUEST.getReasonPhrase(),
    						e.getMessage());
    }
}
