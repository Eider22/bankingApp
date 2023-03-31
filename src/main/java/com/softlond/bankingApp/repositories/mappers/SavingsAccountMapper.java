package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.SavingsAccount;
import com.softlond.bankingApp.repositories.dtos.SavingsAccountRepositoryDto;

public class SavingsAccountMapper extends MapperRepositroyBase<SavingsAccountRepositoryDto, SavingsAccount> {

	@Override
	public SavingsAccount mapperT1T2(SavingsAccountRepositoryDto input) {
		SavingsAccount savingsAccount = new SavingsAccount(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity(), input.getDepositsQuantity());
		return savingsAccount;
	}

	@Override
	public SavingsAccountRepositoryDto mapperT2T1(SavingsAccount input) {
		SavingsAccountRepositoryDto savingsAccountDto = new SavingsAccountRepositoryDto(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity(), input.getDepositsQuantity());
		return savingsAccountDto;
	}

	@Override
	public List<SavingsAccount> MapperT1T2(List<SavingsAccountRepositoryDto> input) {
		List<SavingsAccount> savingsAccounts = new ArrayList<SavingsAccount>();
		for (SavingsAccountRepositoryDto savingsAccountdto : input) {
			savingsAccounts.add(this.mapperT1T2(savingsAccountdto));
		}
		return savingsAccounts;
	}

	@Override
	public List<SavingsAccountRepositoryDto> MapperT2T1(List<SavingsAccount> input) {
		List<SavingsAccountRepositoryDto> savingsAccountsDtos = new ArrayList<SavingsAccountRepositoryDto>();
		for (SavingsAccount savingsAccount : input) {
			savingsAccountsDtos.add(this.mapperT2T1(savingsAccount));
		}
		return savingsAccountsDtos;
	}

}
