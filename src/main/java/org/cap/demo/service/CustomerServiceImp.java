package org.cap.demo.service;



import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.dao.CustomerDaoImp;
import org.cap.demo.dao.ICustomerDao;
import org.cap.demo.model.Account;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;

public class CustomerServiceImp implements ICustomerService {
	
	ICustomerDao customerDao;
	
	public CustomerServiceImp() {
		this.customerDao = new CustomerDaoImp();
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


public Account getAccountByAccountNo(int accno) {
	List<Customer> customers = customerDao.getAllCustomer();
	for(Customer customer:customers) {
		List<Account> accounts = customer.getAccounts();
		for(Account account: accounts) {
			if(account.getAccno()==accno)
				return account;
			}
		}
	return null;
}

public Account depositAndWithdraw(Account account, double amount, String type) throws InvalidAccountNumberException {
//	acc.setBalance(acc.getBalance()+amount);
	return customerDao.depositAndWithdraw(account, amount,type);
	
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
public List<Transaction> getAllTransactionByCustomer(int customerId, List<LocalDate> date) {
	// TODO Auto-generated method stub
	return null;
}


}
