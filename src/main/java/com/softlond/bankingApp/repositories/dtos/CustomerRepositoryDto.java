package com.softlond.bankingApp.repositories.dtos;

import java.time.LocalDate;

public class CustomerRepositoryDto {
	private String id;
	private String firstName;
	private String secondName;
	private String firstLastname;
	private String secondLastname;
	private String identityNumber;
	private LocalDate dateOfBirth;

	public CustomerRepositoryDto(String firstName, String secondName, String firstLastname, String secondLastname,
			String identityNumber, LocalDate dateOfBirth) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstLastname = firstLastname;
		this.secondLastname = secondLastname;
		this.identityNumber = identityNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public CustomerRepositoryDto(String id, String firstName, String secondName, String firstLastname,
			String secondLastname, String identityNumber, LocalDate dateOfBirth) {
		this(firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth);
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getSecondLastname() {
		return secondLastname;
	}

	public void setSecondLastname(String secondLastName) {
		this.secondLastname = secondLastName;
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
