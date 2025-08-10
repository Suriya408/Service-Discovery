package com.usk.bank.dto;

import com.usk.bank.model.Account;

public class UserResponse {

	private Long Id;

	private String name;

	private String email;

	private Long phoneNumber;

	private Long startingBalance;

	private Account account;

	private String password;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
