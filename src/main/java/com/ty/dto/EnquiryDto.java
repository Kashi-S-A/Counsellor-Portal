package com.ty.dto;

import com.ty.enums.ClassMode;
import com.ty.enums.Course;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnquiryDto {

	private Integer eid;
	
	private String name;
	
	private Course course;
	
	private ClassMode classMode;
}
