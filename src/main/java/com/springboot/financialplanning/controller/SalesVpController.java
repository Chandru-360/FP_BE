package com.springboot.financialplanning.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Role;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.SalesVp;
import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.service.CompanyService;
import com.springboot.financialplanning.service.SalesVpService;
import com.springboot.financialplanning.service.UserService;



@RestController
@RequestMapping("/salesvp")
public class SalesVpController {
	

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SalesVpService salesVpService;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/add")
	public SalesVp insertSalesVp(@RequestBody SalesVp salesVp) {
		/*save user info in db*/
		User user=salesVp.getUser();
		String passwordPlain=user.getPassword();
		String encodedPassword=passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);
		
		user.setRole(Role.SALES_VP);
		user=userService.insert(user);
		
		salesVp.setUser(user);
		
		return salesVpService.insert(salesVp);
	}

	@GetMapping("/getone/{sid}")
	public ResponseEntity<?> getOne(@PathVariable("sid")int sid) {
		
		try {
			SalesVp salesVp = salesVpService.getBysalesVpId(sid);
			return ResponseEntity.ok().body(salesVp);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}  
	@PutMapping("/update/{id}")
	public ResponseEntity<?> updateSalesVp(@PathVariable("id") int id, 
			@RequestBody SalesVp newsalesVp) {
		try {
			SalesVp salesVp = salesVpService.getBysalesVpId(id);
			if(newsalesVp.getName() != null)
				salesVp.setName(newsalesVp.getName());
			if(newsalesVp.getEmail() != null)
				salesVp.setEmail(newsalesVp.getEmail());
			if(newsalesVp.getPhoneNumber() != null)
				salesVp.setPhoneNumber(newsalesVp.getPhoneNumber());
			
			salesVp = salesVpService.insert(salesVp);
			return ResponseEntity.ok().body(salesVp);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	@PostMapping("/onboard/{cid}")
//	public ResponseEntity<String> onboardCompany(
//           
//            @PathVariable("companyId") int companyId) {
//        try {
//            companyService.onboardCompany(companyId);
//            return ResponseEntity.ok("Company with ID: " + companyId + " has been onboarded  " );
//        } catch (InvalidIdException e) {
//            return ResponseEntity.badRequest().body(e.getMessage());
//        }
//    }

}
