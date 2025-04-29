package com.springboot.financialplanning;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.financialplanning.model.Executive;
import com.springboot.financialplanning.repository.ExecutiveRepository;

@SpringBootTest
public class ExecutiveTest {
	
	@Autowired
	ExecutiveRepository executiveRepository;
	public final static Logger LOGGER=Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Disabled
	@Test
	void insertExecutive() {
		
		Executive c=new Executive(1,"dhoni","dhoni@gmail.com","9484557777","chennai",null, "1981-07-07",null, null);
		
		Executive savedExecutive=executiveRepository.save(c);
		LOGGER.log(Level.INFO, "savedExecutive :" +savedExecutive);
		
	}
	
	@Test
	void getAllExecutives() {
		List<Executive> ExecutiveList=executiveRepository.findAll();
		ExecutiveList.forEach((p)->{
			LOGGER.log(Level.INFO, "List of Executives"+p);
		});
}
	
	@Test
	void getExecutiveById() {
		int id=15;
		Optional<Executive> opt=executiveRepository.findById(id);
		Executive c=opt.get();
		LOGGER.log(Level.INFO, "Executive :"+ c);
}
	
	@Disabled
	@Test
	void deleteExecutive() {
		int id=15;
		executiveRepository.deleteById(id);
		Optional<Executive> opt=executiveRepository.findById(1);
		if(!opt.isPresent())
		{
			LOGGER.log(Level.INFO, "Executive not present..");
		}
		}
	
	@Test
	void updateExecutive() {
		int id=15;
		Optional<Executive> existingExecutive=executiveRepository.findById(id);
		if(existingExecutive.isPresent()) {
		Executive obj=existingExecutive.get();
		LOGGER.log(Level.INFO, "existingExecutive :" +obj);
		
		//update the object
		obj.setCity("chennai");
		obj.setContact("564645454");
		
		Executive updatedExecutive=executiveRepository.save(obj);
		LOGGER.log(Level.INFO, "updatedExecutive :" +updatedExecutive);
		}
		else {
			LOGGER.log(Level.INFO, "Executive not present..");
		}
	}
	
	

}
