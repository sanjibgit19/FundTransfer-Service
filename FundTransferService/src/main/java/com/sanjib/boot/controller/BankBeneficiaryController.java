package com.sanjib.boot.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sanjib.boot.model.BankModel;
import com.sanjib.boot.model.BeneficiaryModel;
import com.sanjib.boot.service.BankBeneficiaryService;
import com.sanjib.boot.util.ResponseModel;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@RequestMapping("/bank")
public class BankBeneficiaryController {
	@Autowired
	private BankBeneficiaryService bankBeneficiaryService;
	
	@PostMapping(value = "/beneficiary", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> addBeneficiary(@Valid @RequestBody BeneficiaryModel beneficiaryModel){
		ResponseEntity<ResponseModel> responseEntity=null;
		try {
			responseEntity=bankBeneficiaryService.addBeneficiary(beneficiaryModel);
			log.info("Service Call Done");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception While Service Call");
		}
		return responseEntity;
	}//addBeneficiary(--)
	
	@PostMapping(value = "/bankAccount", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ResponseModel> addBankAccount(@Valid @RequestBody BankModel bankModel){
		ResponseEntity<ResponseModel> responseEntity=null;
		try {
			responseEntity=bankBeneficiaryService.addBankAccount(bankModel);
			log.info("Service Call Done");
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception While Service Call");
		}
		return responseEntity;
	}//addBankAccount(-)

}//class