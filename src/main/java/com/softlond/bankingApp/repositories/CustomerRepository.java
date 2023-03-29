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

public class CustomerRepository implements IRepository {
	private String connectionString;

	public CustomerRepository() {
		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());
			this.connectionString = "jdbc:sqlite:pruebas.db";
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
			String sentenciaSql = "INSERT INTO customers (firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth) "
					+ " VALUES('" + accountOwner.getFirstName() + "', '" + accountOwner.getSecondName() + "', '"
					+ accountOwner.getFirstLastname() + "', '" + accountOwner.getSecondLastName() + "', '"
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
	public void update(String identityNumber, Object object) {
		//TODO: other attributes are missing
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			Customer cust = (Customer) object;
			String sqlSentence = "UPDATE customers \n"
					+ "SET	firstName = '" + cust.getFirstName() +"'\n"
					+ "WHERE identityNumber = '" + identityNumber + "';";
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
			if (queryResult != null && queryResult.next()) {
				Customer customer = null;
				String firstName = queryResult.getString("firstName");
				String secondName = queryResult.getString("secondName");
				int firstLastname = queryResult.getInt("firstLastname");
				String secondLastname = queryResult.getString("secondLastname");
				String identityNumber = queryResult.getString("identityNumber");
				LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));

				customer = new Customer(firstName, secondName, secondLastname, secondLastname, identityNumber,
						dateOfBirth);
				return customer;
			}

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
		}
		return null;
	}

}
