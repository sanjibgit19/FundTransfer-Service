package com.sanjib.boot.service;

import org.springframework.http.ResponseEntity;

import com.sanjib.boot.model.BankModel;
import com.sanjib.boot.model.BeneficiaryModel;
import com.sanjib.boot.util.ResponseModel;

public interface BankBeneficiaryService {
	public ResponseEntity<ResponseModel> addBeneficiary(BeneficiaryModel beneficiaryModel);
	
	public ResponseEntity<ResponseModel> addBankAccount(BankModel bankModel);

}//interface
