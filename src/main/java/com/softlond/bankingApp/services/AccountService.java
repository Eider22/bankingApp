package com.softlond.bankingApp.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.controllers.mappers.AccountControllerMapper;
import com.softlond.bankingApp.controllers.mappers.CustomerControllerMapper;
import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.AccountRepository;
import com.softlond.bankingApp.repositories.CustomerRepository;
import com.softlond.bankingApp.repositories.contracts.IAccountRepository;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.AccountRepositoryMapper;
import com.softlond.bankingApp.repositories.mappers.CustomerRepositoryMapper;
import com.softlond.bankingApp.services.contracts.IAccountService;
import com.softlond.bankingApp.services.contracts.ICustomerService;

public class AccountService implements IAccountService {

	private IAccountRepository accountRepository;
	private ICustomerService customerService;
	private AccountRepositoryMapper accountRepositoryMapper;
	private AccountControllerMapper accountControllerMapper;
	
	public AccountService() {
		this.accountRepository = new AccountRepository();
		this.customerService = new CustomerService();
		this.accountRepositoryMapper = new AccountRepositoryMapper();
		this.accountControllerMapper = new AccountControllerMapper();
	}

	@Override
	public AccountControllerDto save(Map accountMap) throws Exception {
		try {
			String accountNumber = (String) accountMap.get("accountNumber");
			String accountType = (String) accountMap.get("accountType");
			Integer customerId = (Integer) accountMap.get("customerId");

			if (accountNumber == null || accountNumber == "") {
				throw new MissingAtributeException("Falta atributo requerido - accountNumber");
			}
			if (accountType == null || accountType == "") {
				throw new MissingAtributeException("Falta atributo requerido - accountType");
			}

			if (customerId == null) {
				throw new MissingAtributeException("Falta atributo requerido - customerId");
			}
			
			if(accountType.equals("1")) {
				accountType = "Ahorros";
			}else if(accountType.equals("2")) {
				accountType = "Corriente";				
			}else {
				throw new Exception("Tipo de cuenta inválido");				
			}

			this.customerService.findById(customerId);

			AccountRepositoryDto newAccount = new AccountRepositoryDto(accountNumber, 0, customerId, accountType);

			Account account = this.accountRepository.save(newAccount);

			if (account == null) {
				throw new Exception("No fue posible crear la cuenta");
			}
			
			return this.accountControllerMapper.mapperT2T1(this.accountRepositoryMapper.mapperT2T1(account));

		} catch (SQLException e) {
			if (e.getErrorCode() == 19) {
			throw new Exception("No fue posible crear la cuenta, ya existe una cuenta con este número de cuenta");
			}
			throw new Exception("No fue posible crear la cuenta");
		} 
	}

	@Override
	public List<AccountControllerDto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountControllerDto> listByCustomerId() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AccountControllerDto> listByCustomerId(Integer customerId) throws Exception {
		if(customerId == null) {
			throw new Exception("No envió un id de cliente para la busqueda");
		}
		
//		this.customerService.findById(customerId);

		List<Account> accounts = this.accountRepository.listByCustomerId(customerId);
		return this.accountControllerMapper.MapperT2T1(this.accountRepositoryMapper.MapperT2T1(accounts));
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
