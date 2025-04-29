package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.InvestorMutualFund;



public interface InvestorMutualFundRepository extends JpaRepository<InvestorMutualFund, Integer>{

	List<InvestorMutualFund> findByInvestorIdAndMutualFundId(int iid, int mfid);

}

