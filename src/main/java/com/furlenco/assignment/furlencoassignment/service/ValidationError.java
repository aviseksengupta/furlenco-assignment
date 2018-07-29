package com.furlenco.assignment.furlencoassignment.service;

public class ValidationError extends RuntimeException {
	
	private String message;
	
	public ValidationError(String message) {
		this.message = message;
	}
	
	public ValidationError() {
		this.message = "Validation Error";
	}
	
	@Override
	public String getMessage() {
		return message;
	}
}
