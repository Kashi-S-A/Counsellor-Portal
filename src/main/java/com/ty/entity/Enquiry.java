package com.ty.entity;

import com.ty.enums.ClassMode;
import com.ty.enums.Course;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "enquiry_info")
@Data
public class Enquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eid;
	
	private String name;
	
	@Column(unique = true)
	private String email;
	
	private Long phone;
	
	@Enumerated(EnumType.STRING)
	private Course course;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private ClassMode classMode=ClassMode.OFFLINE;
	
	@Column(nullable = false)
	private Double fees;
	
	@ManyToOne
	@JoinColumn(name = "cid")
	private Counsellor counsellor;
}
