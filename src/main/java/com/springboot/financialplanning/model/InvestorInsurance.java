package com.springboot.financialplanning.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.enums.InvestmentType;



@Entity
public class InvestorInsurance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate investmentDate;
	private Double yearlyAmount;
	private Double monthlyAmount;
	private LocalDate monthlyInvestmentDate;
	

	@Enumerated(EnumType.STRING)
	private InvestmentType investmentType;
	
	
	@ManyToOne
	private Investor investor;
	
	@ManyToOne
	private Insurance insurance;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public LocalDate getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(LocalDate investmentDate) {
		this.investmentDate = investmentDate;
	}

	public Double getYearlyAmount() {
		return yearlyAmount;
	}

	public void setYearlyAmount(Double yearlyAmount) {
		this.yearlyAmount = yearlyAmount;
	}

	public Double getMonthlyAmount() {
		return monthlyAmount;
	}

	public void setMonthlyAmount(Double monthlyAmount) {
		this.monthlyAmount = monthlyAmount;
	}

	public LocalDate getMonthlyInvestmentDate() {
		return monthlyInvestmentDate;
	}

	public void setMonthlyInvestmentDate(LocalDate monthlyInvestmentDate) {
		this.monthlyInvestmentDate = monthlyInvestmentDate;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	public InvestorInsurance(int id, LocalDate investmentDate, Double yearlyAmount, Double monthlyAmount,
			LocalDate monthlyInvestmentDate, InvestmentType investmentType, Investor investor, Insurance insurance) {
		super();
		this.id = id;
		this.investmentDate = investmentDate;
		this.yearlyAmount = yearlyAmount;
		this.monthlyAmount = monthlyAmount;
		this.monthlyInvestmentDate = monthlyInvestmentDate;
		this.investmentType = investmentType;
		this.investor = investor;
		this.insurance = insurance;
	}

	public InvestorInsurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "InvestorInsurance [id=" + id + ", investmentDate=" + investmentDate + ", yearlyAmount=" + yearlyAmount
				+ ", monthlyAmount=" + monthlyAmount + ", monthlyInvestmentDate=" + monthlyInvestmentDate
				+ ", investmentType=" + investmentType + ", investor=" + investor + ", insurance=" + insurance + "]";
	}
	
	
}

