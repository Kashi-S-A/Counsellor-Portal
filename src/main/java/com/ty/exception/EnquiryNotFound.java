package com.ty.exception;

public class EnquiryNotFound extends RuntimeException {

	private String message;

	public EnquiryNotFound() {
	}

	public EnquiryNotFound(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return this.message;
	}
}
