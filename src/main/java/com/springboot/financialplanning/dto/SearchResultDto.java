package com.springboot.financialplanning.dto;

import java.util.List;

import com.springboot.financialplanning.model.Insurance;
import com.springboot.financialplanning.model.MutualFund;
import com.springboot.financialplanning.model.ThematicFund;

public class SearchResultDto {
	private List<MutualFund> mutualFunds;
	private List<ThematicFund> thematicFunds;
	private List<Insurance> insurances;
	
	
	public SearchResultDto(List<MutualFund> mutualFunds, List<ThematicFund> thematicFunds, List<Insurance> insurances) {
		super();
		this.mutualFunds = mutualFunds;
		this.thematicFunds = thematicFunds;
		this.insurances = insurances;
	}
	public List<MutualFund> getMutualFunds() {
		return mutualFunds;
	}
	public void setMutualFunds(List<MutualFund> mutualFunds) {
		this.mutualFunds = mutualFunds;
	}
	public List<ThematicFund> getThematicFunds() {
		return thematicFunds;
	}
	public void setThematicFunds(List<ThematicFund> thematicFunds) {
		this.thematicFunds = thematicFunds;
	}
	public List<Insurance> getInsurances() {
		return insurances;
	}
	public void setInsurances(List<Insurance> insurances) {
		this.insurances = insurances;
	}
	
	

}
