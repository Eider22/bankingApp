package com.softlond.bankingApp.repositories;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Customer;
import com.softlond.bankingApp.exceptions.NotFoundCustomerException;
import com.softlond.bankingApp.repositories.contracts.ICustomerRepository;
import com.softlond.bankingApp.repositories.dtos.CustomerRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.CustomerRepositoryMapper;

public class CustomerRepository implements ICustomerRepository {
	private String connectionString;

	public CustomerRepository() {
		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());
			this.connectionString = "jdbc:sqlite:bankingDB.db";

			createTable();
		} catch (SQLException e) {
			System.err.println("Error de conexión con la base de datos: " + e);
		}

	}

	private void createTable() {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			
			String sql = "CREATE TABLE IF NOT EXISTS customers (\n" + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ " firstName TEXT NOT NULL,\n" + " secondName TEXT NULL,\n" + " firstLastname TEXT NOT NULL,\n"
					+ " identityNumber TEXT NOT NULL UNIQUE,\n" + " secondLastname TEXT NULL,\n"
					+ " dateOfBirth DATE NOT NULL" + ");";

			Statement sentence = connection.createStatement();
			sentence.execute(sql);
		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		}
	}

	@Override
	public Customer save(CustomerRepositoryDto object) throws SQLException {
		System.out.println("Entró a save en repository");
		CustomerRepositoryMapper customerMapper = new CustomerRepositoryMapper();

		Customer newCustomer = customerMapper.mapperT1T2WithoutId(object);

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			if (newCustomer.getFirstName() == null || newCustomer.getFirstName() == ""
					|| newCustomer.getFirstLastname() == null || newCustomer.getFirstLastname() == ""
					|| newCustomer.getIdentityNumber() == null || newCustomer.getIdentityNumber() == ""
					|| newCustomer.getDateOfBirth() == null) {
				return null;
			}

			String sentenceSql = "INSERT INTO customers (firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth) "
					+ " VALUES('" + newCustomer.getFirstName() + "', '" + newCustomer.getSecondName() + "', '"
					+ newCustomer.getFirstLastname() + "', '" + newCustomer.getSecondLastname() + "', '"
					+ newCustomer.getIdentityNumber() + "','" + newCustomer.getDateOfBirth() + "');";
			Statement sentence = connection.createStatement();
			sentence.execute(sentenceSql);
			return this.findByIdentity(newCustomer.getIdentityNumber());
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			throw e;
		}

	}

	@Override
	public List<Customer> list() throws SQLException {
		List<Customer> customers = new ArrayList<Customer>();

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM customers";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult != null) {
				while (queryResult.next()) {
					Customer customer = null;
					String id = queryResult.getString("id");
					String firstName = queryResult.getString("firstName");
					String secondName = queryResult.getString("secondName");
					String firstLastname = queryResult.getString("firstLastname");
					String secondLastname = queryResult.getString("secondLastname");
					String identityNumber = queryResult.getString("identityNumber");
					LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

					customer = new Customer(id, firstName, secondName, firstLastname, secondLastname, identityNumber,
							dateOfBirth);
					customers.add(customer);
				}
				return customers;
			}
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			throw e;
		}
		return null;

	}

	@Override
	public Customer update(Integer id, CustomerRepositoryDto old, CustomerRepositoryDto modified) throws SQLException {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			CustomerRepositoryMapper customerMapper = new CustomerRepositoryMapper();

			Customer oldCustomer = customerMapper.mapperT1T2WithoutId(old);
			Customer modifiedCustomer = customerMapper.mapperT1T2WithoutId(modified);

			String query = "";
			if (modifiedCustomer.equals(oldCustomer)) {
				return null;
			}
			if (modifiedCustomer.getFirstName() != null
					&& !modifiedCustomer.getFirstName().equals(oldCustomer.getFirstName())) {
				query += "firstName = '" + modifiedCustomer.getFirstName() + "',";
			}

			if (modifiedCustomer.getSecondName() == null || oldCustomer.getSecondName() == null) {
				query += "secondName = '" + modifiedCustomer.getSecondName() + "',";
			} else if (!(modifiedCustomer.getSecondName() == null || oldCustomer.getSecondName() == null)
					&& !modifiedCustomer.getSecondName().equals(oldCustomer.getSecondName())) {
				query += "secondName = '" + modifiedCustomer.getSecondName() + "',";
			}

			if (modifiedCustomer.getFirstLastname() != null
					&& !(modifiedCustomer.getFirstLastname().equals(oldCustomer.getFirstLastname()))) {
				query += "firstLastname = '" + modifiedCustomer.getFirstLastname() + "',";
			}

			if (modifiedCustomer.getSecondLastname() == null || oldCustomer.getSecondLastname() == null) {
				query += "secondLastname = '" + modifiedCustomer.getSecondLastname() + "',";
			} else if (!modifiedCustomer.getSecondLastname().equals(oldCustomer.getSecondLastname())) {
				query += "secondLastname = '" + modifiedCustomer.getSecondLastname() + "',";
			}
			if (modifiedCustomer.getDateOfBirth() != null
					&& !modifiedCustomer.getDateOfBirth().equals(oldCustomer.getDateOfBirth())) {
				query += "dateOfBirth = '" + modifiedCustomer.getDateOfBirth() + "',";
			}

			if (query.length() == 0) {
				return null;
			}
			query = query.substring(0, query.length() - 1);
			String sqlSentence = "UPDATE customers \n" + "SET " + query + "WHERE id = '" + id + "';";
			Statement sentence = connection.createStatement();
			sentence.execute(sqlSentence);
			return this.findById(id);
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			throw e;
		}
	}

	@Override
	public boolean delete(Integer id) {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "DELETE FROM customers WHERE id = '" + id + "';";
			Statement sentence = connection.createStatement();
			sentence.execute(sqlSentence);
			return true;
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}
		return false;
	}

	@Override
	public Customer findById(Integer id) throws SQLException {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM customers WHERE id = ?";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			sentence.setInt(1, id);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult == null || !queryResult.next()) {
				return null;
			}

			Customer customer = null;
			String ID = queryResult.getString("id");
			String firstName = queryResult.getString("firstName");
			String secondName = queryResult.getString("secondName");
			String firstLastname = queryResult.getString("firstLastname");
			String secondLastname = queryResult.getString("secondLastname");
			String identityNumber = queryResult.getString("identityNumber");
			LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

			customer = new Customer(ID, firstName, secondName, firstLastname, secondLastname, identityNumber,
					dateOfBirth);
			return customer;

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		}
	}

	@Override
	public Customer findByIdentity(String identifyNumber) throws SQLException {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM customers WHERE identityNumber = ?";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			sentence.setString(1, identifyNumber);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult == null || !queryResult.next()) {
				return null;
			}

			Customer customer = null;
			String id = queryResult.getString("id");
			String firstName = queryResult.getString("firstName");
			String secondName = queryResult.getString("secondName");
			String firstLastname = queryResult.getString("firstLastname");
			String secondLastname = queryResult.getString("secondLastname");
			String identityNumber = queryResult.getString("identityNumber");
			LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

			customer = new Customer(id, firstName, secondName, firstLastname, secondLastname, identityNumber,
					dateOfBirth);
			return customer;

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		}
	}

}
