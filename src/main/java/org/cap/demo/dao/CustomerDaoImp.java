package org.cap.demo.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.cap.demo.model.Account;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;

public class CustomerDaoImp implements ICustomerDao {
	
	private static AtomicInteger addressId = new AtomicInteger(34);
	private static AtomicInteger accountId = new AtomicInteger(789);
	private static AtomicInteger customerId =  new AtomicInteger(12345);
	private static List<Customer> customers = dummyCustomerDB();
	
	
	public boolean addCustomer(Customer customer) {
		customer.setCustomerId(customerId.getAndIncrement());
		customer.getAddress().setAddressId(addressId.getAndIncrement());
		
		return customers.add(customer);
	}
	private static List<Customer> dummyCustomerDB() {
		
		List<Customer> customers = new ArrayList<Customer>();
		return customers;
	}
	public List<Customer> getAllCustomer() {
		
		return customers;
	}
	
	public Customer loginCustomer(String email, String password) {
		for(Customer customer:customers) {
			if(customer.getEmailId().equals(email) && customer.getPassword().equals(password)) {
				
				return customer;
			}
		}
		return null;
	}

	public Customer findCustomer(int customerId) {
		for(Customer customer:customers) {
			if(customer.getCustomerId()==customerId)
				return customer;
		}
		return null;
	}
	
	
	public Account createAccount(Account account, Customer customer) {
		if(findCustomer(customer.getCustomerId())!=null) {
			account.setAccno(accountId.getAndIncrement());
			customer.getAccounts().add(account);
			return account;
		}
		
		return null;
	}
	@Override
	public List<Account> getAllAccountByCustomer(int customerId,String type) {
		
		return null;
	}
	@Override
	public Account depositAndWithdraw(Account account, double amount, String type) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Account findAccount(int accNo) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public List<Transaction> getAllTransactionByCustomer(int customerId, List<LocalDate> date) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
