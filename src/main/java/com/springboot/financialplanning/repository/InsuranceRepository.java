package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enums.Category;
import com.springboot.financialplanning.model.Insurance;



public interface InsuranceRepository extends JpaRepository<Insurance, Integer>{

	List<Insurance> findByCompanyId(int cid);

	List<Insurance> findByCategory(Category category);

	@Query("select i from Insurance i where i.company.name=?1")
	List<Insurance> findByCompany(String name);

	@Query("select i from Insurance i where i.company.fundtype=?1")
	List<Insurance> findByFundType(String fundType);


   
}