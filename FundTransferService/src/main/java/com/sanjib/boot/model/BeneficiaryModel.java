package com.sanjib.boot.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class BeneficiaryModel {
	
	@NotNull(message = "Account Number must have provide")
	private Integer beneficiaryAccountNumber;
	
	@NotEmpty(message = "Please Enter Account Holder Name")
	private String accountHolderName;
	
	@NotEmpty(message = "Please provide IFSC Code")
	private String ifsccode;
	
	@NotEmpty(message = "Please provide Branch Name")
	private String branchName;
	
	@NotNull(message = "Please Enter Bank Account Number")
	private Integer accountNumber;
	

}//class