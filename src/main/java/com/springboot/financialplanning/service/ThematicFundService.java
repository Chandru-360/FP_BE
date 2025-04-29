package com.springboot.financialplanning.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enums.Category;
import com.springboot.financialplanning.exception.InvalidCategoryException;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.ThematicFund;
import com.springboot.financialplanning.repository.ThematicFundRepository;



@Service
public class ThematicFundService {
	private List<ThematicFund> thematicFunds = new ArrayList<>();

	@Autowired
	private ThematicFundRepository thematicFundRepository;
	
	public ThematicFund insert(ThematicFund thematicFund) {
		return thematicFundRepository.save(thematicFund);
	}

	public ThematicFund getByid(int tfid) throws InvalidIdException {
		Optional<ThematicFund> optional = thematicFundRepository.findById(tfid);
		if (!optional.isPresent())
			throw new InvalidIdException("ThematicFund id Invalid");
		return optional.get();
	}
	
	public List<ThematicFund> getAllThematicFunds() {
		List<ThematicFund> list = thematicFundRepository.findAll();
		return list;
		 
	}
	
	public ThematicFund getThematicFundById(int cid) throws InvalidIdException {
		Optional<ThematicFund> optional = thematicFundRepository.findById(cid);
		if (!optional.isPresent())
			throw new InvalidIdException("ThematicFund id Invalid");
		return optional.get();
	}
	
	
	public void deleteThematicFund(int cid) {
		thematicFundRepository.deleteById(cid);
	}


	public List<ThematicFund> getThematicFundsByCategory(Category category) {
		return thematicFundRepository.findByCategory(category);
	}


	
	public List<ThematicFund> findByFundType(String fundType) {
        return thematicFundRepository.findByFundType(fundType);
    }
	
	
	 public List<ThematicFund> findByCategory(Category category) {
	        return thematicFundRepository.findByCategory(category);
	    }

	public List<ThematicFund> findByCompany(String Name) {
		
		return thematicFundRepository.findByCompany(Name);
	}

	public List<MutualFund> getAllThematicFundsByCompanyId(int cid) {
		// TODO Auto-generated method stub
		return thematicFundRepository.findAllById(cid);
	}
}