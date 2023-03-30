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
import com.softlond.bankingApp.repositories.contracts.IRepository;

public class CustomerRepository implements IRepository {
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
	public void save(Object object) {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			Customer accountOwner = (Customer) object;

			if (accountOwner.getFirstName() == null || accountOwner.getFirstLastname() == null) {
				return;
			}

			String sentenciaSql = "INSERT INTO customers (firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth) "
					+ " VALUES('" + accountOwner.getFirstName() + "', '" + accountOwner.getSecondName() + "', '"
					+ accountOwner.getFirstLastname() + "', '" + accountOwner.getSecondLastname() + "', '"
					+ accountOwner.getIdentityNumber() + "','" + accountOwner.getDateOfBirth() + "');";
			Statement sentencia = connection.createStatement();
			sentencia.execute(sentenciaSql);
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}

	}

	@Override
	public List<?> list() {
		List<Customer> costumers = new ArrayList<Customer>();

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM customers";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult != null) {
				while (queryResult.next()) {
					Customer costumer = null;
					String firstName = queryResult.getString("firstName");
					String secondName = queryResult.getString("secondName");
					String firstLastname = queryResult.getString("firstLastname");
					String secondLastname = queryResult.getString("secondLastname");
					String identityNumber = queryResult.getString("identityNumber");
					LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

					costumer = new Customer(firstName, secondName, firstLastname, secondLastname, identityNumber,
							dateOfBirth);
					costumers.add(costumer);
				}
				return costumers;
			}
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		}
		return null;

	}

	@Override
	public void update(String identityNumber, Object old, Object modified) {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			Customer oldCustomer = (Customer) old;
			Customer modifiedCustomer = (Customer) modified;

			String query = "";
			if (modifiedCustomer.equals(oldCustomer)) {
				return;
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
				return;
			}
			query = query.substring(0, query.length() - 1);
			String sqlSentence = "UPDATE customers \n" + "SET " + query + "WHERE identityNumber = '" + identityNumber
					+ "';";
			Statement sentence = connection.createStatement();
			sentence.execute(sqlSentence);
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}
	}

	@Override
	public void delete(String identityNumber) {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "DELETE FROM customers WHERE identityNumber = '" + identityNumber + "';";
			Statement sentence = connection.createStatement();
			sentence.execute(sqlSentence);
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
		}
	}

	@Override
	public Object find(String identifyNumber) {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM customers WHERE identityNumber = ?";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			sentence.setString(1, identifyNumber);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult == null || !queryResult.next()) {
				return null;
			}

			Customer customer = null;
			String firstName = queryResult.getString("firstName");
			String secondName = queryResult.getString("secondName");
			String firstLastname = queryResult.getString("firstLastname");
			String secondLastname = queryResult.getString("secondLastname");
			String identityNumber = queryResult.getString("identityNumber");
			LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

			customer = new Customer(firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth);
			return customer;

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		}
		return null;
	}

}
