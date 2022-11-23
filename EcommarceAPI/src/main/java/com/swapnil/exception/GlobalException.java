package com.swapnil.exception;

import java.time.LocalTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.swapnil.model.Customer;

@ControllerAdvice
public class GlobalException {

	
	@ExceptionHandler(CustomerException.class)
	public ResponseEntity<MyError> getCustomerException(CustomerException c,WebRequest req){
		
		MyError myerror=new MyError(c.getMessage(), LocalTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
		
	}
	
	@ExceptionHandler(LoginException.class)
	public ResponseEntity<MyError> getLoginException(LoginException c,WebRequest req){
		
		MyError myerror=new MyError(c.getMessage(), LocalTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(AdminDTOException.class)
	public ResponseEntity<MyError> getadminLoginException(AdminDTOException c,WebRequest req){
		
		MyError myerror=new MyError(c.getMessage(), LocalTime.now(), req.getDescription(false));
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<MyError> manveException(MethodArgumentNotValidException c){
		
		MyError myerror=new MyError(c.getMessage(), LocalTime.now(), c.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<MyError>(myerror, HttpStatus.BAD_REQUEST);
		
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<MyError> noException(NoHandlerFoundException noh,WebRequest req){
		
		MyError mr=new MyError(noh.getMessage(), LocalTime.now(), req.getDescription(false));

	return new ResponseEntity<MyError>(mr, HttpStatus.BAD_REQUEST);	
	}
	
	
	
}
