package com.softlond.bankingApp.services.contracts;

import java.util.List;
import java.util.Map;

import com.softlond.bankingApp.controllers.dtos.CustomerControllerDto;

public interface ICustomerService {
	public CustomerControllerDto save(Map customerMap) throws Exception;

	public List<CustomerControllerDto> list() throws Exception;

	public CustomerControllerDto findByIdentity(String identityNumber) throws Exception;

	public CustomerControllerDto findById(String id) throws Exception;

	public boolean delete(String id) throws Exception;

	public CustomerControllerDto update(String id, Map customerMap) throws Exception;
}
