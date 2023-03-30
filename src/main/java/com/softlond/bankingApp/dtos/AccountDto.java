package com.softlond.bankingApp.dtos;

import com.softlond.bankingApp.entities.Customer;

public class AccountDto {
	private String accountNumber;
	private double balance;
	private Customer owner;

	public AccountDto(String accountNumber, double balance, Customer owner) {
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.owner = owner;
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

}
