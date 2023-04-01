package com.softlond.bankingApp.services.contracts;

import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;

public interface IAccountService {
	public AccountControllerDto save(Map accountMap) throws Exception;

	public List<AccountControllerDto> list() throws Exception;
	
	public List<AccountControllerDto> listByIdCustomer() throws Exception;

	public AccountControllerDto findByIdentity(String identityNumber) throws Exception;

	public AccountControllerDto findById(String id) throws Exception;

	public boolean delete(String id) throws Exception;

	public AccountControllerDto update(String id, Map accountMap) throws Exception;
}
