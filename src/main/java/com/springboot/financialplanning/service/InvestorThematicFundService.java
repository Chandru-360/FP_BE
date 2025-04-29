package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.InvestorThematicFund;
import com.springboot.financialplanning.repository.InvestorThematicFundRepository;

@Service
public class InvestorThematicFundService {

	@Autowired
	private InvestorThematicFundRepository investorThematicFundRepository;
	
	public InvestorThematicFund insert(InvestorThematicFund investorThematicFund) {
		
		return investorThematicFundRepository.save(investorThematicFund);
	}

	public List<InvestorThematicFund> getAllThematicFund(Pageable pageable) {
		return investorThematicFundRepository.findAll(pageable).getContent();
	}

	public List<InvestorThematicFund> getThematicFundDetailsByIds(int iid, int tid) {
		return investorThematicFundRepository.findByInvestorIdAndThematicFundId(iid,tid);
	}

	public InvestorThematicFund getByInvestorThematicFundId(int tid) throws InvalidIdException {
		Optional<InvestorThematicFund> optional =  investorThematicFundRepository.findById(tid);
		if(!optional.isPresent()){
			throw new InvalidIdException("InvestorThematicFund ID Invalid");
		}
		return optional.get();
	}

	public void deleteThematicFundDetails(InvestorThematicFund investorThematicFund) {
		investorThematicFundRepository.delete(investorThematicFund);
	}

}
