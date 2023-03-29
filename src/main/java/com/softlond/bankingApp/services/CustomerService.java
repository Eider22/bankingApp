package com.softlond.bankingApp.services;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.repositories.CustomerRepository;

public class CustomerService {
	private CustomerRepository customerRepository;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
	}

	public void save(Customer newCustomer) {
		this.customerRepository.save(newCustomer);
	}

	public List<Customer> listarPersonas() {
		return (List<Customer>) this.customerRepository.list();
	}

	public Customer find(String identifyNumber) throws Exception {
		Object customer = this.customerRepository.find(identifyNumber);
		if (customer == null) {
			//TODO: Personal exception
			throw new Exception("No se encontro la persona");
		}
		return (Customer) customer;
	}

	public void delete(String identifyNumber) {
		this.customerRepository.delete(identifyNumber);
	}
	
	public Customer update(String identityNumber, Object customer) throws Exception {
		Customer cust = (Customer)this.find(identityNumber);
		this.customerRepository.update(identityNumber, customer);
		return null;
	}
}
