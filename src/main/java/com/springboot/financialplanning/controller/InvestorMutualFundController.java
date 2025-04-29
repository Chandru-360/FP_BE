
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

import com.springboot.financialplanning.dto.InvestorMutualFundDto;
import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Investor;
import com.springboot.financialplanning.model.InvestorInsurance;
import com.springboot.financialplanning.model.InvestorMutualFund;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.Withdraw;
import com.springboot.financialplanning.service.InvestorMutualFundService;
import com.springboot.financialplanning.service.InvestorService;
import com.springboot.financialplanning.service.MutualFundService;
import com.springboot.financialplanning.service.WithdrawService;



@RestController
public class InvestorMutualFundController {
	@Autowired
	private InvestorService investorService;

	@Autowired
	private MutualFundService mutualFundService;

	@Autowired
	private InvestorMutualFundService investorMutualFundService;
	
	@Autowired
	private WithdrawService withdrawService;
	
	
	/* Insert InvestorMutualFund Details By InvestorId and MutualFundId */
	@PostMapping("/investormutualfund/add/{iid}/{mfid}")
	public ResponseEntity<?> mutualfund(@PathVariable("iid") int iid, @PathVariable("mfid") int mfid,
			@RequestBody InvestorMutualFund investorMutualFund) {
		try {
			/*fetch investor by id*/
			Investor investor = investorService.getByInvestorId(iid);

			/*fetch mutualfund by id*/
			MutualFund mutualFund = mutualFundService.getMutualFundById(mfid);
			
			/*attach investor to investormutualfund*/
			investorMutualFund.setInvestor(investor);
			
			/*attach mutualfund to investormutualfund*/
			investorMutualFund.setMutualFund(mutualFund);
			double navPrice = mutualFund.getNavPrice();
			
			 switch (investorMutualFund.getInvestmentType()) {
	            case SIP:
	                investorMutualFund.setSipStartDate(investorMutualFund.getSipStartDate());
	                investorMutualFund.setSipAmount(investorMutualFund.getSipAmount());
	                
	                investorMutualFund.setNavUnits(investorMutualFund.getSipAmount() / navPrice);
	                // Save the investorMutualFund for SIP
	                break;
	            case ONE_TIME:
	                investorMutualFund.setInvestmentDate(investorMutualFund.getInvestmentDate());
	                investorMutualFund.setOnetimeAmount(investorMutualFund.getOnetimeAmount());
	                
	                investorMutualFund.setNavUnits(investorMutualFund.getOnetimeAmount() / navPrice);
	                // Save the investorMutualFund for one-time investment
	                break;
	            default:
	                return ResponseEntity.badRequest().body("Invalid investment type selected.");
	        }
			 
			/*save the investorMutualFund in db */
			investorMutualFund = investorMutualFundService.insert(investorMutualFund);
			return ResponseEntity.ok().body(investorMutualFund);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	
	@PostMapping("/withdraw/{iind}")
	public ResponseEntity<?> withdrawAmount(@PathVariable("iind") int iind,@RequestBody Withdraw withdraw) {
		
		try {
			InvestorMutualFund investorMutualFund=investorMutualFundService.getByInvestorMutualFundId(iind);
			 double navPrice = investorMutualFund.getMutualFund().getNavPrice();
			 double withdrawalAmount = investorMutualFund.getNavUnits() * navPrice;
			 withdraw.setWithdrawalAmount(withdrawalAmount);
			 withdraw.setWithdrawalDate(LocalDate.now());
			 withdraw.setInvestorMutualFund(investorMutualFund);
			 withdrawService.save(withdraw);
			 
			 // Process the payment based on the selected payment method
	            if (withdraw.getPaymentMethod().equals("bankTransfer")) {
	                // Process withdrawal via bank transfer
	                // Example: bankTransferService.processWithdrawal(withdrawAmount);
	            } else if (withdraw.getPaymentMethod().equals("Upi")) {
	                // Process withdrawal via check
	                // Example: checkService.issueCheck(withdrawAmount);
	            } else {
	                return ResponseEntity.badRequest().body("Invalid payment method selected.");
	            }
	            
	            return ResponseEntity.ok().body("Withdrawal amount: " + withdrawalAmount + ", Payment method: " + withdraw.getPaymentMethod()+ withdraw);
			 
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	
	
	/* Get All MutualFundwithdraw Done by Investor*/
	@GetMapping("/mutualfundwithdraw/all")
	public List<Withdraw> getAllWithdrawalMutualFund(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return withdrawService.getAllWithdraw(pageable);
	}
	
	
	
	
	/* Get All MutualFundDetails Done by Investor*/
	@GetMapping("/investormutualfunddetails/all")
	public List<InvestorMutualFund> getAllMutualFund(
			@RequestParam(value="page",required = false,defaultValue = "0") Integer page,
			@RequestParam(value="size",required = false,defaultValue = "1000000") Integer size) {
		
		Pageable pageable =  PageRequest.of(page, size);
		return investorMutualFundService.getAllMutualFund(pageable);
	}
	
	/* Get All InvestorMutualFund Details By Id */
	@GetMapping("/investormutualfunddetails/{iid}/{mfid}")
	public ResponseEntity<?> getMutualFundDetails(@PathVariable("iid") int iid,
			@PathVariable("mfid")int mfid) {
		
		try {
			/* Fetch Investor object using given iid */
			Investor investor = investorService.getByInvestorId(iid);
			/* Fetch MutualFund object using given mfid */
			MutualFund mutualFund=mutualFundService.getMutualFundById(mfid);
			List<InvestorMutualFund> list= investorMutualFundService.getMutualFundDetailsByIds(iid,mfid);
			return ResponseEntity.ok().body(list);
		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());

		}
	}
	

	/* Delete InvestorMutualFund By Id */
	@DeleteMapping("/InvestorMutualFund/delete/{mdid}")
	public ResponseEntity<?> deleteMutualFundDetails(@PathVariable("mdid") int mdid) {
		
		try {
			//validate id
			InvestorMutualFund investorMutualFund=investorMutualFundService.getByInvestorMutualFundId(mdid);
			//delete
			investorMutualFundService.deleteMutualFundDetails(investorMutualFund);
			return ResponseEntity.ok().body("MutualFundDetails deleted successfully");

		} catch (InvalidIdException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
//	/* Update InvestorMutualFund By Id */
//	@PutMapping("/updatemutualfund/{mdid}")  //:update: which record to update?   give me new value for update
//	public ResponseEntity<?> updateInvestorMutualFund(@PathVariable("mdid") int mdid,
//							@RequestBody InvestorMutualFundDto newInvestorMutualFund) {
//		try {
//			//validate id
//			InvestorMutualFund oldInvestorMutualFund = investorMutualFundService.getByInvestorMutualFundId(mdid);
//			if(newInvestorMutualFund.getAmountInvested() != null)
//				oldInvestorMutualFund.setAmountInvested (newInvestorMutualFund.getAmountInvested());
//
//			 
//			oldInvestorMutualFund = investorMutualFundService.insert(oldInvestorMutualFund); 
//			return ResponseEntity.ok().body(oldInvestorMutualFund);
//
//	} catch (InvalidIdException e) {
//			return ResponseEntity.badRequest().body(e.getMessage());
//		}
//	}
//I
}

