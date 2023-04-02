package com.softlond.bankingApp.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.controllers.dtos.TransactionControllerDto;
import com.softlond.bankingApp.controllers.mappers.TransactionControllerMapper;
import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.entities.Transaction;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.repositories.TransactionRepository;
import com.softlond.bankingApp.repositories.contracts.ITransactionrepository;
import com.softlond.bankingApp.repositories.dtos.TransactionRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.TransactionRepositoryMapper;
import com.softlond.bankingApp.services.contracts.IAccountService;
import com.softlond.bankingApp.services.contracts.ITransactionService;

public class TransactionService implements ITransactionService {
	private IAccountService accountService;
	private ITransactionrepository transactionRepository;
	private TransactionControllerMapper transactionControllerMapper;
	private TransactionRepositoryMapper transactionRepositoryMapper;

	public TransactionService() {
		this.transactionRepository = new TransactionRepository();
		this.transactionControllerMapper = new TransactionControllerMapper();
		this.transactionRepositoryMapper = new TransactionRepositoryMapper();
	}

//	@Override
//	public TransactionControllerDto save(Map transactionMapp) throws Exception {
//
//		if (transactionMapp.get("numberOriginAccount") == null || transactionMapp.get("numberOriginAccount") == "") {
//			throw new MissingAtributeException("Falta atributo requerido - numberOriginAccount");
//		}
//		if (transactionMapp.get("numberTargetAccount") == null || transactionMapp.get("numberTargetAccount") == "") {
//			throw new MissingAtributeException("Falta atributo requerido - numberTargetAccount");
//		}
//		if (transactionMapp.get("amount") == null || transactionMapp.get("amount") == "") {
//			throw new MissingAtributeException("Falta atributo requerido - amount");
//		}
//		if (transactionMapp.get("transactionType") == null || transactionMapp.get("transactionType") == "") {
//			throw new MissingAtributeException("Falta atributo requerido - transactionType");
//		}
//
//		String numberOriginAccount = (String) transactionMapp.get("numberOriginAccount");
//		String numberTargetAccount = (String) transactionMapp.get("numberTargetAccount");
//		Double amount = ((Integer) transactionMapp.get("amount")).doubleValue();
//		String transactionType = (String) transactionMapp.get("transactionType");
//
//		this.accountService = new AccountService();
//
//		AccountControllerDto originAccountControllerDto = this.accountService
//				.findByIdAccountNumber(numberOriginAccount);
//		AccountControllerDto targetAccountControllerDto = this.accountService
//				.findByIdAccountNumber(numberTargetAccount);
//
//		if (transactionType.equals("1")) {
//			transactionType = "Deposito";
//		} else if (transactionType.equals("2")) {
//			transactionType = "Retiro";
//		} else if (transactionType.equals("3")) {
//			transactionType = "Transferencia";
//		} else {
//			throw new Exception("Tipo de transacción inválida");
//		}
//
//		LocalDate dateNow = LocalDate.now();
//		LocalTime timeNow = LocalTime.now();
//
//		TransactionRepositoryDto newTransaction = new TransactionRepositoryDto(dateNow, timeNow, transactionType,
//				amount, originAccountControllerDto.getId(), targetAccountControllerDto.getAccountType());
//
//		return this.transactionControllerMapper.mapperT2T1(this.transactionRepository.save(newTransaction));
//	}
//
//	@Override
//	public TransactionControllerDto transfer(Map transactionMapp) throws Exception {
//
//		return null;
//
//	}

	@Override
	public TransactionControllerDto save(String transactionType, Double amount, String accountId,
			String accountTargetType) throws Exception {

		if (amount == null) {
			return null;
		}

		if (transactionType == null || transactionType == "") {
			return null;
		}

		LocalDate dateNow = LocalDate.now();
		LocalTime timeNow = LocalTime.now();

		TransactionRepositoryDto newTransaction = new TransactionRepositoryDto(dateNow, timeNow, transactionType,
				amount, accountId, accountTargetType);

		return this.transactionControllerMapper.mapperT2T1(this.transactionRepository.save(newTransaction));
	}

	@Override
	public Object withdrawal(Map transactionMapp) throws Exception {
		
		if (transactionMapp.get("numberAccount") == null || transactionMapp.get("numberAccount") == "") {
			throw new MissingAtributeException("Falta atributo requerido - numberAccount");
		}
		
		if (transactionMapp.get("amount") == null || transactionMapp.get("amount") == "") {
			throw new MissingAtributeException("Falta atributo requerido - amount");
		}

		String numberAccount = (String) transactionMapp.get("numberAccount");
		
		Double amount = Double.parseDouble((String)transactionMapp.get("amount"));

		this.accountService = new AccountService();
		AccountControllerDto accountControllerDto = this.accountService.findByIdAccountNumber(numberAccount);
		

		if (accountControllerDto.getBalance() < amount) {
			ServiceCustomResponse response = new ServiceCustomResponse("Cuanta con fondos insuficientes");
			return response;
		}

		if (accountControllerDto.getAccountType().equals("Ahorros")) {
			this.withdrawalSavingsAccount(accountControllerDto, amount);
			
			this.accountService = new AccountService();
			if (!this.accountService.updateBalance(accountControllerDto.getId(), accountControllerDto.getBalance())) {
				throw new Exception("Problema al actualizar la cuenta");
			}

			this.save("Retiro", amount, accountControllerDto.getId(), null);

			ServiceCustomResponse response = new ServiceCustomResponse("Retiro exitoso");
			return response;
		}

		if (accountControllerDto.getAccountType().equals("Corriente")) {
			this.withdrawalCurrentAccount(accountControllerDto, amount);

			this.accountService = new AccountService();
			if (!this.accountService.updateBalance(accountControllerDto.getId(), accountControllerDto.getBalance())) {
				throw new Exception("Problema al actualizar la cuenta");
			}

			this.save("Retiro", amount, accountControllerDto.getId(), null);
			ServiceCustomResponse response = new ServiceCustomResponse("Retiro exitoso");
			return response;
		}

		return null;

	}

	private void withdrawalSavingsAccount(AccountControllerDto accountControllerDto, double amount) throws Exception {
		double withdrawalFee = 0;
		if (this.transactionRepository.withdrawalQuantity(accountControllerDto.getId()) >= 3) {
			withdrawalFee = amount * 0.01;
		}

		accountControllerDto.setBalance(accountControllerDto.getBalance() - amount - withdrawalFee);
	}

	private void withdrawalCurrentAccount(AccountControllerDto accountControllerDto, double amount) throws Exception {

		if (this.transactionRepository.withdrawalQuantity(accountControllerDto.getId()) > 5) {
			throw new Exception("Ya cumplió con el límite de 5 retiros");

		}
		accountControllerDto.setBalance(accountControllerDto.getBalance() - amount);
	}

	public void deposit(Map transactionMapp) {

	}

	@Override
	public TransactionControllerDto transfer(Map transactionMapp) throws Exception {

		return null;

	}

	@Override
	public List<TransactionControllerDto> listByAccountId(Integer accountId) throws Exception {

		if (accountId == null) {
			throw new Exception("No envió un id de la cuenta para la busqueda");
		}

		this.accountService = new AccountService();
		this.accountService.findById(Integer.toString(accountId));

		List<Transaction> transactions = this.transactionRepository.listByAccountId(accountId);

		return this.transactionControllerMapper
				.MapperT2T1(this.transactionRepositoryMapper.MapperT2T1WithoutId(transactions));

	}

	@Override
	public List<TransactionControllerDto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
