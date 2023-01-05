package com.examplenative.errors;

public class ErrorAuthorization extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ErrorAuthorization(String message) {
		super(message);
	}

}
