package com.springboot.financialplanning.dto;

public class SIPResult {
    private double maturityAmount;

    public SIPResult(double maturityAmount) {
        this.maturityAmount = maturityAmount;
    }

	public double getMaturityAmount() {
		return maturityAmount;
	}

	public void setMaturityAmount(double maturityAmount) {
		this.maturityAmount = maturityAmount;
	}

    // Getters and setters
}