package com.softlond.bankingApp.controllers.mappers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;

public class CustomerControllerMapper extends MapperControllerBase<CustomerControllerDto, CustomerRepositoryDto> {
	@Override
	public CustomerRepositoryDto mapperT1T2(CustomerControllerDto input) {
		CustomerRepositoryDto customer = new CustomerRepositoryDto(input.getFirstName(), input.getSecondName(), input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(), LocalDate.parse(input.getDateOfBirth()));
		return customer;
	}

	@Override
	public CustomerControllerDto mapperT2T1(CustomerRepositoryDto input) {
		CustomerControllerDto customerDto = new CustomerControllerDto(input.getId(), input.getFirstName(),
				input.getSecondName(), input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(),
				input.getDateOfBirth().toString());
		return customerDto;
	}

	@Override
	public List<CustomerRepositoryDto> MapperT1T2(List<CustomerControllerDto> input) {
		List<CustomerRepositoryDto> customers = new ArrayList<CustomerRepositoryDto>();
		for (CustomerControllerDto customerdto : input) {
			customers.add(this.mapperT1T2(customerdto));
		}
		return customers;
	}

	@Override
	public List<CustomerControllerDto> MapperT2T1(List<CustomerRepositoryDto> input) {
		List<CustomerControllerDto> customersDtos = new ArrayList<CustomerControllerDto>();
		for (CustomerRepositoryDto customer : input) {
			customersDtos.add(this.mapperT2T1(customer));
		}
		return customersDtos;
	}

	public CustomerRepositoryDto mapperT1T2WithoutId(CustomerControllerDto input) {
		CustomerRepositoryDto customer = new CustomerRepositoryDto(input.getId(),input.getFirstName(), input.getSecondName(), input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(), LocalDate.parse(input.getDateOfBirth()));
		return customer;
	}

	public CustomerControllerDto mapperT2T1WithoutId(CustomerRepositoryDto input) {
		CustomerControllerDto customerDto = new CustomerControllerDto(input.getFirstName(), input.getSecondName(),
				input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(), input.getDateOfBirth().toString());
		return customerDto;
	}

}
