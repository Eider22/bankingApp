package com.softlond.bankingApp.repositories.dtos;

import com.softlond.bankingApp.entities.Customer;

public class CurrentAccountRepositoryDto extends AccountRepositoryDto {
	private Customer withdrawalsQuantity;

	public CurrentAccountRepositoryDto(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity) {
		super(accountNumber, balance, owner);
		this.withdrawalsQuantity = withdrawalsQuantity;
	}

	public CurrentAccountRepositoryDto(String id, String accountNumber, double balance, Customer owner,
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
