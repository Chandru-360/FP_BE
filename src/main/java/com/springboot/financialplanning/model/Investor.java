package com.springboot.financialplanning.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;



@Entity
public class Investor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String city;
	private LocalDate dob;
	private String pancardNumber;
	private String gender;
	private String contactNumber;
	
	@OneToOne
	private User user;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getPancardNumber() {
		return pancardNumber;
	}

	public void setPancardNumber(String pancardNumber) {
		this.pancardNumber = pancardNumber;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public Investor(int id, String name, String email, String city, LocalDate dob, String pancardNumber, String gender,
			String contactNumber, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.city = city;
		this.dob = dob;
		this.pancardNumber = pancardNumber;
		this.gender = gender;
		this.contactNumber = contactNumber;
		this.user = user;
	}

	public Investor() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Investor [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + ", dob=" + dob
				+ ", pancardNumber=" + pancardNumber + ", gender=" + gender + ", contactNumber=" + contactNumber
				+ ", user=" + user + "]";
	}

	


}
