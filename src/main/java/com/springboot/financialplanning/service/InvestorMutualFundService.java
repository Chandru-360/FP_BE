package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.InvestorMutualFund;
import com.springboot.financialplanning.repository.InvestorMutualFundRepository;



@Service
public class InvestorMutualFundService {

	@Autowired
	private InvestorMutualFundRepository investorMutualFundRepository;
	
	public InvestorMutualFund insert(InvestorMutualFund investorMutualFund) {
		return investorMutualFundRepository.save(investorMutualFund);
	}

	public List<InvestorMutualFund> getAllMutualFund(Pageable pageable) {
		return investorMutualFundRepository.findAll(pageable).getContent();
	}

	public List<InvestorMutualFund> getMutualFundDetailsByIds(int iid, int mfid) {
		return investorMutualFundRepository.findByInvestorIdAndMutualFundId(iid,mfid);
	}

	public InvestorMutualFund getByInvestorMutualFundId(int id) throws InvalidIdException {
		Optional<InvestorMutualFund> optional =  investorMutualFundRepository.findById(id);
		if(!optional.isPresent()){
			throw new InvalidIdException("InvestorMutualFund ID Invalid");
		}
		return optional.get();
	}

	public void deleteMutualFundDetails(InvestorMutualFund investorMutualFund) {
		investorMutualFundRepository.delete(investorMutualFund);
		
	}

}