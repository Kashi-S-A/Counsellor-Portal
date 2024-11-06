package com.ty.exception;

public class CounsellorNotFound extends RuntimeException {

	private String message;
	
	public CounsellorNotFound(){}

	public CounsellorNotFound(String message) {
		this.message = message;
	}
	
	@Override
	public String getMessage() {
		return this.message;
	}
}
