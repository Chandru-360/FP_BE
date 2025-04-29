package com.springboot.financialplanning.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.enums.Category;
import com.springboot.financialplanning.dto.SearchResultDto;
import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.ThematicFund;
import com.springboot.financialplanning.service.InsuranceService;
import com.springboot.financialplanning.service.MutualFundService;
import com.springboot.financialplanning.service.ThematicFundService;
@RestController
@RequestMapping("/search")
public class SearchBarController {
	@Autowired
	private MutualFundService mutualFundService;
	
	@Autowired
	private ThematicFundService thematicFundService;
	
	@Autowired
	private InsuranceService insuranceService;
	
	@GetMapping("/ByCompanyName")
    public SearchResultDto searchByCompany(@RequestParam String Name) {
        List<MutualFund> mutualFunds = mutualFundService.findByCompany(Name);
        List<ThematicFund> thematicFunds = thematicFundService.findByCompany(Name);
        List<Insurance> insurances = insuranceService.findByCompany(Name);

        return new SearchResultDto(mutualFunds, thematicFunds, insurances);
    }
	
	@GetMapping("/ByFundType")
    public SearchResultDto searchByFundType(@RequestParam String fundType) {
        List<MutualFund> mutualFunds = mutualFundService.findByFundType(fundType);
        List<ThematicFund> thematicFunds = thematicFundService.findByFundType(fundType);
        List<Insurance> insurances = insuranceService.findByFundType(fundType);

        return new SearchResultDto(mutualFunds, thematicFunds, insurances);
    }
	
	
	@GetMapping("/ByCategory")
    public SearchResultDto searchByCategory(@RequestParam Category category) {
        List<MutualFund> mutualFunds = mutualFundService.findByCategory(category);
        List<ThematicFund> thematicFunds = thematicFundService.findByCategory(category);
        List<Insurance> insurances = insuranceService.findByCategory(category);

        return new SearchResultDto(mutualFunds, thematicFunds, insurances);
    }

}
