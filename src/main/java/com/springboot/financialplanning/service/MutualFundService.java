package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.Category;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.repository.MutualFundRepository;



@Service
public class MutualFundService {
	@Autowired
	private MutualFundRepository mutualFundRepository;

	public MutualFund insert(MutualFund mutualFund) {
		return mutualFundRepository.save(mutualFund);
	}
	
	public List<MutualFund> getAllMutualFunds() {
		List<MutualFund> list = mutualFundRepository.findAll();
		return list;
		 
	}


	public MutualFund getMutualFundById(int cid) throws InvalidIdException {
		Optional<MutualFund> optional = mutualFundRepository.findById(cid);
		if (!optional.isPresent())
			throw new InvalidIdException("MutualFund id Invalid");
		return optional.get();
	}
	
	public void deleteMutualFund(int cid) {
		mutualFundRepository.deleteById(cid);
	}

	
	public List<MutualFund> getMutualFundsByCategory(Category category) {
		return mutualFundRepository.findByCategory(category);
	}

	
	public List<MutualFund> findByCompany(String Name) {
	
		return mutualFundRepository.findByCompany(Name);
	}

	
	
	public List<MutualFund> findByFundType(String fundType) {

		return mutualFundRepository.findByFundType(fundType);
	}
	
	
	
	public List<MutualFund> findByCategory(Category category) {
        return mutualFundRepository.findByCategory(category);
    }

	public List<MutualFund> getAllMutualFundsByCompanyId(int cid) {
		
		return mutualFundRepository.findAllById(cid);
	}
}
