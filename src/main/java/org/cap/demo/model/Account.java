package org.cap.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Account {
	
	private int accno;
	private AccountType accountType;
	private LocalDate openingDate;
	private double balance;
	private String description; 
	private Customer customer;

	private List<Transaction> transactions = new ArrayList<Transaction>();
	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	

	public int getAccno() {
		return accno;
	}

	public void setAccno(int accno) {
		this.accno = accno;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}

	public LocalDate getOpeningDate() {
		return openingDate;
	}

	public void setOpeningDate(LocalDate openingDate) {
		this.openingDate = openingDate;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public Account(int accno, AccountType accountType, LocalDate openingDate, double balance, String description,
			List<Transaction> transactions) {
		super();
		this.accno = accno;
		this.accountType = accountType;
		this.openingDate = openingDate;
		this.balance = balance;
		this.description = description;
		this.transactions = transactions;
	}

	public Account() {
		super();
	}

	@Override
	public String toString() {
		return "Account [accno=" + accno + ", accountType=" + accountType + ", openingDate=" + openingDate
				+ ", balance=" + balance + ", description=" + description + ", transactions=" + transactions + "]";
	}
	
	
}
