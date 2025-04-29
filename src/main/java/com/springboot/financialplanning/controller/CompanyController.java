package com.springboot.financialplanning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Role;
import com.springboot.financialplanning.dto.CompanyDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.User;
import com.springboot.financialplanning.service.CompanyService;
import com.springboot.financialplanning.service.UserService;

@RestController
@RequestMapping("/company")
public class CompanyController {
	@Autowired
	private CompanyService companyService;

	@Autowired
	private UserService userService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("/add")
	public Company insertCompany(@RequestBody Company company) {

		/* save user info in database */
		User user = company.getUser();
		String passwordPlain = user.getPassword();
		String encodedPassword = passwordEncoder.encode(passwordPlain);
		user.setPassword(encodedPassword);

		user.setRole(Role.COMPANY);
		user = userService.insert(user);

		company.setUser(user);

		return companyService.insert(company);
	}

	@GetMapping("/all")
	public List<Company> getAllCompanies() {
		List<Company> list = companyService.getAllCompanies();
		return list;
	}

	@GetMapping("/one/{cid}")
	public ResponseEntity<?> getCompanyById(@PathVariable("cid") int cid) {
		try {
			Company company = companyService.getCompanyById(cid);
			return ResponseEntity.ok().body(company);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<?> deleteCompany(@PathVariable("cid") int cid) {
		try {
			Company company = companyService.getCompanyById(cid);
			companyService.deleteCompany(company.getId());
			return ResponseEntity.ok().body("Company Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/update/{cid}")
	public ResponseEntity<?> updateCompany(@PathVariable("cid") int cid, 
	        @RequestBody CompanyDto newCompany) {
	    try {
	        Company company = companyService.getCompanyById(cid);
	        if (company != null) {
	            if (newCompany.getName() != null) {
	                company.setName(newCompany.getName());
	            }
	            if (newCompany.getEmail() != null) {
	                company.setEmail(newCompany.getEmail());
	            }
	            if (newCompany.getFundtype() != null) {
	                company.setFundtype(newCompany.getFundtype());
	            }

	            User user = company.getUser(); // Access the User object within Company
	            
	            if (user != null) {
	                if (newCompany.getUsername() != null) {
	                    user.setUsername(newCompany.getUsername());
	                }
	                if (newCompany.getPassword() != null) {
	                    String encodedPassword = passwordEncoder.encode(newCompany.getPassword());
	                    user.setPassword(encodedPassword);
	                }
	                
	                // Update the company in the database
	                company = companyService.insert(company);
	                return ResponseEntity.ok().body(company);
	            }
	        }
	    }catch (InvalidIdException e) {
	              return ResponseEntity.badRequest().body(e.getMessage());
	    }
		return ResponseEntity.badRequest().body("Field is empty");
		
	        }
	}
	
