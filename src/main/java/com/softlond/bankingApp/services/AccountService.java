package com.softlond.bankingApp.services;

import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.services.contracts.IAccountService;

public class AccountService implements IAccountService{

	@Override
	public AccountControllerDto save(Map accountMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountControllerDto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<AccountControllerDto> listByIdCustomer() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountControllerDto findByIdentity(String identityNumber) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AccountControllerDto findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public AccountControllerDto update(String id, Map accountMap) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
