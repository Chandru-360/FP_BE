package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.repository.CompanyRepository;


@Service
public class CompanyService {
 @Autowired
 private CompanyRepository companyRepository;
 
 public Company insert(Company company) {
		return companyRepository.save(company);
	}
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}
	
	public List<Company> getAllCompanies() {
		List<Company> list = companyRepository.findAll();
		return list;
		 
	}
	
	public Company getCompanyById(int cid) throws InvalidIdException {
		Optional<Company> optional=companyRepository.findById(cid);
		if(!optional.isPresent())
			throw new InvalidIdException("companyid Invalid");
		return optional.get();
	}
	
	public void deleteCompany(int cid) {
		companyRepository.deleteById(cid);
	}
//	public void onboardCompany(int companyId) throws InvalidIdException {
//		Company company = getCompanyById(companyId);
//		 saveCompany(company);
//	}
//	public List<Company> findAll() {
//		List<Company> list = companyRepository.findAll();
//		return list;
//	}
	
}