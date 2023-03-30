package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.dtos.SavingsAccountDto;
import com.softlond.bankingApp.entities.SavingsAccount;

public class SavingsAccountMapper extends MapperBase<SavingsAccountDto, SavingsAccount> {

	@Override
	public SavingsAccount mapperT1T2(SavingsAccountDto input) {
		SavingsAccount savingsAccount = new SavingsAccount(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity(), input.getDepositsQuantity());
		return savingsAccount;
	}

	@Override
	public SavingsAccountDto mapperT2T1(SavingsAccount input) {
		SavingsAccountDto savingsAccountDto = new SavingsAccountDto(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity(), input.getDepositsQuantity());
		return savingsAccountDto;
	}

	@Override
	public List<SavingsAccount> MapperT1T2(List<SavingsAccountDto> input) {
		List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
		for (SavingsAccountDto savingsAccountdto : input) {
			savingsAccounts.add(this.mapperT1T2(savingsAccountdto));
		}
		return savingsAccounts;
	}

	@Override
	public List<SavingsAccountDto> MapperT2T1(List<SavingsAccount> input) {
		List<SavingsAccountDto> savingsAccountsDtos = new ArrayList<SavingsAccountDto>();
		for (SavingsAccount savingsAccount : input) {
			savingsAccountsDtos.add(this.mapperT2T1(savingsAccount));
		}
		return savingsAccountsDtos;
	}

}
