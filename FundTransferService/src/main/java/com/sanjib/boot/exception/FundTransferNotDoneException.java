package com.sanjib.boot.exception;

public class FundTransferNotDoneException extends RuntimeException {
	
	private final String errorMessage;
	private final String errorReason;

	public FundTransferNotDoneException(String errorMessage, String errorReason) {
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