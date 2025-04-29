package com.springboot.financialplanning.exception;

public class InvalidCategoryException extends Exception{
	
	private static final long serialVersionUID = 1L;
	private String message;

	public InvalidCategoryException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
	
}
