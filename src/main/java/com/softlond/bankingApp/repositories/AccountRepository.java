package com.softlond.bankingApp.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.repositories.contracts.IAccountRepository;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.AccountRepositoryMapper;

public class AccountRepository implements IAccountRepository {
	private String connectionString;

	public AccountRepository() {
		try {
//			DriverManager.registerDriver(new org.sqlite.JDBC());
			this.connectionString = "jdbc:sqlite:bankingDB.db";

			createTable();
		} catch (Exception e) {
			System.err.println("Error de conexión con la base de datos: " + e);
		}

	}

	private void createTable() {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			String sql = "CREATE TABLE IF NOT EXISTS accounts(ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "accountNumber TEXT NOT NULL UNIQUE,\n" + "balance REAL NOT NULL,\n"
					+ "accountType TEXT NOT NULL,\n" + "customerId INTEGER NOT NULL"
//					+ "CONSTRAINT  fk_customer FOREIGN KEY(customerId) REFERENCES customers(id) ON DELETE CASCADE"
					+ ");";

			Statement sentence = connection.createStatement();
			sentence.execute(sql);

		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		}
	}

	@Override
	public Account save(AccountRepositoryDto account) throws SQLException {
		AccountRepositoryMapper accountMapper = new AccountRepositoryMapper();

		Account newAccount = accountMapper.mapperT1T2WithoutId(account);

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			if (newAccount.getAccountNumber() == null || newAccount.getAccountNumber() == ""
					|| newAccount.getAccountType() == null || newAccount.getAccountType() == "") {
				return null;
			}

			String sentenceSql = "INSERT INTO accounts (accountNumber, balance, accountType, customerId) " + " VALUES('"
					+ newAccount.getAccountNumber() + "', '" + newAccount.getBalance() + "', '"
					+ newAccount.getAccountType() + "', '" + newAccount.getCustomer() + "');";

			Statement sentence = connection.createStatement();
			sentence.execute(sentenceSql);

			Statement stmt = connection.createStatement();
			String configureFk = "PRAGMA foreign_keys=off;\n" + "ALTER TABLE accounts RENAME TO _accounts_old;\n"
					+ "CREATE TABLE accounts(ID INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "accountNumber TEXT NOT NULL UNIQUE,\n" + "balance REAL NOT NULL,\n"
					+ "accountType TEXT NOT NULL,\n" + "customerId INTEGER NOT NULL,\n"
					+ "CONSTRAINT  fk_customer FOREIGN KEY(customerId) REFERENCES customers(id) ON DELETE CASCADE);\n"
					+ "INSERT INTO accounts SELECT * FROM _accounts_old);\n"
					+ "INSERT INTO accounts SELECT * FROM _accounts_old;\n" 
					+ "COMMIT;\n" + "PRAGMA foreign_keys=on;";

			stmt.execute(configureFk);
			return findByAccountNumber(newAccount.getAccountNumber());
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			throw e;
		}

	}

//	@Override
//	public List<Account> list() throws SQLException {
//		List<Account> accounts = new ArrayList<Account>();
//
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//			String sqlSentence = "SELECT * FROM accounts";
//			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
//			ResultSet queryResult = sentence.executeQuery();
//
//			if (queryResult != null) {
//				while (queryResult.next()) {
//					Account account = null;
//					String id = queryResult.getString("id");
//					String firstName = queryResult.getString("firstName");
//					String secondName = queryResult.getString("secondName");
//					String firstLastname = queryResult.getString("firstLastname");
//					String secondLastname = queryResult.getString("secondLastname");
//					String identityNumber = queryResult.getString("identityNumber");
//					LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));
//
//					account = new Account(id, firstName, secondName, firstLastname, secondLastname, identityNumber,
//							dateOfBirth);
//					accounts.add(account);
//				}
//				return accounts;
//			}
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//			throw e;
//		} catch (Exception e) {
//			System.err.println("Error " + e.getMessage());
//			throw e;
//		}
//		return null;
//
//	}
//
//	@Override
//	public Account update(String id, AccountRepositoryDto old, AccountRepositoryDto modified) throws SQLException {
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//
//			AccountRepositoryMapper accountMapper = new AccountRepositoryMapper();
//
//			Account oldAccount = accountMapper.mapperT1T2WithoutId(old);
//			Account modifiedAccount = accountMapper.mapperT1T2WithoutId(modified);
//
//			String query = "";
//			if (modifiedAccount.equals(oldAccount)) {
//				return null;
//			}
//			if (modifiedAccount.getFirstName() != null
//					&& !modifiedAccount.getFirstName().equals(oldAccount.getFirstName())) {
//				query += "firstName = '" + modifiedAccount.getFirstName() + "',";
//			}
//
//			if (modifiedAccount.getSecondName() == null || oldAccount.getSecondName() == null) {
//				query += "secondName = '" + modifiedAccount.getSecondName() + "',";
//			} else if (!(modifiedAccount.getSecondName() == null || oldAccount.getSecondName() == null)
//					&& !modifiedAccount.getSecondName().equals(oldAccount.getSecondName())) {
//				query += "secondName = '" + modifiedAccount.getSecondName() + "',";
//			}
//
//			if (modifiedAccount.getFirstLastname() != null
//					&& !(modifiedAccount.getFirstLastname().equals(oldAccount.getFirstLastname()))) {
//				query += "firstLastname = '" + modifiedAccount.getFirstLastname() + "',";
//			}
//
//			if (modifiedAccount.getSecondLastname() == null || oldAccount.getSecondLastname() == null) {
//				query += "secondLastname = '" + modifiedAccount.getSecondLastname() + "',";
//			} else if (!modifiedAccount.getSecondLastname().equals(oldAccount.getSecondLastname())) {
//				query += "secondLastname = '" + modifiedAccount.getSecondLastname() + "',";
//			}
//			if (modifiedAccount.getDateOfBirth() != null
//					&& !modifiedAccount.getDateOfBirth().equals(oldAccount.getDateOfBirth())) {
//				query += "dateOfBirth = '" + modifiedAccount.getDateOfBirth() + "',";
//			}
//
//			if (query.length() == 0) {
//				return null;
//			}
//			query = query.substring(0, query.length() - 1);
//			String sqlSentence = "UPDATE accounts \n" + "SET " + query + "WHERE id = '" + id + "';";
//			Statement sentence = connection.createStatement();
//			sentence.execute(sqlSentence);
//			return this.findById(id);
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//			throw e;
//		} catch (Exception e) {
//			System.err.println("Error " + e.getMessage());
//			throw e;
//		}
//	}
//
//	@Override
//	public boolean delete(String id) {
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//			String sqlSentence = "DELETE FROM accounts WHERE id = '" + id + "';";
//			Statement sentence = connection.createStatement();
//			sentence.execute(sqlSentence);
//			return true;
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//		} catch (Exception e) {
//			System.err.println("Error " + e.getMessage());
//		}
//		return false;
//	}
//
//	@Override
//	public Account findById(String id) throws SQLException {
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//			String sqlSentence = "SELECT * FROM accounts WHERE id = ?";
//			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
//			sentence.setString(1, id);
//			ResultSet queryResult = sentence.executeQuery();
//
//			if (queryResult == null || !queryResult.next()) {
//				return null;
//			}
//
//			Account account = null;
//			String ID = queryResult.getString("id");
//			String firstName = queryResult.getString("firstName");
//			String secondName = queryResult.getString("secondName");
//			String firstLastname = queryResult.getString("firstLastname");
//			String secondLastname = queryResult.getString("secondLastname");
//			String identityNumber = queryResult.getString("identityNumber");
//			LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));
//
//			account = new Account(ID, firstName, secondName, firstLastname, secondLastname, identityNumber,
//					dateOfBirth);
//			return account;
//
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//			throw e;
//		} catch (Exception e) {
//			System.err.println("Error de conexión: " + e);
//			throw e;
//		}
//	}
//

	public Account findByAccountNumber(String accountNum) throws SQLException {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			String sqlSentence = "SELECT * FROM accounts WHERE accountNumber = ?";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			sentence.setString(1, accountNum);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult == null || !queryResult.next()) {
				return null;
			}

			Account account = null;
			String id = queryResult.getString("id");
			String accountNumber = queryResult.getString("accountNumber");
			double balance = queryResult.getDouble("balance");
			int customer = queryResult.getInt("customerId");
			String accountType = queryResult.getString("accountType");

			account = new Account(id, accountNumber, balance, customer, accountType);
			return account;

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		}
	}

	@Override
	public List<Account> listByCustomerId(Integer customerId) throws Exception {
		List<Account> accounts = new ArrayList<Account>();

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
			String sqlSentence = "SELECT * FROM accounts WHERE customerId =" + customerId;
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult != null) {
				while (queryResult.next()) {
					Account account = null;
					String id = queryResult.getString("id");
					String accountNumber = queryResult.getString("accountNumber");
					double balance = queryResult.getDouble("balance");
					int customer = queryResult.getInt("customerId");
					String accountType = queryResult.getString("accountType");

					account = new Account(id, accountNumber, balance, customer, accountType);
					accounts.add(account);
				}
				return accounts;
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
	public boolean delete(String id) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Account> list() throws Exception {
		return null;
	}

	@Override
	public Account findById(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
