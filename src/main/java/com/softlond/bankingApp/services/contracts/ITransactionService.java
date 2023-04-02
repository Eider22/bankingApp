package com.softlond.bankingApp.services.contracts;

import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.TransactionControllerDto;

public interface ITransactionService {
	public TransactionControllerDto save(Map transactionMapp) throws Exception;

	public TransactionControllerDto createTransaction(Map transactionMapp) throws Exception;

	public List<TransactionControllerDto> listByAccountId(Integer accountId) throws Exception;
	
	public List<TransactionControllerDto> list() throws Exception;

}
