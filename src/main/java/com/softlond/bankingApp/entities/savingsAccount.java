package com.softlond.bankingApp.entities;

public class SavingsAccount {
	private String accountNumber;
	private double balance;
	private Customer owner;
	private Customer withdrawalsQuantity;
	private Customer depositsQuantity;

	public SavingsAccount(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity,
			Customer depositsQuantity) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
		this.withdrawalsQuantity = withdrawalsQuantity;
		this.depositsQuantity = depositsQuantity;
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

	public Customer getDepositsQuantity() {
		return depositsQuantity;
	}

	public void setDepositsQuantity(Customer depositsQuantity) {
		this.depositsQuantity = depositsQuantity;
	}

}
