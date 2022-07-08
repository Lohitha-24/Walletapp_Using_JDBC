package org.cap.demo.service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.dao.CustomerDaoImp;
import org.cap.demo.dao.CustomerDbImp;
import org.cap.demo.dao.ICustomerDao;
import org.cap.demo.model.Account;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;

public class CustomerServiceDbImp implements ICustomerService{
	
ICustomerDao customerDao;
	
	public CustomerServiceDbImp() {
		this.customerDao = new CustomerDbImp();
	} 

	
public boolean addCustomer(Customer customer) throws DataInsertionException {
	
		return customerDao.addCustomer(customer);
	}


public Customer loginCustomer(String email, String password) {
		
	return customerDao.loginCustomer(email, password);
}


public Account createAccount(Account account, Customer customer) {
	// TODO Auto-generated method stub
	return customerDao.createAccount(account, customer);
}

public List<Account> viewAllAccounts(Customer customer){
	List<Account> accounts = customer.getAccounts();
	return accounts;
}


public Account getAccountByAccountNo(int accNo) {
//	List<Customer> customers = customerDao.getAllCustomer();
//	for(Customer customer:customers) {
//		List<Account> accounts = customer.getAccounts();
//		for(Account account: accounts) {
//			if(account.getAccno()==accno)
//				return account;
//			}
//		}
	return customerDao.findAccount(accNo);
}

public double deposit(Account acc, double amount) {
	acc.setBalance(acc.getBalance()+amount);
	return acc.getBalance();
	
	
}


public double withdraw(Account acc, double amount) {
	if(acc.getBalance()<amount)
		return-1;
	else {
		acc.setBalance(acc.getBalance()-amount);
		return acc.getBalance();
	}
}


@Override
public List<Account> getAllAccountByCustomer(int customerId, String type) {
	
	return customerDao.getAllAccountByCustomer(customerId,type);
}


@Override
public Account depositAndWithdraw(Account account, double amount ,String type) throws InvalidAccountNumberException {
	// TODO Auto-generated method stub
	return customerDao.depositAndWithdraw(account, amount, type);
}


@Override
public List<Transaction> getAllTransactionByCustomer(int customerId, List<LocalDate> date) {
	// TODO Auto-generated method stub
	return customerDao.getAllTransactionByCustomer(customerId, date);
}


}
