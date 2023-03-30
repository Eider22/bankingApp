package com.softlond.bankingApp.dtos;

import com.softlond.bankingApp.entities.Customer;

public class CurrentAccountDto extends AccountDto{
	private Customer withdrawalsQuantity;

	public CurrentAccountDto(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity) {
		super(accountNumber, balance, owner);
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

	public Customer getWithdrawalsQuantity() {
		return withdrawalsQuantity;
	}

	public void setWithdrawalsQuantity(Customer withdrawalsQuantity) {
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

}
