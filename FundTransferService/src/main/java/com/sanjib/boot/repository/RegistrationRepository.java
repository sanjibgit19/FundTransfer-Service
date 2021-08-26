package com.sanjib.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sanjib.boot.Entity.RegistrationEntity;

@Repository
public interface RegistrationRepository extends JpaRepository<RegistrationEntity, String> {

	Boolean existsByMobileNumber(Long mobileNumber);

	  Boolean existsByEmailId(String email);
}//interface
