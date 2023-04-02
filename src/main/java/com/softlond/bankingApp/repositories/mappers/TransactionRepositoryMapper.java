package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.entities.Transaction;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
import com.softlond.bankingApp.repositories.dtos.TransactionRepositoryDto;

public class TransactionRepositoryMapper extends MapperRepositroyBase<TransactionRepositoryDto, Transaction> {

	@Override
	public Transaction mapperT1T2(TransactionRepositoryDto input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionRepositoryDto mapperT2T1(Transaction input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Transaction> MapperT1T2(List<TransactionRepositoryDto> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionRepositoryDto> MapperT2T1(List<Transaction> input) {
		List<TransactionRepositoryDto> transactionDtos = new ArrayList<TransactionRepositoryDto>();
		for (Transaction transaction : input) {
			transactionDtos.add(this.mapperT2T1(transaction));
		}
		return transactionDtos;
	}
	
	public List<TransactionRepositoryDto> MapperT2T1WithoutId(List<Transaction> input) {
		List<TransactionRepositoryDto> transactionDtos = new ArrayList<TransactionRepositoryDto>();
		for (Transaction transaction : input) {
			transactionDtos.add(this.mapperT2T1WithoutId(transaction));
		}
		return transactionDtos;
	}

	public Transaction mapperT1T2WithoutId(TransactionRepositoryDto input) {
		Transaction transaction = new Transaction(input.getDate(), input.getHour(), input.getTransactionType(),
				input.getAmount(), input.getIdAccount(), input.getTargetAccountType());
		return transaction;
	}

	public TransactionRepositoryDto mapperT2T1WithoutId(Transaction input) {
		System.out.println("→" + input.getDate());
		TransactionRepositoryDto transaction = new TransactionRepositoryDto(input.getDate(), input.getHour(),
				input.getTransactionType(), input.getAmount(), input.getIdAccount(), input.getTargetAccountType());
		return transaction;
	}

}
