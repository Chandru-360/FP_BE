package com.springboot.financialplanning.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.financialplanning.model.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	
	User findByUsername(String username);

}
