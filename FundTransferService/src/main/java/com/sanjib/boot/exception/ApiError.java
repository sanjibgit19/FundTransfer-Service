package com.sanjib.boot.exception;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiError {
	
	private Long errorCode;
	
	private String errorMesssgae;
	
	private String errorReason;
	
	private Date date;
	

}//class
