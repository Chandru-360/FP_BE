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

import com.enums.InvestmentType;
import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.InvestorInsurance;

@SpringBootTest
public class InvestorInsuranceTest {

	@Autowired
	InvestorInsuranceRepository investorInsuranceRepository;

	@Autowired
	InvestorRepository investorRepository;

	@Autowired
	InsuranceRepository insuranceRepository;

	public final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Disabled
	@Test
	void saveMethodTest() {

		InvestorInsurance c = new InvestorInsurance(1, LocalDate.now(), (double) 240000, null, null,
				InvestmentType.YEARLY, null, null);

		InvestorInsurance savedInvestorInsurance = investorInsuranceRepository.save(c);
		LOGGER.log(Level.INFO, "savedInvestorInsurance :" + savedInvestorInsurance);

	}

	@Test
	void getAllInsuranceByInvestorIdAndInsuranceIDTest() {
		int investorId = 17; // Investor ID for which you want to retrieve insurance
		int insuranceId = 16;

		Optional<Investor> existingInvestor = investorRepository.findById(investorId);
		if (existingInvestor.isPresent()) {
			Optional<Insurance> existingInsurance = insuranceRepository.findById(insuranceId);
			if (existingInsurance.isPresent()) {
				List<InvestorInsurance> investorInsuranceList = investorInsuranceRepository
						.findByInvestorIdAndInsuranceId(investorId, insuranceId);
				if (!investorInsuranceList.isEmpty()) {
					LOGGER.log(Level.INFO, "List of Investor Insurances for Investor ID " + investorId
							+ " and Insurance ID " + insuranceId);
					investorInsuranceList.forEach((p) -> LOGGER.log(Level.INFO, p.toString()));
				} else {
					LOGGER.log(Level.INFO, "No Investor Insurances found for Investor ID " + investorId
							+ " and Insurance ID " + insuranceId);
				}
			} else {
				LOGGER.log(Level.INFO, "Insurance ID " + insuranceId + " is invalid");
			}
		} else {
			LOGGER.log(Level.INFO, "Investor ID " + investorId + " is invalid");
		}
	}

	@Test
	void getAllInvestorInsuranceTest() {
		List<InvestorInsurance> InvestorInsuranceList = investorInsuranceRepository.findAll();
		InvestorInsuranceList.forEach((p) -> {
			LOGGER.log(Level.INFO, "List of InvestorInsurance" + p);
		});
	}
	

	@Disabled
	@Test
	void deleteInvestorInsuranceByIdMethodTest() {
		int id=18;
		investorInsuranceRepository.deleteById(id);
		Optional<InvestorInsurance> opt=investorInsuranceRepository.findById(18);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "InvestorInsurance not present..");
		}
		}
	
	
	@Test
	void UpdateTest() {
		int id=18;
		Optional<InvestorInsurance> existingInvestorInsurance=investorInsuranceRepository.findById(id);
		if(existingInvestorInsurance.isPresent()) {
		InvestorInsurance obj=existingInvestorInsurance.get();
		LOGGER.log(Level.INFO, "existingInvestorInsurance :" +obj);
		
		//update the object
		obj.setYearlyAmount((double) 50000);
		
		
		InvestorInsurance updatedInvestorInsurance=investorInsuranceRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedInvestorInsurance :" +updatedInvestorInsurance);
		}
		else {
			LOGGER.log(Level.INFO, "InvestorInsurance not present..");
		}
	}
	

}
