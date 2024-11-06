package com.ty.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ty.entity.Enquiry;

public interface EnquiryRepository extends JpaRepository<Enquiry, Integer> {

	Optional<Enquiry> findByEmail(String email);
}
