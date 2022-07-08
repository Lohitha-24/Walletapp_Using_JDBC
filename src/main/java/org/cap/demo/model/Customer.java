package org.cap.demo.model;

import java.util.ArrayList;
import java.util.List;

public class Customer {
	
	private int customerId;
	private String firstName;
	private String lastName;
	private String emailId; 
	private String password;
	private String confirmPassword;
	private String contactNo;
	private Address address;
	
	
	private List<Account> accounts = new ArrayList<Account>();

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

	public Customer(int customerId, String firstName, String lastName, String emailId, String password, String confirmPassword,
			String contactNo, Address address, List<Account> accounts) {
		super();
		this.firstName = firstName;
		this.customerId = customerId;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contactNo = contactNo;
		this.address = address;
		this.accounts = accounts;
	}

	public Customer(int customerId, String firstName, String lastName, String emailId, String password, String confirmPassword,
			String contactNo) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.contactNo = contactNo;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", emailId=" + emailId + ", password=" + password + ", contactNo=" + contactNo + ", address="
				+ address + ", accounts=" + accounts + "]";
	}

	


}
