package com.softlond.bankingApp.controllers.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.controllers.dtos.AccountControllerDto;
import com.softlond.bankingApp.controllers.dtos.TransactionControllerDto;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.dtos.TransactionRepositoryDto;

public class TransactionControllerMapper
		extends MapperControllerBase<TransactionControllerDto, TransactionRepositoryDto> {

	@Override
	public TransactionRepositoryDto mapperT1T2(TransactionControllerDto input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TransactionControllerDto mapperT2T1(TransactionRepositoryDto input) {
		TransactionControllerDto transactiontDto = new TransactionControllerDto(input.getDate().toString(),
				input.getHour().toString(), input.getTransactionType(), Double.toString(input.getAmount()),
				input.getIdAccount(), input.getTargetAccountType());
		return transactiontDto;
	}

	@Override
	public List<TransactionRepositoryDto> MapperT1T2(List<TransactionControllerDto> input) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TransactionControllerDto> MapperT2T1(List<TransactionRepositoryDto> input) {
		List<TransactionControllerDto> transactionsDtos = new ArrayList<TransactionControllerDto>();
		for (TransactionRepositoryDto transaction : input) {
			transactionsDtos.add(this.mapperT2T1(transaction));
		}
		return transactionsDtos;
	}

}
