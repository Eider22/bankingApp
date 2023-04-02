package com.softlond.bankingApp.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.controllers.mappers.AccountControllerMapper;
import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.AccountRepository;
import com.softlond.bankingApp.repositories.contracts.IAccountRepository;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.AccountRepositoryMapper;
import com.softlond.bankingApp.services.contracts.IAccountService;
import com.softlond.bankingApp.services.contracts.ICustomerService;

public class AccountService implements IAccountService {

	private IAccountRepository accountRepository;
	private ICustomerService customerService;
	private AccountRepositoryMapper accountRepositoryMapper;
	private AccountControllerMapper accountControllerMapper;

	public AccountService() {
		this.accountRepository = new AccountRepository();
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

			if (accountType.equals("1")) {
				accountType = "Ahorros";
			} else if (accountType.equals("2")) {
				accountType = "Corriente";
			} else {
				throw new Exception("Tipo de cuenta inválido");
			}

			this.customerService = new CustomerService();
			this.customerService.findById(customerId);

			AccountRepositoryDto newAccount = new AccountRepositoryDto(accountNumber, 100000, customerId, accountType);

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
		if (customerId == null) {
			throw new Exception("No envió un id de cliente para la busqueda");
		}

		this.customerService = new CustomerService();
		this.customerService.findById(customerId);

		List<Account> accounts = this.accountRepository.listByCustomerId(customerId);
		return this.accountControllerMapper.MapperT2T1(this.accountRepositoryMapper.MapperT2T1(accounts));
	}

	@Override
	public AccountControllerDto findById(String id) throws Exception {
		if (id == null) {
			throw new NotFoundCustomerException("Debe enviar el id de la cuenta que desea obtener");
		}

		AccountRepositoryDto account = this.accountRepository.findById(id);

		if (account == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con el id indicado");
		}

		return this.accountControllerMapper.mapperT2T1(account);
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

	@Override
	public boolean deleteByCustomerId(Integer customerId) throws Exception {

		if (customerId == null) {
			throw new NotFoundCustomerException("Debe enviar el id del cliente que desea eliminar");
		}

		boolean ok = this.accountRepository.deleteByCustomerId(customerId);
		if (!ok) {
			return false;
		}
		return true;
	}

	@Override
	public AccountControllerDto findByIdAccountNumber(String accountNumber) throws Exception {
		if (accountNumber == null || accountNumber == "") {
			throw new NotFoundCustomerException("Debe enviar el número de la cuenta que desea obtener");
		}

		Account account = this.accountRepository.findByAccountNumber(accountNumber);

		if (account == null) {
			throw new NotFoundCustomerException("No se encontró la cuenta con el número de cuenta indicado");
		}

		return this.accountControllerMapper.mapperT2T1(this.accountRepositoryMapper.mapperT2T1(account));
	}

	@Override
	public boolean updateBalance(String accountId, Double balance) throws SQLException {
		if (accountId == null || accountId == "") {
			return false;
		}

		if (balance == null) {
			return false;
		}
		return this.accountRepository.updateBalance(accountId, balance);
	}

}
