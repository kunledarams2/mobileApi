package com.kunledarams.mobilewsapi.execptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kunledarams.mobilewsapi.ui.model.response.ErrorMessage;

@ControllerAdvice
public class AppExeceptionHandler {
	
	@ExceptionHandler(value = {UserServiceExecption.class})
	public ResponseEntity<Object>handerUserServiceEx(UserServiceExecption ex, WebRequest request){
		
		ErrorMessage errorMessage= new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
	}

	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object>handerUserServiceEx(Exception ex, WebRequest request){
		
		ErrorMessage errorMessage= new ErrorMessage(new Date(), ex.getMessage());
		
		return new ResponseEntity<Object>(errorMessage, new HttpHeaders(),  HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
