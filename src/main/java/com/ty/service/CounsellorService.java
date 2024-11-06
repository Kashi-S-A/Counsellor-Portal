package com.ty.service;

import org.springframework.http.ResponseEntity;

import com.ty.dto.LoginRequest;
import com.ty.entity.Counsellor;
import com.ty.enums.Status;

public interface CounsellorService {

	ResponseEntity<?> register(Counsellor counsellor);

	ResponseEntity<?> login(LoginRequest loginRequest);

	ResponseEntity<?> updateStatus(Integer cid, Status status);

	ResponseEntity<?> updatePhone(Integer cid, Long phone);
	
	ResponseEntity<?> getCounsellor(Integer cid);
	
	ResponseEntity<?> deleteCounsellor(Integer cid);
}
