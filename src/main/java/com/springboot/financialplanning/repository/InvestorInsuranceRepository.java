package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.InvestorInsurance;

public interface InvestorInsuranceRepository extends JpaRepository<InvestorInsurance, Integer>{

	List<InvestorInsurance> findByInvestorIdAndInsuranceId(int iid, int inid);

}
