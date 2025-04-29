package com.springboot.financialplanning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.financialplanning.dto.InvestorInsuranceDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.InvestorInsurance;
import com.springboot.financialplanning.service.InsuranceService;
import com.springboot.financialplanning.service.InvestorInsuranceService;
import com.springboot.financialplanning.service.InvestorService;

@RestController
public class InvestorInsuranceController {
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private InvestorInsuranceService investorInsuranceService;
	
	/* Insert InvestorInsurance Association */
	@PostMapping("/investorinsurance/add/{iid}/{inid}")
	public ResponseEntity<?> insurance(@PathVariable("iid") int iid, @PathVariable("inid") int inid,
			@RequestBody InvestorInsurance investorInsurance) {
		try {
			Investor investor = investorService.getByInvestorId(iid);
			Insurance insurance = insuranceService.getByid(inid);
			investorInsurance.setInvestor(investor);
			investorInsurance.setInsurance(insurance);
			
			switch (investorInsurance.getInvestmentType()) {
            case MONTHLY:
            	investorInsurance.setMonthlyInvestmentDate(investorInsurance.getMonthlyInvestmentDate());
            	investorInsurance.setMonthlyAmount(investorInsurance.getMonthlyAmount());
              
                break;
            case YEARLY:
            	investorInsurance.setInvestmentDate(investorInsurance.getInvestmentDate());
            	investorInsurance.setYearlyAmount(investorInsurance.getYearlyAmount());
             
                break;
            default:
                return ResponseEntity.badRequest().body("Invalid investment type selected.");
        }
			
			
			investorInsurance = investorInsuranceService.insert(investorInsurance);
			return ResponseEntity.ok().body(investorInsurance);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	/* Get All Insurance Done by Investor*/
	@GetMapping("/all")
	public List<InvestorInsurance> getAllInsurance(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return investorInsuranceService.getAllInsurance(pageable);
	}
	
	/* Get All InvestorInsurance Details By Id */
	@GetMapping("/investorinsurancedetails/{iid}/{inid}")
	public ResponseEntity<?> getInsuranceDetails(@PathVariable("iid") int iid,
			@PathVariable("inid")int inid) {
		
		try {
			/* Fetch Investor object using given iid */
			Investor investor = investorService.getByInvestorId(iid);
			/* Fetch Insurance object using given inid */
			Insurance insurance=insuranceService.getByid(inid);
			List<InvestorInsurance> list= investorInsuranceService.getInsuranceDetailsByIds(iid,inid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	/* Delete InvestorInsurance By Id */
	@DeleteMapping("/investorinsurance/delete/{id}")
	public ResponseEntity<?> deleteInsuranceDetails(@PathVariable("id") int id) {
		
		try {
			//validate id
			InvestorInsurance investorInsurance=investorInsuranceService.getByInvestorInsuranceId(id);
			//delete
			investorInsuranceService.deleteInsuranceDetails(investorInsurance);
			return ResponseEntity.ok().body("InsuranceDetails deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/* Update InvestorInsurance By Id */
	@PutMapping("/updateinsurance/{id}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateInvestorInsurance(@PathVariable("id") int id,
							@RequestBody InvestorInsuranceDto newInvestorInsurance) {
		try {
			//validate id
			InvestorInsurance oldInvestorInsurance = investorInsuranceService.getByInvestorInsuranceId(id);
		/*	if(newVendor.getCity() != null)
				oldInvestorInsurance.setCity(newVendor.getCity());
			if(newVendor.getName() != null) 
				oldInvestorInsurance.setName(newVendor.getName()); */
			 
			oldInvestorInsurance = investorInsuranceService.insert(oldInvestorInsurance); 
			return ResponseEntity.ok().body(oldInvestorInsurance);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
