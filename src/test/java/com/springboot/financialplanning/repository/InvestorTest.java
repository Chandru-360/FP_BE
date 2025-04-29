package com.springboot.financialplanning.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.Investor;

@SpringBootTest
public class InvestorTest {

	@Autowired
	InvestorRepository investorRepository;

	public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Disabled
	@Test
	void saveMethodTest() {

		Investor c = new Investor(1, "John Doe", "johndoe@example.com", "Bangalore", null, "ABCDE1234F", "Male",
				"9876543210", null);

		Investor savedInvestor = investorRepository.save(c);
		LOGGER.log(Level.INFO, "savedInvestor :" + savedInvestor);

	}

	@Test
	void getInvestorByIdTest() {
		int id = 17;
		Optional<Investor> opt = investorRepository.findById(id);
		Investor c = opt.get();
		LOGGER.log(Level.INFO, "Investor :" + c);
	}

	@Test
	void getAllInvestorsTest() {
		List<Investor> investorsList = investorRepository.findAll();
		investorsList.forEach((p) -> {
			LOGGER.log(Level.INFO, "List of investors" + p);
		});
	}
	
	@Disabled
	@Test
	void deleteInvestorByIdMethodTest() {
		int id=1;
		investorRepository.deleteById(id);
		Optional<Investor> opt=investorRepository.findById(1);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "Investor not present..");
		}
		}
	

	@Test
	void UpdateTest() {
		int id=17;
		Optional<Investor> existingInvestor=investorRepository.findById(id);
		if(existingInvestor.isPresent()) {
		Investor obj=existingInvestor.get();
		LOGGER.log(Level.INFO, "existingInvestor :" +obj);
		
		//update the object
		obj.setDob(LocalDate.now());
		
		
		Investor updatedInvestor=investorRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedInvestor :" +updatedInvestor);
		}
		else {
			LOGGER.log(Level.INFO, "Investor not present..");
		}
	}

}
