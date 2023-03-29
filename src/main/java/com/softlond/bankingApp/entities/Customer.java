package com.softlond.bankingApp.entities;

import java.time.LocalDate;

public class Customer {
	private String firstName;
	private String secondName;
	private String firstLastname;
	private String secondLastName;
	private String identityNumber;
	private LocalDate dateOfBirth;

	public Customer(String firstName, String secondName, String firstLastname, String secondLastName,
			String identityNumber, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstLastname = firstLastname;
		this.secondLastName = secondLastName;
		this.identityNumber = identityNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSecondName() {
		return secondName;
	}

	public void setSecondName(String secondName) {
		this.secondName = secondName;
	}

	public String getFirstLastname() {
		return firstLastname;
	}

	public void setFirstLastname(String firstLastname) {
		this.firstLastname = firstLastname;
	}

	public String getSecondLastName() {
		return secondLastName;
	}

	public void setSecondLastName(String secondLastName) {
		this.secondLastName = secondLastName;
	}

	public String getIdentityNumber() {
		return identityNumber;
	}

	public void setIdentityNumber(String identityNumber) {
		this.identityNumber = identityNumber;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
