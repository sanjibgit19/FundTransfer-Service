package com.sanjib.boot.Entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
@Table(name = "bank_tab")
public class BankEntity {
	
	/*@Id
	@GeneratedValue
	private Integer id;
	*/
	@Id
	@NotNull(message = "Account Number must have provide")
	private Integer accountNumber;
	
	@NotEmpty(message = "Please provide IFSC Code")
	private String ifsccode;
	
	@NotEmpty(message = "Please provide Branch Name")
	private String branchName;
	
	@NotNull(message = "Please Enter Balance")
	private Float initialBalance;
	
	@OneToMany(targetEntity = BeneficiaryEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "USERACCNO_FK" ,referencedColumnName = "accountNumber")
	private List<BeneficiaryEntity> beneficiaryEntities;
	
	@Column(name = "USERID_FK")
	private String userName;

}//class
