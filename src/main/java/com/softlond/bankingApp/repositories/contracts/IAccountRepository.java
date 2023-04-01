package com.softlond.bankingApp.repositories.contracts;

import java.util.List;

import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;

public interface IAccountRepository {

	public Account save(AccountRepositoryDto accountDto) throws Exception;

	public boolean delete(String id) throws Exception;

	public List<Account> list() throws Exception;
	
	public List<Account> listByCustomerId(Integer customerId) throws Exception;
	
	public Account findById(String id) throws Exception;
	
}
