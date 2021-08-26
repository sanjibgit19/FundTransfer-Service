package com.sanjib.boot.exception;

public class BankAccountNotFoundException extends RuntimeException {
	
	private final String errorMessage;
	private final String errorReason;

	public BankAccountNotFoundException(String errorMessage, String errorReason) {
		super();
		this.errorMessage = errorMessage;
		this.errorReason = errorReason;
	}

	public String getErrorMessage() {
		return errorMessage;
	}//
	
	public String getErrorReason() {
		return errorReason;
	}

}//class
