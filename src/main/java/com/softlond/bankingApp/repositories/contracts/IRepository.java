package com.softlond.bankingApp.repositories.contracts;

import java.util.List;

import com.softlond.bankingApp.exceptions.NotFoundCustomerException;

public interface IRepository {
	public void save(Object object)throws Exception;

	public void delete(String identifier)throws Exception;

	public void update(String identifyNumber, Object oldObject, Object modifiedObject) throws Exception;

	public Object find(String identifier) throws Exception;

	public List<?> list() throws Exception;
}
