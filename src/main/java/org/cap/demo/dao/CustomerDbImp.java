package org.cap.demo.dao;

import java.math.BigDecimal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.model.Account;
import org.cap.demo.model.AccountType;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;
import org.cap.demo.model.TransactionType;

public class CustomerDbImp implements ICustomerDao {
	
	@Override
	public boolean addCustomer(Customer customer) throws DataInsertionException {
		int customerId = 0;
		Connection connection = null;
		try {
			connection = getMySQLConnect();
			String sql = "insert into Customer(firstName,lastName,emailId,password,contactNo) values (?,?,?,?,?)";
			PreparedStatement preparedstatement = connection.prepareStatement(sql,PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedstatement.setString(1, customer.getFirstName());
			preparedstatement.setString(2, customer.getLastName());
			preparedstatement.setString(3, customer.getEmailId());
			preparedstatement.setString(4, customer.getPassword());
			preparedstatement.setString(5, customer.getContactNo());
			
			int count = preparedstatement.executeUpdate();
			ResultSet resultSet = preparedstatement.getGeneratedKeys();
			if(resultSet.next())
				customerId = resultSet.getInt(1);
			
			customer.setCustomerId(customerId);
			
			if(count>0) {
				
				if(saveAddress(customer))
					return true;
				else
					throw new DataInsertionException("Address Insertion error!");
			}else
				throw new DataInsertionException("Customer Insertion error!");
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	
	private boolean saveAddress(Customer customer) {
		try {
			String sql = "insert into Address(addressLine1,addressLine2,city,state,pincode,customerId) values (?,?,?,?,?,?)";
			PreparedStatement preparedstatement = getMySQLConnect().prepareStatement(sql);
			
			preparedstatement.setString(1, customer.getAddress().getAddressLine1());
			preparedstatement.setString(2, customer.getAddress().getAddressLine2());
			preparedstatement.setString(3, customer.getAddress().getCity());
			preparedstatement.setString(4, customer.getAddress().getState());
			preparedstatement.setString(5, customer.getAddress().getPincode());
			preparedstatement.setInt(6, customer.getCustomerId());
			
			int count = preparedstatement.executeUpdate();
			if(count>0)
				return true;
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return false;
		
	}

	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Customer loginCustomer(String email, String password) {
		Customer customer = null;
		
		String sql = "select * from customer where emailId = ? and password = ?;";
		
		try{
			PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql);
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			ResultSet resultSet = preparedStatement.executeQuery();
			while(resultSet.next()){
				customer = new Customer();
				
				customer.setCustomerId(resultSet.getInt("customerId"));
				customer.setFirstName(resultSet.getString("firstName"));
				customer.setLastName(resultSet.getString("lastName"));
				customer.setEmailId(resultSet.getString("emailId"));
				customer.setPassword(resultSet.getString("password"));
				customer.setContactNo(resultSet.getString("contactNo"));
				customer.setConfirmPassword(resultSet.getString("password"));
				
				
			}
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return customer;
	}

	@Override
	public Account createAccount(Account account, Customer customer) {
//		Account account2 = null;
		int accNo=0;
		
		try {
			String sql = "insert into Account(accountType ,openingDate, balance,description,customerId) values(?,?,?,?,?)";
			PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
			
			preparedStatement.setString(1, account.getAccountType().name());
			preparedStatement.setDate(2, Date.valueOf(account.getOpeningDate()));
			preparedStatement.setBigDecimal(3,BigDecimal.valueOf( account.getBalance()));
			preparedStatement.setString(4, account.getDescription());
			preparedStatement.setInt(5, customer.getCustomerId());
			
			int count = preparedStatement.executeUpdate();
			ResultSet resultSet =  preparedStatement.getGeneratedKeys();
			
			if(resultSet.next()) {
				accNo = resultSet.getInt(1);
			}
			
			if(count>0) {
				account.setAccno(accNo);
				return account;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private Connection getMySQLConnect() {
		
		Connection connection=null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jandb", "root", "2428");
			
		}
		catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
		catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection; 
	}

	@Override
	public List<Account> getAllAccountByCustomer(int customerId,String type) {
		String sql=null;
		List<Account> accounts = new ArrayList();
		if(type.equalsIgnoreCase("fromaccount"))
			sql = "select * from Account where customerId = ?";
		else
			sql = "select * from Account where customerId != ?";
		try {
			PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
			ResultSet resultSet =  preparedStatement.executeQuery();
			
			while(resultSet.next()) {
				Account account = new Account();
				
				account.setAccno(resultSet.getInt("accno"));
				account.setAccountType(findAccountType(resultSet.getString("accountType")));
				account.setBalance(resultSet.getBigDecimal("balance").doubleValue());
				account.setDescription(resultSet.getString("description"));
				account.setOpeningDate(resultSet.getDate("openingDate").toLocalDate());
				
				accounts.add(account);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return accounts;
	}
	
	private AccountType findAccountType(String type) {
		if(type.equalsIgnoreCase("saving"))
			return AccountType.SAVING;
		else if(type.equalsIgnoreCase("current"))
			return AccountType.CURRENT;
		else if(type.equalsIgnoreCase("loan"))
			return AccountType.LOAN;
		else if(type.equalsIgnoreCase("salary"))
			return AccountType.SALARY;
		else 
			return null;
		
	}

	@Override
	public Account depositAndWithdraw(Account account, double amount, String type) throws InvalidAccountNumberException {
		String sql1=null;
		String sql=null;
//		Account account = findAccount(accNo);
		if(account!=null)
		{
			try {
				if(type.equalsIgnoreCase("credit"))
					sql = "update Account set balance = balance+? where accno = ?";
				else
					sql = "update Account set balance = balance-? where accno = ?";
				PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql);
				preparedStatement.setDouble(1, amount);
				preparedStatement.setInt(2, account.getAccno());
				
				int count = preparedStatement.executeUpdate();
				if(count>0)
				{	if(type.equalsIgnoreCase("credit")) {
						account.setBalance(account.getBalance() + amount);
						sql1 = "insert into transaction(transactionType,transactionDateTime,descreption,toAccount,amount) "
								+ "values(?,?,?,?,?)";
					}
					else {
						account.setBalance(account.getBalance() - amount);
						sql1 = "insert into transaction(transactionType,transactionDateTime,descreption,fromAccount,amount) "
								+ "values(?,?,?,?,?)";
					}
				
				
				// adding transcation into transcation table;
				
				
				PreparedStatement preparedStatement2 = getMySQLConnect().prepareStatement(sql1);
				preparedStatement2.setString(1, type);
				preparedStatement2.setObject(2, LocalDateTime.now());
				if(type.equalsIgnoreCase("credit"))
					preparedStatement2.setString(3, "Amount deposited");
				else
					preparedStatement2.setString(3, "Amount withdrawn");
				preparedStatement2.setInt(4, account.getAccno());
				preparedStatement2.setBigDecimal(5, BigDecimal.valueOf(amount));
				
				int count1 = preparedStatement2.executeUpdate();
				if(count>0)
					return account;
				}
				
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}else
			throw new InvalidAccountNumberException("Sorry! Account number is not valid");
		return null;
	}
	
	
	
	public Account findAccount(int accNo) {
		Account account = null;
		try {
			
			String sql = "select * from account where accno = ?";
			PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql);
			preparedStatement.setInt(1, accNo);
			
			ResultSet resultSet =  preparedStatement.executeQuery();
			while(resultSet.next()){
				account = new Account();
				account.setAccno(resultSet.getInt("accno"));
				account.setAccountType(findAccountType(resultSet.getString("accountType")));
				account.setBalance(resultSet.getBigDecimal("balance").doubleValue());
				account.setOpeningDate(resultSet.getDate("openingDate").toLocalDate());
				account.setDescription(resultSet.getString("description"));
				
			}
					
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		return account;
	}

	@Override
	public List<Transaction> getAllTransactionByCustomer(int customerId, List<LocalDate> date) {
		List<Transaction> transactions = new ArrayList();
		String sql = "select t.transcationId,t.transactionType,t.transactionDateTime,t.descreption,t.fromAccount,"
				+ "t.toAccount,t.amount from transaction t,account a "
				+ "where t.fromaccount =  a.accno and a.customerId =?;";
		try {
			PreparedStatement preparedStatement = getMySQLConnect().prepareStatement(sql);
			preparedStatement.setInt(1, customerId);
//			preparedStatement.setObject(2, date.get(0));
//			preparedStatement.setObject(3, date.get(1));
			ResultSet resultSet =  preparedStatement.executeQuery();
		
			while(resultSet.next()) {
				Transaction transaction = new Transaction();
				
				transaction.setTranscationId(resultSet.getInt("transcationId"));
				transaction.setTransactionType(findTransactionType(resultSet.getString("transactionType")));
				transaction.setTransactionDateTime(resultSet.getTimestamp(3).toLocalDateTime());
				transaction.setDescreption(resultSet.getString("descreption"));
				transaction.setFromAccount(findAccount(resultSet.getInt("fromAccount")));
				transaction.setToAccount(findAccount(resultSet.getInt("toAccount")));
				transaction.setAmount(resultSet.getBigDecimal("amount").doubleValue());
				
				transactions.add(transaction);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// TODO Auto-generated method stub
		return transactions;
	}
	
	private TransactionType findTransactionType(String type) {
		if(type.equalsIgnoreCase("credit"))
			return TransactionType.CREDIT;
		else if(type.equalsIgnoreCase("debit"))
			return TransactionType.DEBIT;
		else
			return null;

}


}