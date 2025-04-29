package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.InvestorInsurance;
import com.springboot.financialplanning.repository.InvestorInsuranceRepository;

@Service
public class InvestorInsuranceService {

	@Autowired
	private InvestorInsuranceRepository investorInsuranceRepository;
	
	public InvestorInsurance insert(InvestorInsurance investorInsurance) {
		
		return investorInsuranceRepository.save(investorInsurance);
	}
	
	public List<InvestorInsurance> getAllInsurance(Pageable pageable) {
		return investorInsuranceRepository.findAll(pageable).getContent();
	}

	public void deleteInsuranceDetails(InvestorInsurance investorInsurance) {
		investorInsuranceRepository.delete(investorInsurance);
		
	}

	public InvestorInsurance getByInvestorInsuranceId(int id) throws InvalidIdException {
		Optional<InvestorInsurance> optional =  investorInsuranceRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("InvestorInsurance ID Invalid");
		}
		return optional.get();
	}

	public List<InvestorInsurance> getInsuranceDetailsByIds(int iid, int inid) {
		return investorInsuranceRepository.findByInvestorIdAndInsuranceId(iid, inid);
	}

}
