package com.springboot.financialplanning.repository;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.enums.Category;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.Insurance;

@SpringBootTest
public class InsuranceTest {
	
	@Autowired
	InsuranceRepository insuranceRepository;
	
	@Autowired
	CompanyRepository companyRepository;
	
	public final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Disabled
	@Test
	void saveMethodTest() {

			Insurance c=new Insurance(1, "LIC Life Insurance", Category.LIFE,5000,5,
					"98.87%","Best life insuracne policy for all people","Below 70",500000 );
			
			Insurance savedInsurance=insuranceRepository.save(c);
			LOGGER.log(Level.INFO, "savedInsurance :" +savedInsurance);
		}
		
	@Disabled
	@Test
	void getInsuranceByIdTest() {
		int id=16;
		Optional<Insurance> opt=insuranceRepository.findById(id);
		Insurance c=opt.get();
		LOGGER.log(Level.INFO, "Insurance :"+ c);
	}
	
	@Disabled
	@Test
	void getAllInsuranceTest() {
		List<Insurance> InsuranceList=insuranceRepository.findAll();
		InsuranceList.forEach((p)->{
			LOGGER.log(Level.INFO, "List of Insurance"+p);
		});
		
}
	
	
	@Test
    void getAllInsuranceByCompanyIdTest() {
        int companyId = 4; // Company ID for which you want to retrieve insurance
        Optional<Company> existingCompany = companyRepository.findById(companyId);
        if (existingCompany.isPresent()) {
            List<Insurance> insuranceList = insuranceRepository.findByCompanyId(companyId);
            if (!insuranceList.isEmpty()) {
                LOGGER.log(Level.INFO, "List of Insurance for Company ID " + companyId);
                insuranceList.forEach((p) -> LOGGER.log(Level.INFO, p.toString()));
            } else {
                LOGGER.log(Level.INFO, "No insurance found for Company ID " + companyId);
            }
        } else {
            LOGGER.log(Level.INFO, "Company ID " + companyId + " is invalid");
        }
    }
	
	@Disabled
	@Test
	void deleteInsuranceByIdMethodTest() {
		int id=1;
		insuranceRepository.deleteById(id);
		Optional<Insurance> opt=insuranceRepository.findById(1);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "Insurance not present..");
		}
		}
	
	@Test
	void UpdateTest() {
		int id=16;
		Optional<Insurance> existingInsurance=insuranceRepository.findById(id);
		if(existingInsurance.isPresent()) {
		Insurance obj=existingInsurance.get();
		LOGGER.log(Level.INFO, "existingInsurance :" +obj);
		
		//update the object
		obj.setCategory(Category.DIGITAL_INDIA);
		
		
		Insurance updatedInsurance=insuranceRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedInsurance :" +updatedInsurance);
		}
		else {
			LOGGER.log(Level.INFO, "Insurance not present..");
		}
	}
}

