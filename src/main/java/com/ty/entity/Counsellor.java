package com.ty.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.ty.enums.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Table(name = "counsellor_info")
@Data
public class Counsellor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer cid;

	@NotNull
	private String name;

	@Column(unique = true)
	@Size(min = 5,max = 20,message = "Email should be having charecters between 5 and 20")
	@Email(message = "Invalid email")
	private String email;

	@Column(unique = true)
	private Long phone;
	
	private String password;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Status status=Status.ACTIVE;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "counsellor")
	private List<Enquiry> enquiries;

	@CreationTimestamp
	private LocalDateTime createdDate;

	@UpdateTimestamp
	private LocalDateTime lastUpdate;
}
