package com.softlond.bankingApp.controllers.dtos;

import java.time.LocalDate;

public class CustomerControllerDto {
	private String id;
	private String firstName;
	private String secondName;
	private String firstLastname;
	private String secondLastname;
	private String identityNumber;
	private String dateOfBirth;

	public CustomerControllerDto(String firstName, String secondName, String firstLastname, String secondLastname,
			String identityNumber, String dateOfBirth) {
		super();
		this.firstName = firstName;
		this.secondName = secondName;
		this.firstLastname = firstLastname;
		this.secondLastname = secondLastname;
		this.identityNumber = identityNumber;
		this.dateOfBirth = dateOfBirth;
	}

	public CustomerControllerDto(String id, String firstName, String secondName, String firstLastname,
			String secondLastname, String identityNumber, String dateOfBirth) {
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

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

}
