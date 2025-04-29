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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Category;
import com.springboot.financialplanning.dto.InsuranceDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Company;
import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.service.CompanyService;
import com.springboot.financialplanning.service.InsuranceService;



@RestController
@RequestMapping("/insurance")
public class InsuranceController {

	@Autowired
	private InsuranceService insuranceService;
	
	@Autowired
	private CompanyService companyService;
	
	
	/*Insert Insurance by Company id*/
	@PostMapping("/add/{cid}")
	public ResponseEntity<?> insertInsurance(@PathVariable ("cid") int cid,@RequestBody Insurance insurance) {
			try {
				/*fetch company from db by id*/
				Company company = companyService.getCompanyById(cid);
				/*attach company to insurance*/
				insurance.setCompany(company);
				/*save the savedInsurance in db*/
				Insurance savedInsurance = insuranceService.insert(insurance);
				return ResponseEntity.ok().body(savedInsurance);
			}
			catch(InvalidIdException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}
	
	/*Get Insurance by Insurance id*/
	@GetMapping("/getone/{inid}")
	public ResponseEntity<?> getOne(@PathVariable("inid")int inid) {
		
		try {
			Insurance insurance = insuranceService.getByid(inid);
			return ResponseEntity.ok().body(insurance);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}  
	
	/*Get All Insurance*/
	@GetMapping("/getall") /// Insurance/getall?page=0&size=10
	public List<Insurance> getAll(@RequestParam(value="page",required = false, defaultValue = "0") Integer page,
							   @RequestParam(value="size", required = false, defaultValue = "10000000") Integer size) { // v1 v2 v3 v4 v5
																										// : size & page

		Pageable pageable = PageRequest.of(page, size); // null null
		return insuranceService.getAll(pageable);
	}
	
	
	/*Get All Insurance by Company id*/ 
	@GetMapping("/all/{cid}")
	public ResponseEntity<?> getInsuranceByCompany(@PathVariable("cid") int cid) {
		/* Fetch company object using given cid */
		try {
			Company company = companyService.getCompanyById(cid);
			List<Insurance> list= insuranceService.getInsuranceByCompany(cid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	
	/*Delete Insurance by Id*/
	@DeleteMapping("/delete/{inid}")
	public ResponseEntity<?> deleteInsurance(@PathVariable("inid") int inid) {
		
		try {
			//validate id
			Insurance insurance = insuranceService.getByid(inid);
			//delete
			insuranceService.deleteInsurance(insurance);
			return ResponseEntity.ok().body("Insurance deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*Delete Insurance by InsuranceId and companyId */
	@DeleteMapping("/delete/{inid}/{cid}")
	public ResponseEntity<?> deleteInsurance(@PathVariable("inid") int inid,
			@PathVariable("cid")int cid) {
		
		try {
			//validate id
			Company company =companyService.getCompanyById(cid);
			Insurance insurance = insuranceService.getByid(inid);
			//delete
			insuranceService.deleteInsurance(insurance);
			return ResponseEntity.ok().body("Insurance deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	/*Update Insurance By Id */
	@PutMapping("/update/{inid}")  //:update: which record to update?   give me new value for update
	public ResponseEntity<?> updateInsurance(@PathVariable("inid") int inid,
							@RequestBody InsuranceDto newInsurance) {
		try {
			//validate id
			Insurance oldinsurance = insuranceService.getByid(inid);

			if(newInsurance.getPolicyName() != null) 
				oldinsurance.setPolicyName(newInsurance.getPolicyName());
			if(newInsurance.getCategory() != null) 
				oldinsurance.setCategory(newInsurance.getCategory());
			if(newInsurance.getDescription() != null) 
				oldinsurance.setDescription(newInsurance.getDescription());
			if(newInsurance.getClaimSettlement() != null) 
				oldinsurance.setClaimSettlement(newInsurance.getClaimSettlement());
			if(newInsurance.getPremium()!= 0) 
				oldinsurance.setPremium(newInsurance.getPremium());
			if(newInsurance.getPolicyTenure() != 0) 
				oldinsurance.setPolicyTenure(newInsurance.getPolicyTenure());
			if(newInsurance.getAgeCreteria() != null) 
				oldinsurance.setAgeCreteria(newInsurance.getAgeCreteria());
			if(newInsurance.getCoverage() != 0) 
				oldinsurance.setCoverage(newInsurance.getCoverage());
			 
			oldinsurance = insuranceService.insert(oldinsurance); 
			return ResponseEntity.ok().body(oldinsurance);

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	/*Update Insurance By InsuranceId and CompanyId */
//	@PutMapping("/update/{inid}/{cid}")  //:update: which record to update?   give me new value for update
//	public ResponseEntity<?> updateInsurance(@PathVariable("inid") int inid,
//			                                 @PathVariable("cid")int cid,
//							                 @RequestBody InsuranceDto newInsurance) {
//		try {
//			//validate id
//			Company company=companyService.getCompanyById(cid);
//			Insurance oldinsurance = insuranceService.getByid(inid);
//
//			if(newInsurance.getName() != null) 
//				oldinsurance.setName(newInsurance.getName());
//			if(newInsurance.getCategory() != null) 
//				oldinsurance.setCategory(newInsurance.getCategory());
//			if(newInsurance.getStartdate() != null) 
//				oldinsurance.setStartdate(newInsurance.getStartdate());
//			if(newInsurance.getEnddate() != null) 
//				oldinsurance.setEnddate(newInsurance.getEnddate());
//			if(newInsurance.getPremimum()!= 0) 
//				oldinsurance.setPremimum(newInsurance.getPremimum());
//			if(newInsurance.getPolicytenure() != 0) 
//				oldinsurance.setPolicytenure(newInsurance.getPolicytenure());
//			 
//			oldinsurance = insuranceService.insert(oldinsurance); 
//			return ResponseEntity.ok().body(oldinsurance);
//
//		} catch (InvalidIdException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
	@GetMapping("/category/{category}")
    public ResponseEntity<?> getInsurancesFundsByCategory(@PathVariable("category") Category category) {
        List<Insurance> insurances = insuranceService.getInsurancesByCategory(category);
		return ResponseEntity.ok().body(insurances);
    }
	
}
