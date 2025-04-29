package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.enums.Category;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.repository.InsuranceRepository;



@Service
public class InsuranceService {

	@Autowired
	private InsuranceRepository insuranceRepository;

	public Insurance insert(Insurance insurance) {
		return insuranceRepository.save(insurance);
	}
	public Insurance getByid(int inid) throws InvalidIdException {
		Optional<Insurance> optional = insuranceRepository.findById(inid);
		if (!optional.isPresent())
			throw new InvalidIdException("Insurance id Invalid");
		return optional.get();
	}
	public List<Insurance> getAll(Pageable pageable) {
		return insuranceRepository.findAll(pageable).getContent();
	}
	public List<Insurance> getInsuranceByCompany(int cid) {
		return insuranceRepository.findByCompanyId(cid);
	}
	public void deleteInsurance(Insurance insurance) {
		insuranceRepository.delete(insurance);
		
	}
	public List<Insurance> getInsurancesByCategory(Category category) {
		return insuranceRepository.findByCategory(category);
	}
	 public List<Insurance> findByFundType(String fundType) {
	        return insuranceRepository.findByFundType(fundType);
	    }
	 public List<Insurance> findByCategory(Category category) {
	        return insuranceRepository.findByCategory(category);
	    }
	public List<Insurance> findByCompany(String Name) {
		return insuranceRepository.findByCompany(Name);
	}
	
}

