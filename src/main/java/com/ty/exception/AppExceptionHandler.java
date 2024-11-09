package com.ty.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ty.responsestructure.ResponseStructure;

@RestControllerAdvice
public class AppExceptionHandler {

	@ExceptionHandler(CounsellorNotFound.class)
	public ResponseEntity<ResponseStructure<String>> catchCounsellorNotFound(CounsellorNotFound message) {
	
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Counsellor is Not found/registered");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
	
	@ExceptionHandler(EnquiryNotFound.class)
	public ResponseEntity<ResponseStructure<String>> catchEnquiryNotFound(EnquiryNotFound message) {
	
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Enquiry is Not found/added");
		rs.setData(null);

		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}
}
