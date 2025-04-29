package com.springboot.financialplanning.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.enums.Category;



@Entity
public class Insurance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String policyName;
	@Enumerated(EnumType.STRING)
	private Category category;
	private double premium;
	private int policyTenure;
	private String claimSettlement;
	private String description;
	private String ageCreteria;
	private double coverage;
	
	@ManyToOne
	private Company company;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPolicyName() {
		return policyName;
	}

	public void setPolicyName(String policyName) {
		this.policyName = policyName;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public int getPolicyTenure() {
		return policyTenure;
	}

	public void setPolicyTenure(int policyTenure) {
		this.policyTenure = policyTenure;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

	public String getClaimSettlement() {
		return claimSettlement;
	}

	public void setClaimSettlement(String claimSettlement) {
		this.claimSettlement = claimSettlement;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAgeCreteria() {
		return ageCreteria;
	}

	public void setAgeCreteria(String ageCreteria) {
		this.ageCreteria = ageCreteria;
	}

	public double getCoverage() {
		return coverage;
	}

	public void setCoverage(double coverage) {
		this.coverage = coverage;
	}

	public Insurance(int id, String policyName, Category category, double premium, int policyTenure,
			String claimSettlement, String description, String ageCreteria, double coverage) {
		super();
		this.id = id;
		this.policyName = policyName;
		this.category = category;
		this.premium = premium;
		this.policyTenure = policyTenure;
		this.claimSettlement = claimSettlement;
		this.description = description;
		this.ageCreteria = ageCreteria;
		this.coverage = coverage;
		this.company = company;
	}

	public Insurance() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Insurance [id=" + id + ", policyName=" + policyName + ", category=" + category + ", premium=" + premium
				+ ", policyTenure=" + policyTenure + ", claimSettlement=" + claimSettlement + ", description="
				+ description + ", ageCreteria=" + ageCreteria + ", coverage=" + coverage + ", company=" + company
				+ "]";
	}
	

}
