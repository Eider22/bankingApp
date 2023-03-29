package com.softlond.bankingApp.services;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.repositories.CustomerRepository;
import com.softlond.bankingApp.repositories.exceptions.NotFoundCustomerException;

public class CustomerService {
	private CustomerRepository customerRepository;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
	}

	public void save(Customer newCustomer) {
		if (newCustomer == null) {
			return;
		}

		if (newCustomer.getFirstName() == null || newCustomer.getFirstLastname() == null) {
			return;
		}

		this.customerRepository.save(newCustomer);
	}

	public List<Customer> listarPersonas() {
		return (List<Customer>) this.customerRepository.list();
	}

	public Customer findByIdentity(String identifyNumber) throws NotFoundCustomerException {

		if (identifyNumber == null) {
			return null;
		}

		System.out.println("antes");
		Object customer = this.customerRepository.find(identifyNumber);
		System.out.println("despues");
		if (customer == null) {
			return null;
		}
		return (Customer) customer;

	}

	public void delete(String identifyNumber) {
		this.customerRepository.delete(identifyNumber);
	}

	public void update(String identityNumber, Object modifiedCustomer) throws Exception {
		Customer oldCustomer = (Customer) this.findByIdentity(identityNumber);
		if (oldCustomer == null) {
			return;
		}
		this.customerRepository.update(identityNumber, oldCustomer, modifiedCustomer);
	}
}
