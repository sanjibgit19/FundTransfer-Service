package com.sanjib.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sanjib.boot.Entity.BankEntity;
import com.sanjib.boot.Entity.BeneficiaryEntity;
import com.sanjib.boot.exception.BankAccountNotFoundException;
import com.sanjib.boot.exception.FundTransferNotDoneException;
import com.sanjib.boot.exception.InsufficientBalanceException;
import com.sanjib.boot.exception.LimitExitException;
import com.sanjib.boot.repository.BankRepository;
import com.sanjib.boot.repository.BeneficiaryRepository;
import com.sanjib.boot.util.ResponseModel;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FundTransferServiceImpl implements FundTransferService {

	@Autowired
	private BankRepository bankRepo;
	
	@Autowired
	private BeneficiaryRepository beneficiaryRepo;

	@Override
	public ResponseEntity<ResponseModel> fundTransfer(Integer fromAccNo, Float transferAmount, Integer toAccNo) {
		Float fromAccAvailableAmount = null;
		Float toAccAvailableAmount = null;
		BankEntity bankEntity = null;
		BeneficiaryEntity beneficiaryEntity = null;
		ResponseModel responseModel = null;
		try {
			// Transferring the amount
			bankEntity = bankRepo.getByAccountNumber(fromAccNo);
			beneficiaryEntity = beneficiaryRepo.getByAccountNumber(toAccNo);
			if (bankEntity != null && beneficiaryEntity != null) {
				// Calculating Balance
				fromAccAvailableAmount = bankEntity.getInitialBalance();
				if(fromAccAvailableAmount<transferAmount) {
					throw new InsufficientBalanceException("Not Having sufficient Balance", "Invalid Input");
				}//if
				else if (transferAmount>40000) {
					throw new LimitExitException("Can not transfer More than 40k","Invalid Input");
					
				}//else-if
				else {
					Float currentFromAccAvailableAmount = fromAccAvailableAmount - transferAmount;
					toAccAvailableAmount = beneficiaryEntity.getInitialBalance();
					Float currentToAccAvailableAmount = toAccAvailableAmount + transferAmount;
					// updating account Balance
					Integer count1 = bankRepo.updateByAccountNumber(currentFromAccAvailableAmount, fromAccNo);
					Integer count2 = beneficiaryRepo.updateByAccountNumber(currentToAccAvailableAmount, toAccNo);
					if (count1 != null && count2 != null) {
						responseModel = new ResponseModel();
						responseModel.setStatusCode(4000);
						responseModel.setSuccMsg("Fund Transfer Successfull");
					} else {
						throw new FundTransferNotDoneException("Try after sometime", "Internel Server problem");
					}// inner if-else
				}//else
		}else {
			throw new BankAccountNotFoundException("Bank Account Not Found", "Invalid Input");
		} // if-else
			
		}catch (Exception e) {
			e.printStackTrace();
			log.error("Exception for Repository Call");
			throw e;
		} // catch()
		return new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK);
	}// fundTransfer(---)

}
// class