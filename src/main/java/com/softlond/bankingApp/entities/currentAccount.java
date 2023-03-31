package com.softlond.bankingApp.entities;

public class CurrentAccount {

	private String id;
	private String accountNumber;
	private double balance;
	private Customer owner;
	private Customer withdrawalsQuantity;

	public CurrentAccount(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

	public CurrentAccount(String id, String accountNumber, double balance, Customer owner,
			Customer withdrawalsQuantity) {
		this(accountNumber, balance, owner, withdrawalsQuantity);
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

	public Customer getOwner() {
		return owner;
	}

	public void setOwner(Customer owner) {
		this.owner = owner;
	}

	public Customer getWithdrawalsQuantity() {
		return withdrawalsQuantity;
	}

	public void setWithdrawalsQuantity(Customer withdrawalsQuantity) {
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

}
