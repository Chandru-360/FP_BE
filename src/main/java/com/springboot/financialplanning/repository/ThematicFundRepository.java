package com.springboot.financialplanning.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.enums.Category;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.ThematicFund;

public interface ThematicFundRepository extends JpaRepository<ThematicFund, Integer>{

	List<ThematicFund> findByCategory(Category category);

	@Query("select t from ThematicFund t where t.company.fundtype=?1")
	List<ThematicFund> findByFundType(String fundType);

	@Query("select t from ThematicFund t where t.company.name=?1")
	List<ThematicFund> findByCompany(String Name);

	List<MutualFund> findAllById(int cid);

}
