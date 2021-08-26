package com.sanjib.boot.model;

import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class RegistrationModel {
	

	@NotEmpty(message = "Name can not be Blank")
	private String name;
	
	private Set<String> role;
	
	@NotNull(message ="Please Enter Your Adhar Number")
	private Long mobileNumber;
	
	@Email
	private String emailId;
	
	@NotEmpty(message = "Please Enter your Adress")
	private String address;
	
	@NotEmpty(message = "Please Enter your PAN number")
	private String panNumber;
	
	@NotNull(message ="Please Enter Your Adhar Number")
	private Long adharNumber;
	
	@NotEmpty(message = "Please Enter your Company Name")
	private String employerName;
	

}//class
