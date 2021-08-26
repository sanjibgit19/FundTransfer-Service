package com.sanjib.boot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sanjib.boot.Entity.BeneficiaryEntity;

@Repository
public interface BeneficiaryRepository extends JpaRepository<BeneficiaryEntity, Integer> {

	public BeneficiaryEntity getByAccountNumber(Integer accNO);

	@Transactional
	@Modifying
	@Query("update BeneficiaryEntity b set b.initialBalance=:amount where b.accountNumber=:accNO")
	Integer updateByAccountNumber(@Param("amount") Float amount, @Param("accNO") Integer accNO);

}// interface
