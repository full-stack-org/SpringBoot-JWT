package com.springboot.jwt.exception.handler;

public class UserNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7544168981010224516L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
