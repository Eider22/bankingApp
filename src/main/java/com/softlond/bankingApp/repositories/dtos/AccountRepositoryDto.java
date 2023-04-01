package com.softlond.bankingApp.repositories.dtos;

import com.softlond.bankingApp.entities.Customer;

public class AccountRepositoryDto {
	private String id;
	private String accountNumber;
	private double balance;
	private Integer customerId;
	private String accountType;

	public AccountRepositoryDto(String accountNumber, double balance, Integer customerId, String accountType) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerId = customerId;
		this.accountType = accountType;
	}

	public AccountRepositoryDto(String id, String accountNumber, double balance, Integer customerId,
			String accountType) {
		this(accountNumber, balance, customerId, accountType);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Integer getCustomer() {
		return customerId;
	}

	public void setCustomer(Integer customerId) {
		this.customerId = customerId;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
