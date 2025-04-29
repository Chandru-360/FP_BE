package com.springboot.financialplanning.controller;

import org.springframework.web.bind.annotation.*;

import com.springboot.financialplanning.dto.SIPResult;

@RestController
@RequestMapping("/sip")
public class SIPController {

	//http://localhost:8181/sip/calculate?principal=10000&interestRate=8&tenureYears=5
	@GetMapping("/calculate")
    public SIPResult calculateSIP(
            @RequestParam double principal,
            @RequestParam double interestRate,
            @RequestParam int tenureYears) {
        // Perform SIP calculation here
        double monthlyInterestRate = interestRate / 12 / 100;
        int totalMonths = tenureYears * 12;
        double amount = principal * ((Math.pow(1 + monthlyInterestRate, totalMonths) - 1) /
                (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, totalMonths)));
        return new SIPResult(amount);
    }
}