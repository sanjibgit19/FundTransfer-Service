package com.sanjib.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sanjib.boot.Entity.BankEntity;

@Repository
public interface BankRepository extends JpaRepository<BankEntity, Integer> {
	
	public BankEntity getByAccountNumber(Integer accNO);
	
	Boolean existsByAccountNumber(Integer accNo);
	
	
	//@Transactional
	@org.springframework.transaction.annotation.Transactional
	@Modifying
	@Query("update BankEntity b set b.initialBalance=:amount where b.accountNumber=:accNO")
	Integer updateByAccountNumber(@Param("amount") Float amount, @Param("accNO") Integer accNO);
	

}//interface
