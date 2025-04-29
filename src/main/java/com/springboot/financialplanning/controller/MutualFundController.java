package com.springboot.financialplanning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Category;
import com.springboot.financialplanning.dto.MutualFundDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.service.CompanyService;
import com.springboot.financialplanning.service.MutualFundService;



@RestController
@RequestMapping("/mutualfund")
public class MutualFundController {
	
	@Autowired
	private MutualFundService mutualFundService;
	
	@Autowired
	private CompanyService companyService;
	
	@PostMapping("/add/{cid}")
	public ResponseEntity<?> insertMutualFund(@PathVariable ("cid") int cid,@RequestBody MutualFund mutualFund) {
			try {
				
				/* Fetch company object from db using cid */
				Company company = companyService.getCompanyById(cid);
				
				/* Attach mutual fund to company */
				mutualFund.setCompany(company);
				
				/* Save the mutual fund in the DB */
				MutualFund savedMutualFund = mutualFundService.insert(mutualFund);
				return ResponseEntity.ok().body(savedMutualFund);
			}
			catch(InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	@GetMapping("/all")
	public List<MutualFund>getAllMutualFunds(){
		List<MutualFund> list = mutualFundService.getAllMutualFunds();
		return list;
	}
	
	@GetMapping("/all/{cid}")
	public List<MutualFund>getAllMutualFundsByCompanyId(@PathVariable("cid")int cid){
		List<MutualFund> list = mutualFundService.getAllMutualFundsByCompanyId(cid);
		return list;
	}
	
	
	@GetMapping("/one/{cid}")
	public ResponseEntity<?> getMutualFundById(@PathVariable("cid") int cid) {
		try {
			MutualFund mutualFund = mutualFundService.getMutualFundById(cid);
			return ResponseEntity.ok().body(mutualFund);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@DeleteMapping("/delete/{cid}")
	public ResponseEntity<?> deleteMutualFund(@PathVariable("cid") int cid) {
		try {
			MutualFund mutualFund = mutualFundService.getMutualFundById(cid);
			mutualFundService.deleteMutualFund(mutualFund.getId());
			return ResponseEntity.ok().body("mutualFund Record deleted");
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	
	@PutMapping("/update/{cid}")
	public ResponseEntity<?> updateMutualFund(@PathVariable("cid") int cid, 
			@RequestBody MutualFundDto newmutualFund) {
		try {
			MutualFund mutualFund = mutualFundService.getMutualFundById(cid);
			if(newmutualFund.getFundName()!= null)
				mutualFund.setFundName(newmutualFund.getFundName());
			if(newmutualFund.getCategory() != null)
				mutualFund.setCategory(newmutualFund.getCategory());
			if(newmutualFund.getFundSize() != 0)
				mutualFund.setFundSize(newmutualFund.getFundSize());
			if(newmutualFund.getReturnFactor() != null)
				mutualFund.setReturnFactor(newmutualFund.getReturnFactor());
			if(newmutualFund.getExpectedReturns() != null)
				mutualFund.setExpectedReturns(newmutualFund.getExpectedReturns());
			if(newmutualFund.getRiskFactor() != null)
				mutualFund.setRiskFactor(newmutualFund.getRiskFactor());
			if(newmutualFund.getLockingPeriod() != null)
				mutualFund.setLockingPeriod(newmutualFund.getLockingPeriod());
			if(newmutualFund.getMinInvenstmentAmount() != 0)
				mutualFund.setMinInvenstmentAmount(newmutualFund.getMinInvenstmentAmount());
			if(newmutualFund.getNavPrice() != 0)
				mutualFund.setNavPrice(newmutualFund.getNavPrice());
			
			mutualFund = mutualFundService.insert(mutualFund);
			return ResponseEntity.ok().body(mutualFund);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	 @GetMapping("/category/{category}")
	    public ResponseEntity<?> getMutualFundsByCategory(@PathVariable("category") Category category) {
	        List<MutualFund> mutualFunds = mutualFundService.getMutualFundsByCategory(category);
			return ResponseEntity.ok().body(mutualFunds);
	    }
}

