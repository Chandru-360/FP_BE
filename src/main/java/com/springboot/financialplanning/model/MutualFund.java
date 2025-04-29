package com.springboot.financialplanning.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.enums.Category;
import com.enums.RickFactor;

@Entity
public class MutualFund {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
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
	
	
	@ManyToOne
	private Company Company;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	

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

	public Company getCompany() {
		return Company;
	}

	public void setCompany(Company company) {
		Company = company;
	}

	public double getNavPrice() {
		return navPrice;
	}

	public void setNavPrice(double navPrice) {
		this.navPrice = navPrice;
	}

	

	

}