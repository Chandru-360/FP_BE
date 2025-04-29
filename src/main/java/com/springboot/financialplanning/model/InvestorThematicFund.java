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
public class InvestorThematicFund {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate investmentDate;
	private double onetimeAmount;
	
	@Enumerated(EnumType.STRING)
	private InvestmentType investmentType;  
    private LocalDate sipStartDate;
    private double sipAmount;
    private double navUnits;
	
	@ManyToOne
	private Investor investor;
	
	@ManyToOne
	private ThematicFund thematicFund;
	
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


	public LocalDate getInvestmentDate() {
		return investmentDate;
	}

	public void setInvestmentDate(LocalDate investmentDate) {
		this.investmentDate = investmentDate;
	}


	public ThematicFund getThematicFund() {
		return thematicFund;
	}

	public void setThematicFund(ThematicFund thematicFund) {
		this.thematicFund = thematicFund;
	}

	public Double getOnetimeAmount() {
		return onetimeAmount;
	}

	public void setOnetimeAmount(Double onetimeAmount) {
		this.onetimeAmount = onetimeAmount;
	}

	public InvestmentType getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(InvestmentType investmentType) {
		this.investmentType = investmentType;
	}

	public LocalDate getSipStartDate() {
		return sipStartDate;
	}

	public void setSipStartDate(LocalDate sipStartDate) {
		this.sipStartDate = sipStartDate;
	}

	public Double getSipAmount() {
		return sipAmount;
	}

	public void setSipAmount(Double sipAmount) {
		this.sipAmount = sipAmount;
	}

	

	public void setOnetimeAmount(double onetimeAmount) {
		this.onetimeAmount = onetimeAmount;
	}

	public void setSipAmount(double sipAmount) {
		this.sipAmount = sipAmount;
	}

	public double getNavUnits() {
		return navUnits;
	}

	public void setNavUnits(double navUnits) {
		this.navUnits = navUnits;
	}

	
	
}

