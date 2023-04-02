package com.softlond.bankingApp.repositories.contracts;

import java.util.List;

import com.softlond.bankingApp.entities.Transaction;
import com.softlond.bankingApp.repositories.dtos.TransactionRepositoryDto;

public interface ITransactionrepository {
	public TransactionRepositoryDto save(TransactionRepositoryDto transaction) throws Exception;
	
	public List<Transaction> listByAccountId(Integer idAccount) throws Exception;

}
