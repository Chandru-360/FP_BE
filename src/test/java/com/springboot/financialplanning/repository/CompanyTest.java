package com.springboot.financialplanning.repository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.springboot.financialplanning.model.Company;

@SpringBootTest
public class CompanyTest {

	public final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	CompanyRepository companyRepository;
	
	@Disabled
	@Test
	void saveMethodTest() {
		
		Company c=new Company(1,"JIO","jio@gmail.com","Thematic","123,mumbai","44549841", null);
		
		Company savedCompany=companyRepository.save(c);
		LOGGER.log(Level.INFO, "savedCompany :" +savedCompany);
		
	}
	
	
	@Test
	void UpdateTest() {
		int id=9;
		Optional<Company> existingCompany=companyRepository.findById(id);
		if(existingCompany.isPresent()) {
		Company obj=existingCompany.get();
		LOGGER.log(Level.INFO, "existingCompany :" +obj);
		
		//update the object
		obj.setName("jio health");
		
		
		Company updatedCompany=companyRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedCompany :" +updatedCompany);
		}
		else {
			LOGGER.log(Level.INFO, "Company not present..");
		}
	}
	
	@Test
	void getAllCompaniesTest() {
		List<com.springboot.financialplanning.model.Company> companyList=companyRepository.findAll();
		companyList.forEach((p)->{
			LOGGER.log(Level.INFO, "List of companies"+p);
		});
}
	
	@Test
	void getCompanyByIdTest() {
		int id=4;
		Optional<Company> opt=companyRepository.findById(id);
		Company c=opt.get();
		LOGGER.log(Level.INFO, "company :"+ c);
}
	@Disabled
	@Test
	void deleteCompanyByIdMethodTest() {
		int id=1;
		companyRepository.deleteById(id);
		Optional<Company> opt=companyRepository.findById(1);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "Company not present..");
		}
		}
	
	

}
