package com.softlond.bankingApp.services;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.CustomerRepository;

public class CustomerService {
	private CustomerRepository customerRepository;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
	}

	public void save(Customer newCustomer) throws Exception {
		if (newCustomer == null) {
			throw new Exception("Error interno en el servidor");
		}

		if (newCustomer.getFirstName() == null || newCustomer.getFirstName() == "") {
			throw new MissingAtributeException("Falta atributo requerido - firstName");
		}
		if (newCustomer.getFirstLastname() == null || newCustomer.getFirstLastname() == "") {
			throw new MissingAtributeException("Falta atributo requerido - firstLastName");
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

	public void update(String identityNumber, Object modifiedCustomer)
			throws NotFoundCustomerException, MissingAtributeException {
		if (identityNumber == null || identityNumber == "") {
			throw new MissingAtributeException("Debe ingresar la identificación del cliente que desea editar");
		}

		if (modifiedCustomer == null) {
			throw new MissingAtributeException("Debe ingresar los datos para ser modificados en el cliente");
		}		

		Customer oldCustomer = (Customer) this.findByIdentity(identityNumber);
		Customer newCustomer = (Customer) modifiedCustomer;
		
		if (newCustomer.getFirstName() == null || newCustomer.getFirstName() == "") {
			throw new MissingAtributeException("Faltan Atributos requeridos - primer nombre");
		}
		
		if (newCustomer.getFirstLastname() == null || newCustomer.getFirstLastname() == "") {
			throw new MissingAtributeException("Faltan Atributos requeridos - primer apellido");
		}
		
		if (newCustomer.getDateOfBirth() == null) {
			throw new MissingAtributeException("Faltan Atributos requeridos - fecha de nacimiento");
		}
		
		this.customerRepository.update(identityNumber, oldCustomer, modifiedCustomer);
	}
}
