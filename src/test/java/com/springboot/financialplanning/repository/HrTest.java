package com.springboot.financialplanning.repository;

import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.Hr;

@SpringBootTest
public class HrTest {
	
	@Autowired
	HrRepository hrRepository;
	
	public final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Test
	void saveMethodTest() {
		
		Hr hr=new Hr(1,"dravid","dravid@gmail.com","948455456",null);
		
		Hr savedHr=hrRepository.save(hr);
		LOGGER.log(Level.INFO, "savedHr :" +savedHr);
		
	}
	
	@Test
	void getHrByIdTest() {
		int id=10;
		Optional<Hr> opt=hrRepository.findById(id);
		Hr c=opt.get();
		LOGGER.log(Level.INFO, "Hr :"+ c);

}
	
	@Disabled
	@Test
	void deleteHrByIdMethodTest() {
		int id=1;
		hrRepository.deleteById(id);
		Optional<Hr> opt=hrRepository.findById(1);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "Hr not present..");
		}
		}
	
	
	@Test
	void UpdateTest() {
		int id=10;
		Optional<Hr> existingHr=hrRepository.findById(id);
		if(existingHr.isPresent()) {
		Hr obj=existingHr.get();
		LOGGER.log(Level.INFO, "existingHr :" +obj);
		
		//update the object
		obj.setName("Nithin");
		
		//productRepo.save(existingProduct);
		Hr updatedHr=hrRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedHr :" +updatedHr);
		}
		else {
			LOGGER.log(Level.INFO, "Hr not present..");
		}
	}
	
}
