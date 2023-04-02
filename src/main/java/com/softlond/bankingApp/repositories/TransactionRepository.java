package com.softlond.bankingApp.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import com.softlond.bankingApp.entities.Account;
import com.softlond.bankingApp.entities.Transaction;
import com.softlond.bankingApp.repositories.contracts.ITransactionrepository;
import com.softlond.bankingApp.repositories.dtos.AccountRepositoryDto;
import com.softlond.bankingApp.repositories.dtos.TransactionRepositoryDto;
import com.softlond.bankingApp.repositories.mappers.AccountRepositoryMapper;
import com.softlond.bankingApp.repositories.mappers.TransactionRepositoryMapper;

public class TransactionRepository implements ITransactionrepository {
	private String connectionString;

	public TransactionRepository() {
		try {
			DriverManager.registerDriver(new org.sqlite.JDBC());
			this.connectionString = "jdbc:sqlite:bankingDB.db";

			createTable();
		} catch (Exception e) {
			System.err.println("Error de conexión con la base de datos: " + e);
		}

	}

	private void createTable() {
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			String sql = "CREATE TABLE IF NOT EXISTS transactions(id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
					+ "date DATE NOT NULL,\n" + "hour TIME NOT NULL,\n" + "transactionType TEXT NOT NULL,\n"
					+ "amount REAL NOT NULL,\n" + "targetAccountType TEXT NULL,\n"
					+ "accountId INTEGER NOT NULL,\n"
					+ "CONSTRAINT  fk_accoutn FOREIGN KEY(accountId) REFERENCES accounts(id) ON DELETE CASCADE" + ");";

			Statement sentence = connection.createStatement();
			sentence.execute(sql);

		} catch (SQLException e) {
			System.out.println("Error de conexión: " + e.getMessage());
		}
	}

	@Override
	public TransactionRepositoryDto save(TransactionRepositoryDto transaction) throws SQLException {
		TransactionRepositoryMapper transactionMapper = new TransactionRepositoryMapper();

		Transaction newTransaction = transactionMapper.mapperT1T2WithoutId(transaction);

		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			if (newTransaction.getDate() == null || newTransaction.getHour() == null
					|| newTransaction.getTransactionType() == null || newTransaction.getAmount() == null
					|| newTransaction.getIdAccount() == null) {
				return null;
			}

			String sentenceSql = "INSERT INTO transactions (date, hour, transactionType, amount,targetAccountType, accountId) "
					+ " VALUES('" + newTransaction.getDate() + "', '" + newTransaction.getHour() + "', '"
					+ newTransaction.getTransactionType() + "', '" + newTransaction.getAmount() + "','"
					+ newTransaction.getTargetAccountType() + "','" + newTransaction.getIdAccount() + "');";

			Statement sentence = connection.createStatement();
			sentence.execute(sentenceSql);
			
			System.out.println("----->" + newTransaction.getDate());

			return transactionMapper.mapperT2T1WithoutId(newTransaction);
		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error " + e.getMessage());
			throw e;
		}

	}

	@Override
	public List<Transaction> listByAccountId(Integer idAccount) throws Exception {		
		List<Transaction> transactions = new ArrayList<Transaction>();
		try (Connection connection = DriverManager.getConnection(this.connectionString)) {

			String sqlSentence = "SELECT * FROM transactions WHERE accountId = ?";
			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
			sentence.setInt(1, idAccount);
			ResultSet queryResult = sentence.executeQuery();

			if (queryResult != null) {
				while (queryResult.next()) {
					Transaction transaction = null;
					String id = queryResult.getString("id");
					LocalDate date = LocalDate.parse(queryResult.getString("date"));
					String transactionType = queryResult.getString("transactionType");
					LocalTime hour = LocalTime.parse(queryResult.getString("hour"));
					double amount = queryResult.getDouble("amount");
					String targetAccountType = queryResult.getString("targetAccountType");
					String accountId = queryResult.getString("accountId");

					transaction = new Transaction(date, hour, transactionType, amount, accountId, targetAccountType);
					transactions.add(transaction);
				}
				return transactions;

			}

		} catch (SQLException e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		} catch (Exception e) {
			System.err.println("Error de conexión: " + e);
			throw e;
		}
		return transactions;

	}
}