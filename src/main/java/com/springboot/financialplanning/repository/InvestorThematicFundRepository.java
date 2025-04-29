package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.InvestorThematicFund;

public interface InvestorThematicFundRepository extends JpaRepository<InvestorThematicFund, Integer>{

	List<InvestorThematicFund> findByInvestorIdAndThematicFundId(int iid, int tid);

}
