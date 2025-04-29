package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enums.Category;
import com.springboot.financialplanning.model.MutualFund;



public interface MutualFundRepository extends JpaRepository<MutualFund, Integer> {

	List<MutualFund> findByCategory(Category category);

	@Query("select m from MutualFund m where m.Company.fundtype=?1")
	List<MutualFund> findByFundType(String fundType);

	@Query("select m from MutualFund m where m.Company.name=?1")
	List<MutualFund> findByCompany(String Name);

	List<MutualFund> findAllById(int id);


}
