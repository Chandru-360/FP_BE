package com.springboot.financialplanning.model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
@Entity
public class Executive {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String city;
	private String contact;
	private LocalDate dob;
	private String username;
	private String password;

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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Executive(int id, String name, String email, String city, String contact, LocalDate dob, String username,
			String password, User user) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.city = city;
		this.contact = contact;
		this.dob = dob;
		this.username = username;
		this.password = password;
		this.user = user;
	}

	public Executive() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Executive [id=" + id + ", name=" + name + ", email=" + email + ", city=" + city + ", contact=" + contact
				+ ", dob=" + dob + ", username=" + username + ", password=" + password + ", user=" + user + "]";
	}



}