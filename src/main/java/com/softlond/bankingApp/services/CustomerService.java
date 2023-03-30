package com.softlond.bankingApp.services;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.CustomerRepository;

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

	public List<Customer> list() {
		return (List<Customer>) this.customerRepository.list();
	}

	public Customer findByIdentity(String identityNumber) throws NotFoundCustomerException {

		if (identityNumber == null) {
			return null;
		}

		Object customer = this.customerRepository.find(identityNumber);

		if (customer == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con la identificación indicada");
		}

		return (Customer) customer;

	}

	public void delete(String identityNumber) throws NotFoundCustomerException {

		if (identityNumber == null) {
			return;
		}

		Customer oldCustomer = (Customer) this.findByIdentity(identityNumber);
		this.customerRepository.delete(identityNumber);
	}

	public void update(String identityNumber, Object modifiedCustomer) throws NotFoundCustomerException {
		if (identityNumber == null || modifiedCustomer == null) {
			return;
		}

		Customer oldCustomer = (Customer) this.findByIdentity(identityNumber);
		this.customerRepository.update(identityNumber, oldCustomer, modifiedCustomer);
	}
}
