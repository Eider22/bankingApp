package com.softlond.bankingApp.controllers.dtos;

public class AccountControllerDto {
	private String id;
	private String accountNumber;
	private double balance;
	private String accountType;
	private Integer customerId;

	public AccountControllerDto(String accountNumber, double balance, String accountType,
			Integer customerId) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
		this.customerId = customerId;
	}

	public AccountControllerDto(String id, String accountNumber, double balance, String accountType,
			Integer customerId) {
		this(accountNumber, balance, accountType, customerId);
		this.id = id;
	}

	public AccountControllerDto(String id, String accountNumber, double balance, String accountType) {
		this.id = id;
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountType = accountType;
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

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Integer getCustomer() {
		return customerId;
	}

	public void setCustomer(Integer customerId) {
		this.customerId = customerId;
	}

}
