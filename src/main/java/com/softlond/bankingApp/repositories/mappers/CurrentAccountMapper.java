package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.CurrentAccount;
import com.softlond.bankingApp.repositories.dtos.CurrentAccountRepositoryDto;
import com.softlond.bankingApp.entities.CurrentAccount;

public class CurrentAccountMapper extends MapperRepositroyBase<CurrentAccountRepositoryDto, CurrentAccount> {
	@Override
	public CurrentAccount mapperT1T2(CurrentAccountRepositoryDto input) {
		CurrentAccount currentAccount = new CurrentAccount(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity());
		return currentAccount;
	}

	@Override
	public CurrentAccountRepositoryDto mapperT2T1(CurrentAccount input) {
		CurrentAccountRepositoryDto currentAccountDto = new CurrentAccountRepositoryDto(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity());
		return currentAccountDto;
	}

	@Override
	public List<CurrentAccount> MapperT1T2(List<CurrentAccountRepositoryDto> input) {
		List<CurrentAccount> currentAccounts = new ArrayList<CurrentAccount>();
		for (CurrentAccountRepositoryDto currentAccountdto : input) {
			currentAccounts.add(this.mapperT1T2(currentAccountdto));
		}
		return currentAccounts;
	}

	@Override
	public List<CurrentAccountRepositoryDto> MapperT2T1(List<CurrentAccount> input) {
		List<CurrentAccountRepositoryDto> currentAccountsDtos = new ArrayList<CurrentAccountRepositoryDto>();
		for (CurrentAccount currentAccount : input) {
			currentAccountsDtos.add(this.mapperT2T1(currentAccount));
		}
		return currentAccountsDtos;
	}

}
