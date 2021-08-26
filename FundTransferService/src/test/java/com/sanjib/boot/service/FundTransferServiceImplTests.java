package com.sanjib.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sanjib.boot.Entity.BankEntity;
import com.sanjib.boot.Entity.BeneficiaryEntity;
import com.sanjib.boot.exception.BankAccountNotFoundException;
import com.sanjib.boot.repository.BankRepository;
import com.sanjib.boot.repository.BeneficiaryRepository;
import com.sanjib.boot.util.ResponseModel;

@SpringBootTest
public class FundTransferServiceImplTests {
	@InjectMocks
	FundTransferServiceImpl serviceImpl;

	@Mock
	BankRepository bankRepo;

	@Mock
	BeneficiaryRepository beneficiaryRepo;

	@Test
	@DisplayName(value = "testing for fundTransfer() API:: Posetive Senerio ")
	public void fundTransferTest() {
		// preparing for context
		when(bankRepo.getByAccountNumber(1111)).thenReturn(new BankEntity(1, 1111, "ICICI", "HYD", 10000F));
		when(beneficiaryRepo.getByAccountNumber(2222)).thenReturn(new BeneficiaryEntity(1, 2222 ,"Sanjib", "ICICI", "HYD",20000F));
		when(bankRepo.updateByAccountNumber(1000F, 1111)).thenReturn(1);
		when(beneficiaryRepo.updateByAccountNumber(1000F, 2222)).thenReturn(1);
		
		//Event
		ResponseEntity<ResponseModel> responseEntity= serviceImpl.fundTransfer(1111, 2000F, 2222);
		
		//Outcome
		assertEquals(4000, responseEntity.getBody().getStatusCode());
		assertEquals("Fund Transfer Successfull", responseEntity.getBody().getSuccMsg());
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
	}// fundTransferTest()
	
	@Test
	@DisplayName(value = "testing for fundTransfer() API:: Negative Senerio 1")
	public void fundTransferTest1() {
		// preparing for context
		when(bankRepo.getByAccountNumber(1111)).thenReturn(null);
		when(beneficiaryRepo.getByAccountNumber(2222)).thenReturn(new BeneficiaryEntity(1, 2222 ,"Sanjib", "ICICI", "HYD",20000F));
		when(bankRepo.updateByAccountNumber(1000F, 1111)).thenReturn(1);
		when(beneficiaryRepo.updateByAccountNumber(1000F, 2222)).thenReturn(1);
		
		//Event
		//serviceImpl.fundTransfer(1111, 2000F, 2222);
		
		//Event Outcome
		assertThrows(BankAccountNotFoundException.class, ()->serviceImpl.fundTransfer(1111, 2000F, 2222));
		
	}// fundTransferTest1()

}// class
