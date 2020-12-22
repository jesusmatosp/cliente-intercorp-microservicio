// Desafio Intercorp - 2020 
package com.intercorp.jmp.exception;

/**
 * Name: CustomerException
 * @author JESUS MATOS
 * @date 2020-12-21
 * @version 1.0.0
 * 
 * */
public class CustomerException extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * @param message
	 * @param cause
	 */
	public CustomerException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public CustomerException(String message) {
		super(message);
	}
	
	
	
	
}
