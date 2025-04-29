package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.InvestorInsurance;
import com.springboot.financialplanning.repository.InvestorRepository;



@Service
public class InvestorService {

	@Autowired
	private InvestorRepository investorRepository;
	
	public Investor insert(Investor investor) {
	  return investorRepository.save(investor);
	}

	public Investor saveInvestor(Investor investor) {
		return investorRepository.save(investor);
	}
	
	public Investor getByInvestorId(int iid) throws InvalidIdException {
		Optional<Investor> optional=investorRepository.findById(iid);
		if(!optional.isPresent())
			throw new InvalidIdException("Investor id Invalid");
		return optional.get();
	}

	public List<Investor> getAll(Pageable pageable) {
		return investorRepository.findAll(pageable).getContent();
	}

	public void deleteInvestor(Investor investor) {
		investorRepository.delete(investor);
		
	}

	

}