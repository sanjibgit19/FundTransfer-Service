package com.sanjib.boot.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name = "beneficiary_tab")
public class BeneficiaryEntity {
	
	@Id
	@GeneratedValue
	private Integer id;
	
	@NotNull(message = "Account Number must have provide")
	private Integer accountNumber;
	
	@NotEmpty(message = "Please Enter Account Holder Name")
	private String accountHolderName;
	
	@NotEmpty(message = "Please provide IFSC Code")
	private String ifsccode;
	
	@NotEmpty(message = "Please provide Branch Name")
	private String branchName;
	
	@NotNull(message = "Please Enter Balance")
	private Float initialBalance;
	

}//class