package com.sanjib.boot.service;

import org.springframework.http.ResponseEntity;

import com.sanjib.boot.model.BankModel;
import com.sanjib.boot.model.BeneficiaryModel;
import com.sanjib.boot.util.ResponseModel;

public interface BankBeneficiaryService {
	public ResponseEntity<?> addBeneficiary(BeneficiaryModel beneficiaryModel);
	
	public ResponseEntity<?> addBankAccount(BankModel bankModel);

}//interface
