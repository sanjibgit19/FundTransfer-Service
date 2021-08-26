package com.sanjib.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sanjib.boot.service.FundTransferService;
import com.sanjib.boot.util.ResponseModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
//@RequestMapping(value = "/api/auth")
@RequestMapping(value = "/bank")
@Validated
@Api
public class FundTransferController {
	
	@Autowired
	private FundTransferService fundTransferService;
	
	@PutMapping(value = "/transferFund", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiOperation(value = "PUT API",response = ResponseEntity.class)
	 public ResponseEntity<ResponseModel> fundTransfer(@RequestParam("fromAcc") Integer fromAcc, @RequestParam("amount") Float amount, @RequestParam("toAcc") Integer toAcc){
		ResponseEntity<ResponseModel> responseEntity=null;
		responseEntity= fundTransferService.fundTransfer(fromAcc, amount, toAcc);
		log.info("Sevice Call successfully Done");
		return responseEntity;
	 }//
	
}//class
