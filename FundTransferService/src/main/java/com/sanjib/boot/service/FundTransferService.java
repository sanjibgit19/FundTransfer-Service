package com.sanjib.boot.service;

import org.springframework.http.ResponseEntity;


import com.sanjib.boot.util.ResponseModel;

public interface FundTransferService {

 public ResponseEntity<ResponseModel> fundTransfer(Integer fromAccNo,Float transferAmount,Integer toAccNo);//throws Exception;

}//class
