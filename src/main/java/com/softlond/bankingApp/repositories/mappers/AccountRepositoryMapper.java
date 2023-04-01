package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;

public class AccountRepositoryMapper extends MapperRepositroyBase<AccountRepositoryDto, Account> {

	@Override
	public Account mapperT1T2(AccountRepositoryDto input) {
		Account account = new Account(input.getId(), input.getAccountNumber(), input.getBalance(),
				input.getCustomer(), input.getAccountType());
		return account;
	}

	@Override
	public AccountRepositoryDto mapperT2T1(Account input) {
		AccountRepositoryDto accountDto = new AccountRepositoryDto(input.getId(), input.getAccountNumber(),
				input.getBalance(), input.getCustomer(), input.getAccountType());
		return accountDto;
	}

	@Override
	public List<Account> MapperT1T2(List<AccountRepositoryDto> input) {
		List<Account> accounts = new ArrayList<Account>();
		for (AccountRepositoryDto accountdto : input) {
			accounts.add(this.mapperT1T2(accountdto));
		}
		return accounts;
	}

	@Override
	public List<AccountRepositoryDto> MapperT2T1(List<Account> input) {
		List<AccountRepositoryDto> accountsDtos = new ArrayList<AccountRepositoryDto>();
		for (Account account : input) {
			accountsDtos.add(this.mapperT2T1(account));
		}
		return accountsDtos;
	}

	public Account mapperT1T2WithoutId(AccountRepositoryDto input) {
		Account account = new Account(input.getAccountNumber(), input.getBalance(), input.getCustomer(),
				input.getAccountType());
		return account;
	}

	public AccountRepositoryDto mapperT2T1WithoutId(Account input) {
		AccountRepositoryDto accountDto = new AccountRepositoryDto(input.getAccountNumber(), input.getBalance(),
				input.getCustomer(), input.getAccountType());
		return accountDto;
	}

}
