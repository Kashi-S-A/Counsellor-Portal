package com.ty.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ty.dto.LoginRequest;
import com.ty.entity.Counsellor;
import com.ty.service.CounsellorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/counsellor")
public class CounsellorController {

	@Autowired
	private CounsellorService counsellorService;

	@PostMapping("/register")
	public ResponseEntity<?> saveCounsellor(@RequestBody Counsellor counsellor) {
		return counsellorService.register(counsellor);
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginCounsellor(@RequestBody LoginRequest loginRequest) {
		return counsellorService.login(loginRequest);
	}

}
