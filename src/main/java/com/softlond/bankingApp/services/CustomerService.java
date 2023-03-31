package com.softlond.bankingApp.services;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;
import com.softlond.bankingApp.controllers.mappers.CustomerControllerMapper;
import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.CustomerRepository;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.CustomerRepositoryMapper;

public class CustomerService {
	private CustomerRepository customerRepository;
	private CustomerRepositoryMapper customerRepositoryMapper;
	private CustomerControllerMapper customerControllerMapper;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
		this.customerRepositoryMapper = new CustomerRepositoryMapper();
		this.customerControllerMapper = new CustomerControllerMapper();
	}

	// TODO: Pasar un Map en lugar de un costumer para validar la estructura sin
	// usar DTOS
	public CustomerControllerDto save(Map customerMap)
			throws DateTimeParseException, MissingAtributeException, Exception {

		try {
			String firstName = (String) customerMap.get("firstName");
			String secondName = (String) customerMap.get("secondName");
			String firstLastname = (String) customerMap.get("firstLastname");
			String secondLastname = (String) customerMap.get("secondLastname");
			String identityNumber = (String) customerMap.get("identityNumber");
			LocalDate dateOfBirth = LocalDate.parse((String) customerMap.get("dateOfBirth"));

			if (firstName == null || firstName == "") {
				throw new MissingAtributeException("Falta atributo requerido - firstName");
			}
			if (firstLastname == null || firstLastname == "") {
				throw new MissingAtributeException("Falta atributo requerido - firstLastName");
			}

			if (identityNumber == null || identityNumber == "") {
				throw new MissingAtributeException("Falta atributo requerido - identificación");
			}

			if (dateOfBirth == null) {
				throw new MissingAtributeException("Falta atributo requerido - fecha de nacimiento");
			}

			CustomerRepositoryDto newCustomer = new CustomerRepositoryDto(firstName, secondName, firstLastname,
					secondLastname, identityNumber, dateOfBirth);

			Customer customer = this.customerRepository.save(newCustomer);

			if (customer == null) {
				throw new Exception("No fue posible crear el cliente");
			}

			return this.customerControllerMapper.mapperT2T1(this.customerRepositoryMapper.mapperT2T1(customer));

		} catch (DateTimeParseException e) {
			throw new Exception("Formato invalido para fecha");
		}

	}

	public List<CustomerControllerDto> list() {
		if (this.customerRepository.list() == null) {

		}

		List<Customer> customers = this.customerRepository.list();
		return this.customerControllerMapper.MapperT2T1(this.customerRepositoryMapper.MapperT2T1(customers));
	}

	// TODO: MAPPER
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

	public CustomerRepositoryDto findById(String id) throws NotFoundCustomerException {

		if (id == null) {
			return null;
		}

		Customer customer = (Customer) this.customerRepository.findById(id);

		if (customer == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con el id indicado");
		}

		return this.customerRepositoryMapper.mapperT2T1(customer);
	}

	// TODO: MAPPER
	public boolean delete(String identityNumber) throws NotFoundCustomerException {

		if (identityNumber == null) {
			return false;
		}

		Customer oldCustomer = (Customer) this.findByIdentity(identityNumber);
		boolean ok = this.customerRepository.delete(identityNumber);
		if (!ok) {
			return false;
		}
		return true;
	}

	// TODO: MAPPER
	public Customer update(String identityNumber, Object modifiedCustomer)
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
			newCustomer.setFirstName(oldCustomer.getFirstName());
		}

		if (newCustomer.getSecondName() == null || newCustomer.getSecondName() == "") {
			newCustomer.setSecondName(oldCustomer.getSecondName());
		}

		if (newCustomer.getFirstLastname() == null || newCustomer.getFirstLastname() == "") {
			newCustomer.setFirstLastname(oldCustomer.getFirstLastname());
		}

		if (newCustomer.getSecondLastname() == null || newCustomer.getSecondLastname() == "") {
			newCustomer.setSecondLastname(oldCustomer.getSecondLastname());
		}

		if (newCustomer.getDateOfBirth() == null) {
			newCustomer.setDateOfBirth(oldCustomer.getDateOfBirth());

		}

		boolean ok = this.customerRepository.update(identityNumber, oldCustomer, modifiedCustomer);
		if (!ok) {
			return null;
		}

		return this.findByIdentity(identityNumber);
	}
}
