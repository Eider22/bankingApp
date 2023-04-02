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

	@Override
	public TransactionControllerDto save(Map transactionMapp) throws Exception {

		String numberOriginAccount = (String) transactionMapp.get("numberOriginAccount");
		String numberTargetAccount = (String) transactionMapp.get("numberTargetAccount");
		Double amount = ((Integer) transactionMapp.get("amount")).doubleValue();
		String transactionType = (String) transactionMapp.get("transactionType");

		if (numberOriginAccount == null || numberOriginAccount == "") {
			throw new MissingAtributeException("Falta atributo requerido - numberOriginAccount");
		}
		if (numberTargetAccount == null || numberTargetAccount == "") {
			throw new MissingAtributeException("Falta atributo requerido - numberTargetAccount");
		}
		if (amount == null) {
			throw new MissingAtributeException("Falta atributo requerido - amount");
		}
		if (transactionType == null || transactionType == "") {
			throw new MissingAtributeException("Falta atributo requerido - transactionType");
		}

		this.accountService = new AccountService();
		
		AccountControllerDto originAccountControllerDto = this.accountService
				.findByIdAccountNumber(numberOriginAccount);
		AccountControllerDto targetAccountControllerDto = this.accountService.findByIdAccountNumber(numberTargetAccount);

		if (transactionType.equals("1")) {
			transactionType = "Deposito";
		} else if (transactionType.equals("2")) {
			transactionType = "Retiro";
		} else if (transactionType.equals("3")) {
			transactionType = "Transferencia";
		} else {
			throw new Exception("Tipo de transacción inválida");
		}

		LocalDate dateNow = LocalDate.now();
		LocalTime timeNow = LocalTime.now();
		
		System.out.println(dateNow.toString());
		System.out.println(timeNow.toString());

		TransactionRepositoryDto newTransaction = new TransactionRepositoryDto(dateNow, timeNow, transactionType,
				amount, originAccountControllerDto.getId(), targetAccountControllerDto.getAccountType());
		
		
		return this.transactionControllerMapper.mapperT2T1(this.transactionRepository.save(newTransaction));
	}

	@Override
	public TransactionControllerDto createTransaction(Map transactionMapp) throws Exception {
		return null;
		
	}

	@Override
	public List<TransactionControllerDto> listByAccountId(Integer accountId) throws Exception {
		
		if(accountId == null) {
			throw new Exception("No envió un id de la cuenta para la busqueda");
		}
		
		this.accountService = new AccountService();
		this.accountService.findById(Integer.toString(accountId));

		List<Transaction> transactions= this.transactionRepository.listByAccountId(accountId);
		
		return this.transactionControllerMapper.MapperT2T1(this.transactionRepositoryMapper.MapperT2T1WithoutId(transactions));
		
	}

	@Override
	public List<TransactionControllerDto> list() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
