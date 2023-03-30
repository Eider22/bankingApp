package com.softlond.bankingApp.dtos;

import com.softlond.bankingApp.entities.Customer;

public class SavingsAccountDto extends AccountDto {
	private Customer withdrawalsQuantity;
	private Customer depositsQuantity;

	public SavingsAccountDto(String accountNumber, double balance, Customer owner, Customer withdrawalsQuantity,
			Customer depositsQuantity) {
		super(accountNumber, balance, owner);
		this.withdrawalsQuantity = withdrawalsQuantity;
		this.depositsQuantity = depositsQuantity;
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
