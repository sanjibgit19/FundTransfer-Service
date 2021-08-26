package com.sanjib.boot.Entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.Data;

@Data
@Entity
@Table(name = "user_registration")
public class RegistrationEntity {
	
	/*@Id
	@GeneratedValue
	private Integer id;
	*/
	@Id
	@GenericGenerator(name = "gen1", strategy = "com.sanjib.boot.util.CustomSequenceGen")
	@GeneratedValue(generator = "gen1")
	private String userName;
	
	private String name;
	
	private Long mobileNumber;
	
	private String emailId;
	
	private String address;
	
	private String panNumber;
	
	private Long adharNumber;
	
	private String employerName;
	
	private String password;
	
	/*@OneToOne(targetEntity = BankEntity.class,cascade = CascadeType.ALL)
	@JoinColumn(name = "ACCNO_FK" ,referencedColumnName = "accountNumber")
	private BankEntity bankEntity;
	*/
	

}//class
