package com.furlenco.assignment.furlencoassignment.repository;

public class DatastoreException extends RuntimeException {
	
	String detailMessage;
	
	public DatastoreException(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	
	public DatastoreException() {
		detailMessage = "";
	}
	
	@Override
	public String getMessage() {
		return "Student data storage error: "+ detailMessage;
	}
}
