package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ty.entity.Enquiry;
import com.ty.service.EnquiryService;


@RestController
@RequestMapping("/enquiry")
public class EnquiryController {

	@Autowired
	private EnquiryService enquiryService;
	
	@PostMapping("/add")
	public ResponseEntity<?> addEnquiry(@RequestParam Integer cid, @RequestBody Enquiry enquiry) {
		return enquiryService.addEnquiry(cid, enquiry);
	}
	
}
