package com.softlond.bankingApp.repositories.contracts;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;

public interface ICustomerRepository {
	public Customer save(CustomerRepositoryDto customerDto) throws Exception;

	public boolean delete(Integer identifier) throws Exception;

	public List<Customer> list() throws Exception;
	
	public Customer update(Integer identifyNumber, CustomerRepositoryDto oldCustomerDto,
			CustomerRepositoryDto modifiedCustomerDto) throws Exception;


	public Customer findById(Integer id) throws Exception;
	
	public Customer findByIdentity(String id) throws Exception;
}
