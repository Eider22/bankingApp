package com.softlond.bankingApp.controllers.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;

public class AccountControllerMapper extends MapperControllerBase<AccountControllerDto, AccountRepositoryDto> {

	public AccountRepositoryDto mapperT1T2(AccountControllerDto input) {
		AccountRepositoryDto account = new AccountRepositoryDto(input.getId(), input.getAccountNumber(),
				input.getBalance(), input.getCustomer(), input.getAccountType());
		return account;
	}

	@Override
	public AccountControllerDto mapperT2T1(AccountRepositoryDto input) {
		AccountControllerDto accountDto = new AccountControllerDto(input.getId(), input.getAccountNumber(),
				input.getBalance(), input.getCustomer(), input.getAccountType());
		return accountDto;
	}

	public AccountControllerDto mapperT2T1WithoutCustomer(AccountRepositoryDto input) {
		AccountControllerDto accountDto = new AccountControllerDto(input.getId(), input.getAccountNumber(),
				input.getBalance(), input.getAccountType());
		return accountDto;
	}

	@Override
	public List<AccountRepositoryDto> MapperT1T2(List<AccountControllerDto> input) {
		List<AccountRepositoryDto> accounts = new ArrayList<AccountRepositoryDto>();
		for (AccountControllerDto accountdto : input) {
			accounts.add(this.mapperT1T2(accountdto));
		}
		return accounts;
	}

	@Override
	public List<AccountControllerDto> MapperT2T1(List<AccountRepositoryDto> input) {
		List<AccountControllerDto> accountsDtos = new ArrayList<AccountControllerDto>();
		for (AccountRepositoryDto account : input) {
			accountsDtos.add(this.mapperT2T1(account));
		}
		return accountsDtos;
	}

	public AccountRepositoryDto mapperT1T2WithoutId(AccountControllerDto input) {
		AccountRepositoryDto account = new AccountRepositoryDto(input.getAccountNumber(), input.getBalance(),
				input.getCustomer(), input.getAccountType());
		return account;
	}

	public AccountControllerDto mapperT2T1WithoutId(AccountRepositoryDto input) {
		AccountControllerDto accountDto = new AccountControllerDto(input.getAccountNumber(), input.getBalance(),
				input.getCustomer(), input.getAccountType());
		return accountDto;
	}

}
