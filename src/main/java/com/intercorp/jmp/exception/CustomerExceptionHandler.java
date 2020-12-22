// Desafio Intercorp - 2020 
package com.intercorp.jmp.exception;

import javax.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Name: CustomerExceptionHandler
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * */
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomerExceptionHandler extends ResponseEntityExceptionHandler {


	/**
	 * handleServletRequestBindingException
	 * @param ex ServletRequestBindingException
	 * @param headers HttpHeaders
	 * @param HttpStatus status
	 * @param  WebRequest request
	 * @return Response Entity Object
	 * 
	 * **/
	@Override
	protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		return new ResponseEntity<>("error!", status);
	}

	/***
	 * handleConstraintViolation
	 * @param ConstraintViolationException ex
	 * @param  WebRequest request
	 * @return Response Entity Object
	 * 
	 * */
	@ExceptionHandler({ ConstraintViolationException.class })
	protected ResponseEntity<Object> handleConstraintViolation(ConstraintViolationException ex, WebRequest request) {
		return new ResponseEntity<Object>(null, HttpStatus.BAD_REQUEST);
	}
	
}
