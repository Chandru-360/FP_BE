package com.springboot.financialplanning.controller;

import org.springframework.web.bind.annotation.*;

import com.springboot.financialplanning.model.LumpSumResult;

@RestController
@RequestMapping("/lumpsum")
public class LumpSumController {

	//http://localhost:8080/lumpsum/calculate?principal=10000&interestRate=8&tenureYears=5
	@GetMapping("/calculate")
    public LumpSumResult calculateLumpSum(
            @RequestParam double principal,
            @RequestParam double interestRate,
            @RequestParam int tenureYears) {
        // Perform lump sum calculation here
        double maturityAmount = principal * Math.pow((1 + interestRate / 100), tenureYears);
        return new LumpSumResult(maturityAmount);
    }
}