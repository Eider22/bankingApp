package com.softlond.bankingApp.repositories.contracts;

import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;

public interface IRepository {
	public Customer save(Object object)throws Exception;

	public boolean delete(String identifier)throws Exception;

	public boolean update(String identifyNumber, Object oldObject, Object modifiedObject) throws Exception;

	public Object find(String identifier) throws Exception;

	public List<?> list() throws Exception;
}
