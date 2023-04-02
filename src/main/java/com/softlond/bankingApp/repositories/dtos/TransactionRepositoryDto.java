package com.softlond.bankingApp.repositories.dtos;

import java.time.LocalDate;
import java.time.LocalTime;

public class TransactionRepositoryDto {
	private String id;
	private LocalDate date;
	private LocalTime hour;
	private String transactionType;
	private Double amount;
	private String idAccount;
	private String targetAccountType;

	public TransactionRepositoryDto(LocalDate date, LocalTime hour, String transactionType, Double amount,
			String idAccount, String targetAccountType) {
		super();
		this.date = date;
		this.hour = hour;
		this.transactionType = transactionType;
		this.amount = amount;
		this.idAccount = idAccount;
		this.targetAccountType = targetAccountType;
	}

	public TransactionRepositoryDto(String id, LocalDate date, LocalTime hour, String transactionType, Double amount,
			String idAccount, String targetAccountType) {
		this(date, hour, transactionType, amount, idAccount, targetAccountType);
		this.id = id;
		this.date = date;
		this.hour = hour;
		this.transactionType = transactionType;
		this.amount = amount;
		this.idAccount = idAccount;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
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
