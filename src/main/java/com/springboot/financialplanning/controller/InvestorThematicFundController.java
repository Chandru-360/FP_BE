package com.springboot.financialplanning.controller;

import java.time.LocalDate;
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

import com.springboot.financialplanning.dto.InvestorThematicFundDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.InvestorMutualFund;
import com.springboot.financialplanning.model.InvestorThematicFund;
import com.springboot.financialplanning.model.ThematicFund;
import com.springboot.financialplanning.model.Withdraw;
import com.springboot.financialplanning.service.InvestorService;
import com.springboot.financialplanning.service.InvestorThematicFundService;
import com.springboot.financialplanning.service.ThematicFundService;
import com.springboot.financialplanning.service.WithdrawService;

@RestController
public class InvestorThematicFundController {
	@Autowired
	private InvestorService investorService;
	
	@Autowired
	private ThematicFundService thematicFundService;
	
	@Autowired
	private InvestorThematicFundService investorThematicFundService;
	
	@Autowired
	private WithdrawService withdrawService;
	
	
    /* Insert InvestorThematicFund Details By InvestorId and ThematicFundId */
	@PostMapping("/investorthematicfund/add/{iid}/{tfid}")
	public ResponseEntity<?> thematicfund(@PathVariable("iid") int iid, @PathVariable("tfid") int tfid,
	        @RequestBody InvestorThematicFund investorThematicFund) {
	    try {
	        Investor investor = investorService.getByInvestorId(iid);
	        ThematicFund thematicFund = thematicFundService.getByid(tfid);
	        
	        investorThematicFund.setInvestor(investor);
	       
	        investorThematicFund.setThematicFund(thematicFund);
	        
	        double navPrice = thematicFund.getNavPrice();

	        switch (investorThematicFund.getInvestmentType()) {
	            case SIP:
	                investorThematicFund.setSipStartDate(investorThematicFund.getSipStartDate());
	                investorThematicFund.setSipAmount(investorThematicFund.getSipAmount());
	                
	              
	                investorThematicFund.setNavUnits(investorThematicFund.getSipAmount() / navPrice);
	                break;
	            case ONE_TIME:
	                investorThematicFund.setInvestmentDate(investorThematicFund.getInvestmentDate());
	                investorThematicFund.setOnetimeAmount(investorThematicFund.getOnetimeAmount());
	                
	                investorThematicFund.setNavUnits(investorThematicFund.getOnetimeAmount() / navPrice);
	                break;
	            default:
	                return ResponseEntity.badRequest().body("Invalid investment type selected.");
	        }

	        investorThematicFund = investorThematicFundService.insert(investorThematicFund);
	        return ResponseEntity.ok().body(investorThematicFund);
	    } catch (InvalidIdException e) {
	        return ResponseEntity.badRequest().body(e.getMessage());
	    }
	}

	
	@PostMapping("/InvestorThematicFundwithdraw/{tid}")
	public ResponseEntity<?> withdrawAmount(@PathVariable("tid") int tid,@RequestBody Withdraw withdraw) {
		
		try {
			InvestorThematicFund investorThematicFund=investorThematicFundService.getByInvestorThematicFundId(tid);
			 double navPrice = investorThematicFund.getThematicFund().getNavPrice();
			 double withdrawalAmount = investorThematicFund.getNavUnits() * navPrice;
			 withdraw.setWithdrawalAmount(withdrawalAmount);
			 withdraw.setWithdrawalDate(LocalDate.now());
			 withdraw.setInvestorThematicFund(investorThematicFund);
			 withdrawService.save(withdraw);
			 
			 
	            if (withdraw.getPaymentMethod().equals("bankTransfer")) {
	                
	            } else if (withdraw.getPaymentMethod().equals("Upi")) {
	                
	            } else {
	                return ResponseEntity.badRequest().body("Invalid payment method selected.");
	            }
	            
	            return ResponseEntity.ok().body("Withdrawal amount: " + withdrawalAmount + ", Payment method: " + withdraw.getPaymentMethod()+ withdraw);
			 
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	
	/* Get All ThematicFundwithdraw Done by Investor*/
	@GetMapping("/thematicfundwithdraw/all")
	public List<Withdraw> getAllWithdrawalThematicFund(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return withdrawService.getAllWithdrawinThematic(pageable);
	}

	
	
	/* Get All ThematicFundDetails Done by Investor*/
	@GetMapping("/investorthematicfunddetails/all")
	public List<InvestorThematicFund> getAllThematicFund(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return investorThematicFundService.getAllThematicFund(pageable);
	}
	
	/* Get All InvestorThematicFund Details By Id */
	@GetMapping("/investorthematicfunddetails/{iid}/{tfid}")
	public ResponseEntity<?> getThematicFundDetails(@PathVariable("iid") int iid,
			@PathVariable("tfid")int tfid) {
		
		try {
			/* Fetch Investor object using given iid */
			Investor investor = investorService.getByInvestorId(iid);
			/* Fetch ThematicFund object using given tid */
			ThematicFund thematicFund=thematicFundService.getByid(tfid);
			List<InvestorThematicFund> list= investorThematicFundService.getThematicFundDetailsByIds(iid,tfid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	
	/* Delete InvestorThematicFund By Id */
	@DeleteMapping("/deletethematic/{tdid}")
	public ResponseEntity<?> deleteThematicFundDetails(@PathVariable("tdid") int tdid) {
		
		try {
			//validate id
			InvestorThematicFund investorThematicFund=investorThematicFundService.getByInvestorThematicFundId(tdid);
			//delete
			investorThematicFundService.deleteThematicFundDetails(investorThematicFund);
			return ResponseEntity.ok().body("ThematicFundDetails deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
//	/* Update InvestorThematicFund By Id */
//	@PutMapping("/updatethematic/{tdid}")  //:update: which record to update?   give me new value for update
//	public ResponseEntity<?> updateInvestorThematicFund(@PathVariable("tdid") int tdid,
//							@RequestBody InvestorThematicFundDto newInvestorThematicFund) {
//		try {
//			//validate id
//			InvestorThematicFund oldInvestorThematicFund = investorThematicFundService.getByInvestorThematicFundId(tdid);
//			if(newInvestorThematicFund.getAmountInvested() != null)
//				oldInvestorThematicFund.setAmountInvested (newInvestorThematicFund.getAmountInvested());
//
//			 
//			oldInvestorThematicFund = investorThematicFundService.insert(oldInvestorThematicFund); 
//			return ResponseEntity.ok().body(oldInvestorThematicFund);
//
//		} catch (InvalidIdException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
}
