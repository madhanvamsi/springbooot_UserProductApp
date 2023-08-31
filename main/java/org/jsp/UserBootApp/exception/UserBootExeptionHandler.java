package org.jsp.UserBootApp.exception;

import org.jsp.UserBootApp.dto.ResponseStructure;
import org.jsp.UserBootApp.dto.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@ControllerAdvice
public class UserBootExeptionHandler extends ResponseEntityExceptionHandler
{
	@ExceptionHandler(IdNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>>  handleIDNotFound(IdNotFoundException exception)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("user not found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NOT_FOUND);
		
	}
	@ExceptionHandler(InvalidCredentialsExecption.class)
	public ResponseEntity<ResponseStructure<String>> HandleInvalidCredentialsException(InvalidCredentialsExecption exception)
	{
		ResponseStructure<String> structure=new ResponseStructure<>();
		structure.setData("user not found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);
		
	}

}
