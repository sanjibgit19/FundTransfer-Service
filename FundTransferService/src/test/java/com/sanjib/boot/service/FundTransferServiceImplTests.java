package com.sanjib.boot.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sanjib.boot.Entity.BankEntity;
import com.sanjib.boot.Entity.BeneficiaryEntity;
import com.sanjib.boot.exception.BankAccountNotFoundException;
import com.sanjib.boot.repository.BankRepository;
import com.sanjib.boot.repository.BeneficiaryRepository;
import com.sanjib.boot.util.ResponseModel;


//@ExtendWith(MockitoExtension.class)
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
		when(bankRepo.getByAccountNumber(1111)).thenReturn(new BankEntity(1111, "ICICI", "HYD", 10000F, Stream.of(new BeneficiaryEntity(2222 ,"Sanjib", "ICICI", "HYD",20000F,1111)).collect(Collectors.toList()),"ICICI101"));
		when(beneficiaryRepo.getByAccountNumber(2222)).thenReturn(new BeneficiaryEntity(2222 ,"Sanjib", "ICICI", "HYD",20000F,1111));
		when(bankRepo.updateByAccountNumber(1000F, 1111)).thenReturn(1);
		when(beneficiaryRepo.updateByAccountNumber(1000F, 2222)).thenReturn(1);
		
		//Event
		ResponseEntity<ResponseModel> responseEntity= serviceImpl.fundTransfer(1111, 2000F, 2222,"ICICI101");
		
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
		when(beneficiaryRepo.getByAccountNumber(2222)).thenReturn(null);
		when(bankRepo.updateByAccountNumber(1000F, 1111)).thenReturn(1);
		when(beneficiaryRepo.updateByAccountNumber(1000F, 2222)).thenReturn(1);
		
		//event
		//serviceImpl.fundTransfer(1111, 2000F, 2222,"ICICI101");
		
		//Event and Outcome
		assertThrows(BankAccountNotFoundException.class, ()->serviceImpl.fundTransfer(1111, 2000F, 2222,"ICICI101"));
		
	}// fundTransferTest1()

}// class
