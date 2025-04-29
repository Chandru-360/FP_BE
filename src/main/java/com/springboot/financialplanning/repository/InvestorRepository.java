package com.springboot.financialplanning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.Investor;



public interface InvestorRepository extends JpaRepository<Investor, Integer> {

}
