package com.springboot.financialplanning.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import com.enums.Category;
import com.enums.RickFactor;

public class MutualFundDto {

	
	private String fundName;
	@Enumerated(EnumType.STRING)
	private Category category;
	private double fundSize;
	@Enumerated(EnumType.STRING)
	private RickFactor riskFactor;
	private String returnFactor;
	private String expectedReturns;
	private String lockingPeriod;
	private double minInvenstmentAmount;
	private double navPrice;

	public String getFundName() {
		return fundName;
	}
	public void setFundName(String fundName) {
		this.fundName = fundName;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public double getFundSize() {
		return fundSize;
	}
	public void setFundSize(double fundSize) {
		this.fundSize = fundSize;
	}
	public RickFactor getRiskFactor() {
		return riskFactor;
	}
	public void setRiskFactor(RickFactor riskFactor) {
		this.riskFactor = riskFactor;
	}
	public String getReturnFactor() {
		return returnFactor;
	}
	public void setReturnFactor(String returnFactor) {
		this.returnFactor = returnFactor;
	}
	public String getExpectedReturns() {
		return expectedReturns;
	}
	public void setExpectedReturns(String expectedReturns) {
		this.expectedReturns = expectedReturns;
	}
	public String getLockingPeriod() {
		return lockingPeriod;
	}
	public void setLockingPeriod(String lockingPeriod) {
		this.lockingPeriod = lockingPeriod;
	}
	public double getMinInvenstmentAmount() {
		return minInvenstmentAmount;
	}
	public void setMinInvenstmentAmount(double minInvenstmentAmount) {
		this.minInvenstmentAmount = minInvenstmentAmount;
	}
	public double getNavPrice() {
		return navPrice;
	}
	public void setNavPrice(double navPrice) {
		this.navPrice = navPrice;
	}
	
	
	
}
