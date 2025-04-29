package com.springboot.financialplanning.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.springboot.financialplanning.model.Withdraw;

public interface WithdrawRepository extends JpaRepository<Withdraw, Integer> {

	
	 @Query("SELECT w FROM Withdraw w WHERE w.investor.id = ?1")
	  List<Withdraw> findByInvestorId(int investorId);


	 @Query("SELECT w FROM Withdraw w WHERE w.investor.id = ?1 AND w.withdrawalDate = ?2")
	List<Withdraw> findByInvestorIdAndInvestorMutualFundInvestmentDate(int investorId, LocalDate dateOfInvestment);

}
