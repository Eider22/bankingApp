package com.softlond.bankingApp.controllers.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionControllerDto {
	private String id;
	private String date;
	private String hour;
	private String transactionType;
	private String amount;
	private String idAccount;
	private String targetAccountType;
	
	public TransactionControllerDto(String date, String hour, String transactionType, String amount,
			String idAccount, String targetAccountType) {
		super();
		this.date = date;
		this.hour = hour;
		this.transactionType = transactionType;
		this.amount = amount;
		this.idAccount = idAccount;
		this.targetAccountType = targetAccountType;
	}
	
	public TransactionControllerDto(String id, String date, String hour, String transactionType, String amount,
			String idAccount, String targetAccountType) {
		this(date, hour, transactionType, amount, idAccount, targetAccountType);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getIdAccount() {
		return idAccount;
	}

	public void setIdAccount(String idAccount) {
		this.idAccount = idAccount;
	}

	public String getTargetAccountType() {
		return targetAccountType;
	}

	public void setTargetAccountType(String targetAccountType) {
		this.targetAccountType = targetAccountType;
	}	
	

}
