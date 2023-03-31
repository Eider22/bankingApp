//package com.softlond.bankingApp.repositories;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.time.LocalDate;
//import java.util.ArrayList;
//import java.util.List;
//
//import com.softlond.bankingApp.entities.SavingsAccount;
//import com.softlond.bankingApp.repositories.contracts.IRepository;
//
//public class SavingsAccountRepository implements IRepository {
//	private String connectionString;
//
//	public SavingsAccountRepository() {
//		try {
//			DriverManager.registerDriver(new org.sqlite.JDBC());
//			this.connectionString = "jdbc:sqlite:bankingDB.db";
//			createTable();
//		} catch (SQLException e) {
//			System.err.println("Error de conexión con la base de datos: " + e);
//		}
//
//	}
//
//	private void createTable() {
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//
//			String sql = "CREATE TABLE IF NOT EXISTS savingsAccounts (\n" + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
//					+ " accountNumber TEXT NOT NULL,\n" + "balance DOUBLE NOT NULL DEDAULT 0,\n"
//					+ " withdrawalsQuantity INT NOT NULL DEDAULT 0,\n" + " depositsQuantity INT NOT NULL DEFAULT 0 "
//					+ ");";
//
//			Statement sentence = connection.createStatement();
//			sentence.execute(sql);
//
//		} catch (SQLException e) {
//			System.out.println("Error de conexión: " + e.getMessage());
//		}
//	}
//
//	@Override
//	public boolean save(Object object) {
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//			SavingsAccount savingsAccount = (SavingsAccount) object;
//
//			if (savingsAccount.getFirstName() == null || savingsAccount.getFirstName() == ""
//					|| savingsAccount.getFirstLastname() == null || savingsAccount.getFirstLastname() == ""
//					|| savingsAccount.getIdentityNumber() == null || savingsAccount.getIdentityNumber() == ""
//					|| savingsAccount.getDateOfBirth() == null) {
//				return false;
//			}
//
//			String sentenciaSql = "INSERT INTO savingsAccounts (firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth) "
//					+ " VALUES('" + savingsAccount.getFirstName() + "', '" + savingsAccount.getSecondName() + "', '"
//					+ savingsAccount.getFirstLastname() + "', '" + savingsAccount.getSecondLastname() + "', '"
//					+ savingsAccount.getIdentityNumber() + "','" + savingsAccount.getDateOfBirth() + "');";
//			Statement sentencia = connection.createStatement();
//			sentencia.execute(sentenciaSql);
//			return true;
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//		} catch (Exception e) {
//			System.err.println("Error " + e.getMessage());
//		}
//		return false;
//
//	}
//
//	@Override
//	public List<?> list() {
//		List<SavingsAccount> costumers = new ArrayList<SavingsAccount>();
//
//		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
//			String sqlSentence = "SELECT * FROM customers";
//			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
//			ResultSet queryResult = sentence.executeQuery();
//
//			if (queryResult != null) {
//				while (queryResult.next()) {
//					SavingsAccount costumer = null;
//					String firstName = queryResult.getString("firstName");
//					String secondName = queryResult.getString("secondName");
//					String firstLastname = queryResult.getString("firstLastname");
//					String secondLastname = queryResult.getString("secondLastname");
//					String identityNumber = queryResult.getString("identityNumber");
//					LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));
//
//					costumer = new SavingsAccount(firstName, secondName, firstLastname, secondLastname, identityNumber,
//							dateOfBirth);
//					costumers.add(costumer);
//				}
//				return costumers;
//			}
//		} catch (SQLException e) {
//			System.err.println("Error de conexión: " + e);
//		}
//		return null;
//
//	}
//
//	@Override
//	public void update(String identityNumber, Object old, Object modified) {
////		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
////			SavingsAccount oldSavingsAccount = (SavingsAccount) old;
////			SavingsAccount modifiedSavingsAccount = (SavingsAccount) modified;
////
////			String query = "";
////			if (modifiedSavingsAccount.equals(oldSavingsAccount)) {
////				return;
////			}
////			if (modifiedSavingsAccount.getFirstName() != null
////					&& !modifiedSavingsAccount.getFirstName().equals(oldSavingsAccount.getFirstName())) {
////				query += "firstName = '" + modifiedSavingsAccount.getFirstName() + "',";
////			}
////
////			if (modifiedSavingsAccount.getSecondName() == null || oldSavingsAccount.getSecondName() == null) {
////				query += "secondName = '" + modifiedSavingsAccount.getSecondName() + "',";
////			} else if (!(modifiedSavingsAccount.getSecondName() == null || oldSavingsAccount.getSecondName() == null)
////					&& !modifiedSavingsAccount.getSecondName().equals(oldSavingsAccount.getSecondName())) {
////				query += "secondName = '" + modifiedSavingsAccount.getSecondName() + "',";
////			}
////
////			if (modifiedSavingsAccount.getFirstLastname() != null
////					&& !(modifiedSavingsAccount.getFirstLastname().equals(oldSavingsAccount.getFirstLastname()))) {
////				query += "firstLastname = '" + modifiedSavingsAccount.getFirstLastname() + "',";
////			}
////
////			if (modifiedSavingsAccount.getSecondLastname() == null || oldSavingsAccount.getSecondLastname() == null) {
////				query += "secondLastname = '" + modifiedSavingsAccount.getSecondLastname() + "',";
////			} else if (!modifiedSavingsAccount.getSecondLastname().equals(oldSavingsAccount.getSecondLastname())) {
////				query += "secondLastname = '" + modifiedSavingsAccount.getSecondLastname() + "',";
////			}
////			if (modifiedSavingsAccount.getDateOfBirth() != null
////					&& !modifiedSavingsAccount.getDateOfBirth().equals(oldSavingsAccount.getDateOfBirth())) {
////				query += "dateOfBirth = '" + modifiedSavingsAccount.getDateOfBirth() + "',";
////			}
////
////			if (query.length() == 0) {
////				return;
////			}
////			query = query.substring(0, query.length() - 1);
////			String sqlSentence = "UPDATE customers \n" + "SET " + query + "WHERE identityNumber = '" + identityNumber
////					+ "';";
////			Statement sentence = connection.createStatement();
////			sentence.execute(sqlSentence);
////		} catch (SQLException e) {
////			System.err.println("Error de conexión: " + e);
////		} catch (Exception e) {
////			System.err.println("Error " + e.getMessage());
////		}
//	}
//
//	@Override
//	public void delete(String identityNumber) {
////		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
////			String sqlSentence = "DELETE FROM customers WHERE identityNumber = '" + identityNumber + "';";
////			Statement sentence = connection.createStatement();
////			sentence.execute(sqlSentence);
////		} catch (SQLException e) {
////			System.err.println("Error de conexión: " + e);
////		} catch (Exception e) {
////			System.err.println("Error " + e.getMessage());
////		}
//	}
//
//	@Override
//	public Object find(String identifyNumber) {
////		try (Connection connection = DriverManager.getConnection(this.connectionString)) {
////			String sqlSentence = "SELECT * FROM customers WHERE identityNumber = ?";
////			PreparedStatement sentence = connection.prepareStatement(sqlSentence);
////			sentence.setString(1, identifyNumber);
////			ResultSet queryResult = sentence.executeQuery();
////
////			if (queryResult == null || !queryResult.next()) {
////				return null;
////			}
////
////			SavingsAccount customer = null;
////			String firstName = queryResult.getString("firstName");
////			String secondName = queryResult.getString("secondName");
////			String firstLastname = queryResult.getString("firstLastname");
////			String secondLastname = queryResult.getString("secondLastname");
////			String identityNumber = queryResult.getString("identityNumber");
////			LocalDate dateOfBirth = LocalDate.parse(queryResult.getString("dateOfBirth"));
////
////			customer = new SavingsAccount(firstName, secondName, firstLastname, secondLastname, identityNumber, dateOfBirth);
////			return customer;
////
////		} catch (SQLException e) {
////			System.err.println("Error de conexión: " + e);
////		}
//		return null;
//	}
//}
