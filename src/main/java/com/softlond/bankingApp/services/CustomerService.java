package com.softlond.bankingApp.services;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;
import com.softlond.bankingApp.controllers.mappers.CustomerControllerMapper;
import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.MissingAtributeException;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.CustomerRepository;
import com.softlond.bankingApp.repositories.contracts.ICustomerRepository;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.CustomerRepositoryMapper;
import com.softlond.bankingApp.services.contracts.ICustomerService;

public class CustomerService implements ICustomerService{
	private ICustomerRepository customerRepository;
	private CustomerRepositoryMapper customerRepositoryMapper;
	private CustomerControllerMapper customerControllerMapper;

	public CustomerService() {
		this.customerRepository = new CustomerRepository();
		this.customerRepositoryMapper = new CustomerRepositoryMapper();
		this.customerControllerMapper = new CustomerControllerMapper();
	}

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
		} catch (SQLException e) {
			if (e.getErrorCode() == 19) {
				throw new Exception("El cliente con ese número de cédula ya existe");
			}
			throw new Exception("No fue posible crear el cliente");
		}

	}

	public List<CustomerControllerDto> list() throws Exception {
		if (this.customerRepository.list() == null) {

		}

		List<Customer> customers = this.customerRepository.list();
		return this.customerControllerMapper.MapperT2T1(this.customerRepositoryMapper.MapperT2T1(customers));
	}

	public CustomerControllerDto findByIdentity(String identityNumber) throws Exception {

		if (identityNumber == null  || identityNumber == "") {
			throw new NotFoundCustomerException("Debe enviar la identificaión del cliente que desea obtener");
		}

		Customer customer = this.customerRepository.findByIdentity(identityNumber);

		if (customer == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con la identificación indicada");
		}

		return this.customerControllerMapper.mapperT2T1(this.customerRepositoryMapper.mapperT2T1(customer));

	}

	public CustomerControllerDto findById(String id) throws Exception {

		if (id == null  || id == "") {
			throw new NotFoundCustomerException("Debe enviar el id del cliente que desea obtener");
		}

		Customer customer = this.customerRepository.findById(id);

		if (customer == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con el id indicado");
		}

		return this.customerControllerMapper.mapperT2T1(this.customerRepositoryMapper.mapperT2T1(customer));
	}

	public boolean delete(String id) throws Exception {

		if (id == null  || id == "") {
			throw new NotFoundCustomerException("Debe enviar el id del cliente que desea eliminar");
		}

		CustomerControllerDto customer = this.findById(id);

		if (customer == null) {
			throw new NotFoundCustomerException("No se encontró el cliente con el id indicado");
		}

		boolean ok = this.customerRepository.delete(id);
		if (!ok) {
			return false;
		}
		return true;
	}

	public CustomerControllerDto update(String id, Map customerMap) throws Exception {

		if (id == null || id == "") {
			throw new MissingAtributeException("Debe ingresar la identificación del cliente que desea editar");
		}

		if (customerMap == null) {
			throw new MissingAtributeException("Debe ingresar los datos para ser modificados en el cliente");
		}

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

		CustomerControllerDto findOldCustomer = this.findById(id);

		CustomerRepositoryDto oldCostumer = this.customerControllerMapper.mapperT1T2(findOldCustomer);

		CustomerRepositoryDto newCustomer = new CustomerRepositoryDto(firstName, secondName, firstLastname,
				secondLastname, identityNumber, dateOfBirth);

		Customer updateCustomer = this.customerRepository.update(id, oldCostumer, newCustomer);
		if (updateCustomer == null) {
			throw new Exception("No editó ningún campo para el cliente");
		}

		return this.customerControllerMapper.mapperT2T1(this.customerRepositoryMapper.mapperT2T1(updateCustomer));
	}
}
