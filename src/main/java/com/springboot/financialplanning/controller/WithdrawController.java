package com.springboot.financialplanning.controller;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.financialplanning.model.Withdraw;
import com.springboot.financialplanning.service.WithdrawService;

@RestController
public class WithdrawController {
	
	@Autowired
	private WithdrawService withdrawService;
	

	@GetMapping("/investorwithdrawlist/{investorId}")
	public ResponseEntity<List<Withdraw>> getWithdrawalsByInvestorId(@PathVariable("investorId") int investorId) {
	    try {
	        List<Withdraw> withdrawals = withdrawService.getWithdrawalsByInvestorId(investorId);
	        return ResponseEntity.ok().body(withdrawals);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(Collections.emptyList());
	    }
	}

	@GetMapping("/investorwithdrawlist/{investorId}/{dateOfInvestment}")
	public ResponseEntity<List<Withdraw>> getWithdrawalsByInvestorIdAndDateOfInvestment(
	    @PathVariable("investorId") int investorId,
	    @PathVariable("dateOfInvestment")@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateOfInvestment) {
	    try {
	        List<Withdraw> withdrawals = withdrawService.getWithdrawalsByInvestorIdAndDateOfInvestment(investorId, dateOfInvestment);
	        return ResponseEntity.ok().body(withdrawals);
	    } catch (Exception e) {
	        return ResponseEntity.badRequest().body(Collections.emptyList());
	    }
	}


}
