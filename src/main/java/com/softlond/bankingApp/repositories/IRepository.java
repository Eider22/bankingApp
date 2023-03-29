package com.softlond.bankingApp.repositories;

import java.util.List;

public interface IRepository {
	public void save(Object object);

	public void delete(String identifier);

	public void update(String identifyNumber,Object oldObject, Object modifiedObject);

	public Object find(String identifier);

	public List<?> list();
}
