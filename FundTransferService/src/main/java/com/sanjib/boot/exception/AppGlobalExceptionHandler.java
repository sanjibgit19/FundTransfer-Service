package com.sanjib.boot.exception;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.RollbackException;
import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@RestController
@ControllerAdvice
//@RestControllerAdvice
public class AppGlobalExceptionHandler extends ResponseEntityExceptionHandler {

	@Override
	public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		System.out.println("Entering to handler method for validation");
		List<String> details = new ArrayList<>();
		details = ex.getFieldErrors().stream().map(e1->e1.getField()+" : "+e1.getDefaultMessage()).collect(Collectors.toList());
		
		ValidationErrorResponse error=new ValidationErrorResponse(4000L, details);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}//handleMethodArgumentNotValid(---)
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ApiError> handleAllException(Exception ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(5000L, errorMessage,"Invalid Input" , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	@ExceptionHandler(ConstraintViolationException.class)
	public final ResponseEntity<ApiError> handleConstraintViolationException(ConstraintViolationException ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(5000L, errorMessage,"Invalid Input" , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	@ExceptionHandler(org.hibernate.exception.ConstraintViolationException.class)
	public final ResponseEntity<ApiError> handleHBConstraintViolationException(org.hibernate.exception.ConstraintViolationException ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(5000L, errorMessage,"Invalid Input" , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	@ExceptionHandler(TransactionSystemException.class)
	public final ResponseEntity<ApiError> handleTransactionSystemException(TransactionSystemException ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(6000L, errorMessage,"Wrong Input" , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	
	@ExceptionHandler(RollbackException.class)
	public final ResponseEntity<ApiError> handleRollbackException(RollbackException ex, WebRequest request){
		String errorMessage=ex.getLocalizedMessage();
		ApiError error=new ApiError(5000L, errorMessage,"Invalid Input" , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	@ExceptionHandler(FundTransferNotDoneException.class)
	public final ResponseEntity<ApiError> handleFundTransferNotDoneException(FundTransferNotDoneException ex, WebRequest request){
		String errorMessage=ex.getErrorMessage();
		String errorReason=ex.getErrorReason();
		ApiError error=new ApiError(5000L, errorMessage,errorReason , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	@ExceptionHandler(BankAccountNotFoundException.class)
	public final ResponseEntity<ApiError> handleBankAccountNotFoundException(BankAccountNotFoundException ex, WebRequest request){
		System.out.println("Entering to handler method for Bank Account Exception");
		String errorMessage=ex.getErrorMessage();
		String errorReason=ex.getErrorReason();
		ApiError error=new ApiError(6000L, errorMessage,errorReason , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	
	@ExceptionHandler(InsufficientBalanceException.class)
	public final ResponseEntity<ApiError> handleInsufficientBalanceException(InsufficientBalanceException ex, WebRequest request){
		String errorMessage=ex.getErrorMessage();
		String errorReason=ex.getErrorReason();
		ApiError error=new ApiError(7000L, errorMessage,errorReason , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()
	@ExceptionHandler(LimitExitException.class)
	public final ResponseEntity<ApiError> handleLimitExitException(LimitExitException ex, WebRequest request){
		String errorMessage=ex.getErrorMessage();
		String errorReason=ex.getErrorReason();
		ApiError error=new ApiError(8000L, errorMessage,errorReason , new Date());
		ResponseEntity<ApiError> responseEntity=new ResponseEntity<ApiError>(error,HttpStatus.BAD_REQUEST);
		return responseEntity;
	}//handleException()


}// class
