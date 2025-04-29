package com.springboot.financialplanning.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Withdraw {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private LocalDate withdrawalDate;
	private double withdrawalAmount;
	private String paymentMethod;
	
	@ManyToOne
	private InvestorMutualFund investorMutualFund;
	
	@ManyToOne
	private InvestorThematicFund investorThematicFund;
	
	@ManyToOne
	 @JoinColumn(name = "investor_id")
	private Investor investor;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getWithdrawalDate() {
		return withdrawalDate;
	}

	public void setWithdrawalDate(LocalDate withdrawalDate) {
		this.withdrawalDate = withdrawalDate;
	}

	public double getWithdrawalAmount() {
		return withdrawalAmount;
	}

	public void setWithdrawalAmount(double withdrawalAmount) {
		this.withdrawalAmount = withdrawalAmount;
	}

	public String getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	public InvestorMutualFund getInvestorMutualFund() {
		return investorMutualFund;
	}

	public void setInvestorMutualFund(InvestorMutualFund investorMutualFund) {
		this.investorMutualFund = investorMutualFund;
	}

	public InvestorThematicFund getInvestorThematicFund() {
		return investorThematicFund;
	}

	public void setInvestorThematicFund(InvestorThematicFund investorThematicFund) {
		this.investorThematicFund = investorThematicFund;
	}

	public Investor getInvestor() {
		return investor;
	}

	public void setInvestor(Investor investor) {
		this.investor = investor;
	}

	@Override
	public String toString() {
		return "Withdraw [id=" + id + ", withdrawalDate=" + withdrawalDate + ", withdrawalAmount=" + withdrawalAmount
				+ ", paymentMethod=" + paymentMethod + ", investorMutualFund=" + investorMutualFund
				+ ", investorThematicFund=" + investorThematicFund + ", investor=" + investor + "]";
	}

	
	
	

}
