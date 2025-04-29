package com.springboot.financialplanning.model;
public class LumpSumResult {
    private double maturityAmount;

    public LumpSumResult(double maturityAmount) {
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