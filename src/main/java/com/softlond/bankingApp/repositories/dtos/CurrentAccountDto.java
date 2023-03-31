package com.softlond.bankingApp.repositories.dtos;

import com.softlond.bankingApp.entities.Customer;

public class CurrentAccountDto extends AccountDto {
	private Customer withdrawalsQuantity;

	public CurrentAccountDto(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity) {
		super(accountNumber, balance, owner);
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

	public CurrentAccountDto(String id, String accountNumber, double balance, Customer owner,
			Customer withdrawalsQuantity) {
		super(id, accountNumber, balance, owner);
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

	public Customer getWithdrawalsQuantity() {
		return withdrawalsQuantity;
	}

	public void setWithdrawalsQuantity(Customer withdrawalsQuantity) {
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

}
