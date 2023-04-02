package com.softlond.bankingApp.services.contracts;

import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.TransactionControllerDto;

public interface ITransactionService {

	public TransactionControllerDto transfer(Map transactionMapp) throws Exception;

	public List<TransactionControllerDto> listByAccountId(Integer accountId) throws Exception;
	
	public List<TransactionControllerDto> list() throws Exception;

	TransactionControllerDto save(String transactionType, Double amount, String accountId, String accountTargetType)
			throws Exception;

	Object withdrawal(Map transactionMapp) throws Exception;

}
