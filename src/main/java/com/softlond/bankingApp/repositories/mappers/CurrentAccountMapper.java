package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.dtos.CurrentAccountDto;
import com.softlond.bankingApp.dtos.CurrentAccountDto;
import com.softlond.bankingApp.entities.CurrentAccount;
import com.softlond.bankingApp.entities.CurrentAccount;

public class CurrentAccountMapper extends MapperBase<CurrentAccountDto, CurrentAccount> {
	@Override
	public CurrentAccount mapperT1T2(CurrentAccountDto input) {
		CurrentAccount currentAccount = new CurrentAccount(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity());
		return currentAccount;
	}

	@Override
	public CurrentAccountDto mapperT2T1(CurrentAccount input) {
		CurrentAccountDto currentAccountDto = new CurrentAccountDto(input.getAccountNumber(), input.getBalance(),
				input.getOwner(), input.getWithdrawalsQuantity());
		return currentAccountDto;
	}

	@Override
	public List<CurrentAccount> MapperT1T2(List<CurrentAccountDto> input) {
		List<CurrentAccount> currentAccounts = new ArrayList<CurrentAccount>();
		for (CurrentAccountDto currentAccountdto : input) {
			currentAccounts.add(this.mapperT1T2(currentAccountdto));
		}
		return currentAccounts;
	}

	@Override
	public List<CurrentAccountDto> MapperT2T1(List<CurrentAccount> input) {
		List<CurrentAccountDto> currentAccountsDtos = new ArrayList<CurrentAccountDto>();
		for (CurrentAccount currentAccount : input) {
			currentAccountsDtos.add(this.mapperT2T1(currentAccount));
		}
		return currentAccountsDtos;
	}

}
