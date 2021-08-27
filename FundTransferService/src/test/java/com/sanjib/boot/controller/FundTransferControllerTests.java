package com.sanjib.boot.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sanjib.boot.model.FundTransferRequestModel;
import com.sanjib.boot.service.FundTransferServiceImpl;
import com.sanjib.boot.util.ResponseModel;

//@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class FundTransferControllerTests {

	@InjectMocks
	FundTransferController fundTransferController;

	@Mock
	FundTransferServiceImpl fundTransferServiceImpl;

	static FundTransferRequestModel requestModel;
	static ResponseModel responseModel;

	@BeforeAll
	public static void createObject() {
		requestModel = new FundTransferRequestModel();
		requestModel.setFromAcc(1111);
		requestModel.setAmount(2000F);
		requestModel.setUserId("ICICI101");

		responseModel = new ResponseModel();
		responseModel.setStatusCode(4000);
		responseModel.setSuccMsg("Fund Transfer Successfull");
	}// createObject()

	@Test
	@DisplayName(value = " Testing for fundTransfer() api:: positive Senerio")
	public void fundTransferTest() {
		// context
		when(fundTransferServiceImpl.fundTransfer(1111, 2000F, 2222, "ICICI101"))
				.thenReturn(new ResponseEntity<ResponseModel>(responseModel, HttpStatus.OK));

		// event
		ResponseEntity<ResponseModel> responseEntity = fundTransferController.fundTransfer(requestModel);

		// outcome
		assertEquals(HttpStatus.OK, responseEntity.getBody().getStatusCode());

	}// fundTransferTest()

	@Test
	@DisplayName(value = " Testing for fundTransfer() api:: negative Senerio")
	public void fundTransferTest1() {
		// context
		when(fundTransferServiceImpl.fundTransfer(1111, 2000F, 2222, "ICICI101")).thenReturn(null);

		// Event outcome
		// assertThrows(NullPointerException.class,()->fundTransferController.fundTransfer(requestModel));

		ResponseEntity<ResponseModel> responseEntity = fundTransferController.fundTransfer(requestModel);

		// outcome
		assertEquals(null, responseEntity);

	}// fundTransferTest()

}// class
