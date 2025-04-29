package com.springboot.financialplanning.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.SalesVp;
import com.springboot.financialplanning.repository.SalesVpRepository;



@Service
public class SalesVpService {

	@Autowired
	private SalesVpRepository salesVpRepository;
	
	public SalesVp insert(SalesVp salesVp) {
		  return salesVpRepository.save(salesVp);
		}

	public SalesVp getBysalesVpId(int sid) throws InvalidIdException {
		Optional<SalesVp> optional=salesVpRepository.findById(sid);
		if(!optional.isPresent())
			throw new InvalidIdException("SalesVp id Invalid");
		return optional.get();
	}
}

