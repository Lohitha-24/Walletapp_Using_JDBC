package org.cap.demo.dao;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.model.Account;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;

public interface ICustomerDao {
	
	public boolean addCustomer(Customer customer) throws DataInsertionException;
	public List<Customer> getAllCustomer();
	
	public Customer loginCustomer(String email, String password);
	public Account createAccount(Account account, Customer customer);
	
	public List<Account> getAllAccountByCustomer(int customerId, String type);
	public Account depositAndWithdraw(Account account, double amount, String type) throws InvalidAccountNumberException;
	
	public Account findAccount(int accNo);
	public List<Transaction> getAllTransactionByCustomer(int customerId, List<LocalDate> date);
	
}
