package com.sanjib.boot.exception;

public class InsufficientBalanceException extends RuntimeException {
	
	private final String errorMessage;
	private final String errorReason;

	public InsufficientBalanceException(String errorMessage, String errorReason) {
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
