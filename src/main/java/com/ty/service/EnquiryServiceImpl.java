package com.ty.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ty.dto.EnquiryDto;
import com.ty.dto.FilterDto;
import com.ty.entity.Counsellor;
import com.ty.entity.Enquiry;
import com.ty.enums.ClassMode;
import com.ty.enums.Course;
import com.ty.exception.CounsellorNotFound;
import com.ty.exception.EnquiryNotFound;
import com.ty.repository.CounsellorRepository;
import com.ty.repository.EnquiryRepository;
import com.ty.responsestructure.ResponseStructure;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private EnquiryRepository enquiryRepository;

	@Autowired
	private CounsellorRepository counsellorRepository;

	/*
	 * check counsellor exists or not check enquiry registered or not if counsellor
	 * exists and enquiry is not added --> set that counsellor to the enquiry and
	 * save the enquiry
	 */
	@Override
	public ResponseEntity<?> addEnquiry(Integer cid, Enquiry enquiry) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Does not exist"));
		Optional<Enquiry> opt = enquiryRepository.findByEmail(enquiry.getEmail());
		if (opt.isPresent()) {
			ResponseStructure<String> rs = new ResponseStructure<>();

			rs.setStatusCode(HttpStatus.BAD_REQUEST.value());
			rs.setMessage("Enquiry already taken");
			rs.setData(enquiry.getEmail());

			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		} else {
			enquiry.setCounsellor(counsellor);
			Enquiry save = enquiryRepository.save(enquiry);
//			List<Enquiry> enquiries = counsellor.getEnquiries();
//			enquiries.add(enquiry);
//			counsellor.setEnquiries(enquiries);

			EnquiryDto dto = new EnquiryDto();
			BeanUtils.copyProperties(save, dto);

			ResponseStructure<EnquiryDto> rs = new ResponseStructure<>();

			rs.setStatusCode(HttpStatus.OK.value());
			rs.setMessage("Enquiry added successfully");
			rs.setData(dto);

			return new ResponseEntity<ResponseStructure<EnquiryDto>>(rs, HttpStatus.OK);

		}

	}

	/*
	 * Find enquiry based on id , if exist update course else throw exception
	 */
	@Override
	public ResponseEntity<?> updateCourse(Integer eid, Course course) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Find enquiry based on id , if exist update phone else throw exception
	 */
	@Override
	public ResponseEntity<?> updatePhone(Integer eid, Long phone) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Find enquiry based on id , if exist update classMode else throw exception
	 */
	@Override
	public ResponseEntity<?> updateClassMode(Integer eid, ClassMode classMode) {
		// TODO Auto-generated method stub
		return null;
	}

	/*
	 * Find counsellor based on id ,if exists and that counsellor has added this
	 * enquiry then delete else throw respective exceptions with messages.
	 */
	@Override
	public ResponseEntity<?> deleteEnquiry(Integer cid, Integer eid) {
		Enquiry enquiry = enquiryRepository.findById(eid)
				.orElseThrow(() -> new EnquiryNotFound("Enquiry does not exist"));
		Counsellor counsellor = enquiry.getCounsellor();
		if (counsellor.getCid() == cid) {
			enquiryRepository.delete(enquiry);
			ResponseStructure<String> rs = new ResponseStructure<>();
			rs.setStatusCode(HttpStatus.NO_CONTENT.value());
			rs.setMessage("Deleted Successfully");
			return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
		}
		ResponseStructure<String> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.NOT_FOUND.value());
		rs.setMessage("Cannot delete");
		return new ResponseEntity<ResponseStructure<String>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ResponseStructure<List<Enquiry>>> filter(FilterDto dto) {
		Enquiry enquiry = new Enquiry();
		BeanUtils.copyProperties(dto, enquiry);
		Example<Enquiry> of = Example.of(enquiry);
		List<Enquiry> all = enquiryRepository.findAll(of);
		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Filtered Successfully");
		rs.setData(all);
		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<?> getEnquiriesByCounsellor(Integer cid) {
		Counsellor counsellor = counsellorRepository.findById(cid)
				.orElseThrow(() -> new CounsellorNotFound("Counsellor does not exist"));
		List<Enquiry> enquiries = counsellor.getEnquiries();
		
		ResponseStructure<List<Enquiry>> rs = new ResponseStructure<>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Filtered Successfully");
		rs.setData(enquiries);
		return new ResponseEntity<ResponseStructure<List<Enquiry>>>(rs, HttpStatus.OK);
	}
}
