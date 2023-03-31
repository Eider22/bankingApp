package com.softlond.bankingApp.repositories.mappers;

import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
import com.softlond.bankingApp.entities.Customer;

public class CustomerMapper extends MapperBase<CustomerRepositoryDto, Customer> {
	@Override
	public Customer mapperT1T2(CustomerRepositoryDto input) {
		Customer customer = new Customer(input.getId(), input.getFirstName(), input.getSecondName(),
				input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(), input.getDateOfBirth());
		return customer;
	}

	@Override
	public CustomerRepositoryDto mapperT2T1(Customer input) {
		CustomerRepositoryDto customerDto = new CustomerRepositoryDto(input.getId(), input.getFirstName(),
				input.getSecondName(), input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(),
				input.getDateOfBirth());
		return customerDto;
	}

	@Override
	public List<Customer> MapperT1T2(List<CustomerRepositoryDto> input) {
		List<Customer> customers = new ArrayList<Customer>();
		for (CustomerRepositoryDto customerdto : input) {
			customers.add(this.mapperT1T2(customerdto));
		}
		return customers;
	}

	@Override
	public List<CustomerRepositoryDto> MapperT2T1(List<Customer> input) {
		List<CustomerRepositoryDto> customersDtos = new ArrayList<CustomerRepositoryDto>();
		for (Customer customer : input) {
			customersDtos.add(this.mapperT2T1(customer));
		}
		return customersDtos;
	}

	public Customer mapperT1T2WithoutId(CustomerRepositoryDto input) {
		Customer customer = new Customer(input.getFirstName(), input.getSecondName(), input.getFirstLastname(),
				input.getSecondLastname(), input.getIdentityNumber(), input.getDateOfBirth());
		return customer;
	}

	public CustomerRepositoryDto mapperT2T1WithoutId(Customer input) {
		CustomerRepositoryDto customerDto = new CustomerRepositoryDto(input.getFirstName(), input.getSecondName(),
				input.getFirstLastname(), input.getSecondLastname(), input.getIdentityNumber(), input.getDateOfBirth());
		return customerDto;
	}

}
