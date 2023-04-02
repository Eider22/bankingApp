package com.softlond.bankingApp.services.contracts;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;

public interface IAccountService {
	public AccountControllerDto save(Map accountMap) throws Exception;

	public List<AccountControllerDto> list() throws Exception;
	
	public List<AccountControllerDto> listByCustomerId() throws Exception;

	public List<AccountControllerDto> listByCustomerId(Integer customerId) throws Exception;

	public AccountControllerDto findById(String id) throws Exception;
	
	public AccountControllerDto findByIdAccountNumber(String accountNumber) throws Exception;

	public boolean delete(String id) throws Exception;
	
	public boolean deleteByCustomerId(Integer customerId) throws Exception;

	public AccountControllerDto update(String id, Map accountMap) throws Exception;

	boolean updateBalance(String accountId, Double balance) throws Exception;
}
