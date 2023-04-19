package com.springboot.social_media.Exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.springboot.social_media.modals.ErrorResponseModal;
import com.springboot.social_media.user.UserNotFoundException;

@ControllerAdvice
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponseModal> handleAllExceptions(Exception e, WebRequest request) {
		ErrorResponseModal errorResponse = new ErrorResponseModal(LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponseModal>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorResponseModal> handleNotFoundExceptions(Exception e, WebRequest request) {
		ErrorResponseModal errorResponse = new ErrorResponseModal(LocalDateTime.now(), e.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<ErrorResponseModal>(errorResponse, HttpStatus.NOT_FOUND);
	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		StringBuilder errorMessage=new StringBuilder();
		for(int i=0;i<ex.getAllErrors().size();i++) {
			if(i==ex.getFieldErrors().size()-1)
				errorMessage.append(ex.getFieldErrors().get(i).getDefaultMessage());
			else
				errorMessage.append(ex.getFieldErrors().get(i).getDefaultMessage()+" and ");
		}
		
		ErrorResponseModal errorResponse = new ErrorResponseModal(LocalDateTime.now(), errorMessage.toString(),
				request.getDescription(false));
		return new ResponseEntity<Object>(errorResponse, HttpStatus.BAD_REQUEST);
	}

}
