package com.examplenative.errors;

public class ErrorBusiness extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ErrorBusiness(String message) {
		super(message);
	}
}
