package com.sanjib.boot.model;

import lombok.Data;

@Data
public class FundTransferRequestModel {
	
	private String userId;
	private Integer fromAcc;
	private Float amount;
	private Integer toAcc;

}//class
