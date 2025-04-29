package com.springboot.financialplanning.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Executive;
import com.springboot.financialplanning.repository.ExecutiveRepository;


@Service
public class ExecutiveService {
	@Autowired
	private ExecutiveRepository executiveRepository;
	
	public Executive insert(Executive executive) {
		return executiveRepository.save(executive);
	}
	public Executive saveExecutive(Executive executive) {
		return executiveRepository.save(executive);
	}
	
	public List<Executive> getAllExecutives() {
		List<Executive> list = executiveRepository.findAll();
		return list;
		 
	}
	public void deleteExecutive(int eid) {
		executiveRepository.deleteById(eid);
	}
	

	public Executive getExecutiveById(int eid) throws InvalidIdException {
		Optional<Executive> optional=executiveRepository.findById(eid);
		if(!optional.isPresent())
			throw new InvalidIdException("Executive id  Invalid");
		return optional.get();
	}

}