package com.sanjib.boot.util;

import lombok.Data;

@Data
public class ResponseModel {
	
	private Integer statusCode;
	
	private String succMsg;
	
	private String failureMsg;
	
	private String userName;
	
	private Integer accountNumber;
	
	private Integer password;

}//class
