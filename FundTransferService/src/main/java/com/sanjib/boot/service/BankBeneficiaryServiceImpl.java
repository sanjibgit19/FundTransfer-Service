package com.sanjib.boot.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sanjib.boot.Entity.BankEntity;
import com.sanjib.boot.Entity.BeneficiaryEntity;
import com.sanjib.boot.model.BankModel;
import com.sanjib.boot.model.BeneficiaryModel;
import com.sanjib.boot.repository.BankRepository;
import com.sanjib.boot.repository.BeneficiaryRepository;
import com.sanjib.boot.util.ResponseModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BankBeneficiaryServiceImpl implements BankBeneficiaryService {

	@Autowired
	BankRepository bankRepo;
	
	@Autowired
	BeneficiaryRepository beneficiaryRepo;
	
	@Transactional
	@Override
	public ResponseEntity<ResponseModel> addBeneficiary(BeneficiaryModel beneficiaryModel) {
		ResponseModel responseModel=null;
		BeneficiaryEntity beneficiaryEntity=null;
		BeneficiaryEntity beneficiaryEntity2=null;
		try {
			//copying model to entity 
			beneficiaryEntity=new BeneficiaryEntity();
			BeanUtils.copyProperties(beneficiaryModel, beneficiaryEntity);
			beneficiaryEntity.setInitialBalance(4000F);
			beneficiaryEntity2=beneficiaryRepo.save(beneficiaryEntity);
			if (beneficiaryEntity2!=null) {
				responseModel=new ResponseModel();
				responseModel.setStatusCode(200);
				responseModel.setSuccMsg("Beneficiary Added Successfully");
				responseModel.setAccountNumber(beneficiaryEntity2.getAccountNumber());
			} else {
				responseModel=new ResponseModel();
				responseModel.setStatusCode(400);
				responseModel.setFailureMsg("Beneficiary Not Added");
				return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.INTERNAL_SERVER_ERROR);
			}//else
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception While adding Beneficiary Account");
			throw e;
		}
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}//addBeneficiary()

	@Transactional
	@Override
	public ResponseEntity<ResponseModel> addBankAccount(BankModel bankModel) {
		ResponseModel responseModel=null;
		BankEntity bankEntity=null;
		BankEntity bankEntity2=null;
		try {
			//copying model to entity 
			bankEntity=new BankEntity();
			BeanUtils.copyProperties(bankModel, bankEntity);
			bankEntity.setInitialBalance(30000F);
			bankEntity2=bankRepo.save(bankEntity);
			if (bankEntity2!=null) {
				//responseModel=new ResponseModel();
				responseModel.setStatusCode(200);
				responseModel.setSuccMsg("Banck Account Added Successfully");
				responseModel.setAccountNumber(bankEntity2.getAccountNumber());
			} else {
				responseModel=new ResponseModel();
				responseModel.setStatusCode(400);
				responseModel.setFailureMsg("Banck Account Not Added");
				return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.INTERNAL_SERVER_ERROR);
			}//else
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Exception While adding Bank Account");
			throw e;
		}
		return new ResponseEntity<ResponseModel>(responseModel,HttpStatus.OK);
	}//addBankAccount(-)

}//class
