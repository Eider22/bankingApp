package com.softlond.bankingApp.entities;

public class Account {
	private String id;
	private String accountNumber;
	private double balance;
	private String accountType;
	private Integer customerId;

	public Account(String accountNumber, double balance, Integer customerId, String accountType) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.customerId = customerId;
		this.accountType = accountType;
	}

	public Account(String id, String accountNumber, double balance, Integer customer, String accountType) {
		this(accountNumber, balance, customer, accountType);
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

	public void setCustomer(Integer customer) {
		this.customerId = customer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	
	
}
