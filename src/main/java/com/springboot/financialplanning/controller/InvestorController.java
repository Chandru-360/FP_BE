package com.springboot.financialplanning.controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Role;
import com.springboot.financialplanning.dto.InvestorDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.model.Withdraw;
import com.springboot.financialplanning.service.InvestorService;
import com.springboot.financialplanning.service.UserService;
import com.springboot.financialplanning.service.WithdrawService;


@RestController
@RequestMapping("/investor")
public class InvestorController {

	@Autowired
	private InvestorService investorService;

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/* Insert Investor */
	@PostMapping("/add")
	public Investor insertInvestor(@RequestBody Investor investor) {
		/*save user info in db*/
		User user=investor.getUser();
		String passwordPlain=user.getPassword();
		String encodedPassword=passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole(Role.INVESTOR);
		/*insert user to db*/
		user=userService.insert(user);
		/*Attach user to investor*/
		investor.setUser(user);
		/*save the investor*/
		return investorService.insert(investor);
	}
	
	/* Get Investor By Id */
	@GetMapping("/getone/{iid}")
	public ResponseEntity<?> getOne(@PathVariable("iid")int iid) {
		
		try {
			Investor investor = investorService.getByInvestorId(iid);
			return ResponseEntity.ok().body(investor);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}  
	
	
	/* Get All Investor */
	@GetMapping("/getall") /// investor/getall?page=0&size=10
	public List<Investor> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return investorService.getAll(pageable);
	}
	
	/* Delete Investor By Id */
	@DeleteMapping("/delete/{iid}")
	public ResponseEntity<?> deleteInvestor(@PathVariable("iid") int iid) {
		
		try {
			//validate id
			Investor investor = investorService.getByInvestorId(iid);
			//delete
			investorService.deleteInvestor(investor);
			return ResponseEntity.ok().body("Investor deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/* Update Investor By Id */
	@PutMapping("/update/{iid}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateInvestor(@PathVariable("iid") int iid,
							@RequestBody InvestorDto newInvestor) {
		try {
			//validate id
			Investor oldInvestor = investorService.getByInvestorId(iid);
			if(newInvestor.getCity() != null)
				oldInvestor.setCity(newInvestor.getCity());
			if(newInvestor.getName() != null) 
				oldInvestor.setName(newInvestor.getName()); 
			if(newInvestor.getEmail() != null) 
				oldInvestor.setEmail(newInvestor.getEmail());
			if(newInvestor.getDob() != null) 
				oldInvestor.setDob(newInvestor.getDob());
			if(newInvestor.getGender() != null) 
				oldInvestor.setGender(newInvestor.getGender());
			if(newInvestor.getContactNumber() != null) 
				oldInvestor.setContactNumber(newInvestor.getContactNumber());
			if(newInvestor.getPancardNumber() != null) 
				oldInvestor.setPancardNumber(newInvestor.getPancardNumber());
			
			 
			oldInvestor = investorService.insert(oldInvestor); 
			return ResponseEntity.ok().body(oldInvestor);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
}
