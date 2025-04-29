package com.springboot.financialplanning.repository;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.enums.Category;
import com.springboot.financialplanning.model.Insurance;

public class GeneratedQueriesTest {
	
	@Autowired
	InsuranceRepository insuranceRepository;
	
	@Test
	void findByCompanyIdMethodTest() {
		// Check if insuranceRepository is null
		if (insuranceRepository != null) {
			List<Insurance> resultobj = insuranceRepository.findByCompanyId(2);
			resultobj.forEach((p) -> {
				System.out.println(p);
			});
		} else {
			System.out.println("insuranceRepository is null");
		}

	}
	
	@Test
	void findByCategoryMethodTest() {
		// Check if insuranceRepository is null
		if (insuranceRepository != null) {
			List<Insurance> resultobj = insuranceRepository.findByCategory(Category.HEALTH);
			resultobj.forEach((p) -> {
				System.out.println(p);
			});
		} else {
			System.out.println("insuranceRepository is null");
		}

	}
}
