package com.ty.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ty.dto.CounsellorDto;
import com.ty.dto.LoginRequest;
import com.ty.entity.Counsellor;
import com.ty.enums.Status;
import com.ty.exception.CounsellorNotFound;
import com.ty.repository.CounsellorRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class CounsellorServiceImpl implements CounsellorService {

	@Autowired
	private CounsellorRepository counsellorRepository;

	/*
	 * Takes Counsellor if email is not registered then save it else don't save
	 */
	@Override
	public ResponseEntity<?> register(Counsellor counsellor) {
		Optional<Counsellor> opt = counsellorRepository.findByEmail(counsellor.getEmail());

		if (opt.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Already Registered");
			rs.setData(counsellor.getEmail());
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			Counsellor save = counsellorRepository.save(counsellor);

//			ObjectMapper mapper = new ObjectMapper();
//			CounsellorDto dto = mapper.convertValue(save, CounsellorDto.class);

			CounsellorDto dto = new CounsellorDto();
			BeanUtils.copyProperties(save, dto);

			ResponseStructure<CounsellorDto> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.CREATED.value());
			rs.setMessage("Registered successfully");
			rs.setData(dto);
			return new ResponseEntity<ResponseStructure<CounsellorDto>>(rs, HttpStatus.OK);
		}
	}

	/*
	 * Find COunsellor based on email, if exist validate password and return
	 * response else throw CounsellorNotFound exception
	 */
	@Override
	public ResponseEntity<?> login(LoginRequest loginRequest) {
		Counsellor counsellor = counsellorRepository.findByEmail(loginRequest.getEmail())
				.orElseThrow(() -> new CounsellorNotFound("Counserllor Does not exist"));
		ResponseStructure<String> rs = new ResponseStructure<>();
		if (counsellor.getPassword().equals(loginRequest.getPassword())) {
			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Login Successfull");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		}
		rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
		rs.setMessage("Invalid Password");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

	/*
	 * Find the counsellor based on id if exist update the new Status else throw
	 * exception
	 */
	@Override
	public ResponseEntity<?> updateStatus(Integer cid, Status status) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is not registered"));
		counsellor.setStatus(status);
		Counsellor save = counsellorRepository.save(counsellor);
		ResponseStructure<Status> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Status Updated Successfully");
		rs.setData(save.getStatus());
		return new ResponseEntity<ResponseStructure<Status>>(rs, HttpStatus.OK);
	}

	/*
	 * update Phone if counsellor exists based on id else throw the exception
	*/
	@Override
	public ResponseEntity<?> updatePhone(Integer cid, Long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Fetch and return counsellor if exist based id else throw exception
	 */
	@Override
	public ResponseEntity<?> getCounsellor(Integer cid) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor is exist"));

		CounsellorDto dto = new CounsellorDto();

		BeanUtils.copyProperties(counsellor, dto);

		ResponseStructure<CounsellorDto> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Fetched Successfully");
		rs.setData(dto);
		return new ResponseEntity<ResponseStructure<CounsellorDto>>(rs, HttpStatus.OK);
	}

	/*
	 * Delete counsellor if exists based on id else throw exception 
	*/
	@Override
	public ResponseEntity<?> deleteCounsellor(Integer cid) {
		// TODO Auto-generated method stub
		return null;
	}
}
