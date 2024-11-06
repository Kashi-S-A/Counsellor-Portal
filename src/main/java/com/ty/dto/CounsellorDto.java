package com.ty.dto;

import java.time.LocalDateTime;

import com.ty.enums.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CounsellorDto {

	private String name;

	private Long phone;

	private String email;

	private LocalDateTime createdDate;

	private Status status;
}
