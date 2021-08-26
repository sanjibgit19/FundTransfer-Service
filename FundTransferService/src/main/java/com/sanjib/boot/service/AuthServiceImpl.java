package com.sanjib.boot.service;

import java.security.SecureRandom;
import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjib.boot.Entity.BankEntity;
import com.sanjib.boot.Entity.RegistrationEntity;
import com.sanjib.boot.model.ERole;
import com.sanjib.boot.model.RegistrationModel;
import com.sanjib.boot.model.Role;
import com.sanjib.boot.model.User;
import com.sanjib.boot.repository.RoleRepository;
import com.sanjib.boot.repository.RegistrationRepository;
import com.sanjib.boot.repository.UserRepository;
import com.sanjib.boot.util.ResponseMessage;
import com.sanjib.boot.util.ResponseModel;

import lombok.extern.slf4j.Slf4j;


@Service
@Slf4j
@SuppressWarnings("unused")
public class AuthServiceImpl implements AuthService {

	@Autowired
	private RegistrationRepository regRepo;
	@Autowired
	RoleRepository roleRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Autowired
	PasswordEncoder encoder;
	

	@Transactional
	@Override
	public ResponseEntity<?> registerUser(RegistrationModel regModel) {
		Integer password=null;
		RegistrationEntity regEntity=null;
		BankEntity bankEntity=null;
		ResponseModel responseModel=null;
		RegistrationEntity regEntityNew=null;
		try {
			//Copying Model object to entity
			regEntity =new RegistrationEntity();
			regEntity.setName(regModel.getName());
			regEntity.setMobileNumber(regModel.getMobileNumber());
			regEntity.setEmailId(regModel.getEmailId());
			regEntity.setAddress(regModel.getAddress());
			regEntity.setPanNumber(regModel.getPanNumber());
			regEntity.setAdharNumber(regModel.getAdharNumber());
			regEntity.setEmployerName(regModel.getEmployerName());
			//preparing for password
			password=new SecureRandom().nextInt(10000);
			regEntity.setPassword(String.valueOf(password));
			//regEntity.setPassword(encoder.encode( String.valueOf(password)));
			if(regRepo.existsByMobileNumber(regEntity.getMobileNumber())) {
				return ResponseEntity.badRequest().body( new ResponseMessage("Mobile Number is already Register"));
			}//if
			if(regRepo.existsByEmailId(regEntity.getEmailId())) {
				return ResponseEntity.badRequest().body( new ResponseMessage("Error: emailId is already in use"));
			}//if
			
			regEntityNew= regRepo.save(regEntity);
			//preparing response
			responseModel=new ResponseModel();
			responseModel.setStatusCode(200);;
			responseModel.setSuccMsg("User Registraion Done");
			responseModel.setUserName(regEntityNew.getUserName());
			responseModel.setPassword(password);
			//preparing for User Object to implement security
			User user=new User();
			user.setUsername(regEntityNew.getUserName());
			user.setPassword(encoder.encode( String.valueOf(password)));
			//preparing for role
			Set<String> strRoles =regModel.getRole();
			//Set<String> strRoles = new HashSet<>();
			//strRoles.add(strRole);
			Set<Role> roles=new HashSet<>();
			if(strRoles==null) {
				Role userRole=roleRepo.findByName(ERole.ROLE_USER)
						.orElseThrow(()->new RuntimeException("Error:User Role Not Found "));
				roles.add(userRole);
			}else {
				strRoles.forEach(role->{
					switch(role) {
					case "admin":
						Role adminRole=roleRepo.findByName(ERole.ROLE_ADMIN)
						.orElseThrow(()->new RuntimeException("Admin Role Not Found"));
						roles.add(adminRole);
						break;
						
					default: 
						Role userRole=roleRepo.findByName(ERole.ROLE_USER)
						.orElseThrow(()-> new RuntimeException("User Role Not Found "));
						roles.add(userRole);
					}//switch()
				});
			}//else
			
			user.setRoles(roles);
			userRepo.save(user);
			log.info("User Registration Done");
		} catch (Exception e) {
		e.printStackTrace();
		 throw e;
		}
			return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}//registerUser(-)


}//class