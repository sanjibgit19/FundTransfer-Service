package com.sanjib.boot.service;

import org.springframework.http.ResponseEntity;

import com.sanjib.boot.Entity.RegistrationEntity;
import com.sanjib.boot.model.RegistrationModel;
import com.sanjib.boot.util.ResponseModel;

public interface AuthService {
	public ResponseEntity<?> registerUser(RegistrationModel regModel);// throws Exception;

}//interface
