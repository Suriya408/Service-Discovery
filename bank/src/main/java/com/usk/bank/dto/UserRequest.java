package com.usk.bank.dto;

public class UserRequest {

	private String name;

	private String email;

	private Long phoneNumber;

	private Long startingBalance;

	private String password;

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

	public Long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(Long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Long getStartingBalance() {
		return startingBalance;
	}

	public void setStartingBalance(Long startingBalance) {
		this.startingBalance = startingBalance;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
