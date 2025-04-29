package com.springboot.financialplanning.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.service.UserService;


@RestController
public class AuthController {
	@Autowired
	private UserService userService;

	@GetMapping("/user/login")
	public User login (Principal principal) {
		String username=principal.getName();
		User user=(User)userService.loadUserByUsername(username);
		return user;
		}
}
