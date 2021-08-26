package com.sanjib.boot.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BankModel {

	@NotNull(message = "Account Number must have provide")
	private Integer accountNumber;
	
	@NotEmpty(message = "Please provide IFSC Code")
	private String ifsccode;
	
	@NotEmpty(message = "Please provide Branch Name")
	private String branchName;
	
}//class
