package org.cap.demo.view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.cap.demo.Exeption.ConfirmPasswordDoesnotMatchException;
import org.cap.demo.Exeption.EmptyAddressFieldException;
import org.cap.demo.Exeption.FirstNameEmptyException;
import org.cap.demo.Exeption.InvalidContactNoException;
import org.cap.demo.dao.CustomerDaoImp;
import org.cap.demo.dao.ICustomerDao;
import org.cap.demo.model.Account;
import org.cap.demo.model.AccountType;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;
import org.cap.demo.util.InvalidBalanceException;
import org.cap.demo.util.InvalidEmailException;
import org.cap.demo.util.InvalidPasswordException;
import org.cap.demo.util.validation;

public class UserInterface {
	
	Scanner sc = new Scanner(System.in);
	ICustomerDao cust = new CustomerDaoImp();
	
	public String iterateOptions() {
		System.out.println("Do you want to continue? [Y/N]");
		return sc.next();
	}
	
	
	public int mainmenu() {
		System.out.println("1. Create new Customer\r\n" + 
							"2. Sign In\r\n" +
							"3. Exit \r\n");
		
		System.out.println("Enter option [1-3] : ");
		
		int input = sc.nextInt();
		return input;
		
		}
	
	public int loginmenu() {
		System.out.println("1. Create Account\r\n" + 
							"2. Fund Transfer\r\n" +
							"3. Deposit Amount\r\n" +
							"4. Withdraw Amount\r\n" +
							"5. Transaction summary\r\n" +
							"6. View all Accounts\r\n" +
							"7. SignOut");
		
		System.out.println("Enter option [1-5] : ");
		
		int input = sc.nextInt();
		return input;
		
		}
	
	
	
	public Customer getCustomerDetails() throws InvalidPasswordException {
		String password=null,fname=null,contactNo=null,email=null,cnfmpassword=null;
		
		Customer customer = new Customer();
		
//----------------------first name validation-----------------------
		do {
			System.out.println("Enter firstName");
			fname = sc.next();
	
			if(fname==null || fname=="")
				throw new FirstNameEmptyException("First name cannot be empty! Enter again.");
			
			}while(fname==null || fname=="");
		
		customer.setFirstName(fname);
		
		System.out.println("Enter lastName");
		customer.setLastName(sc.next());
		
//---------------------contact No. validation-------------------------
		do {
		System.out.println("Enter contactNo");
		contactNo = sc.next();
		try {
		if(!contactNo.matches("\\d{10}"))
			throw new InvalidContactNoException("Contact no. should be of 10 digits");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(!contactNo.matches("\\d{10}"));
		customer.setContactNo(contactNo);
		
//---------------------email validation--------------------------------
		String regex_Email = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
		do {
			System.out.println("Enter emailId");
			email = sc.next();
			try {
			if(!email.matches(regex_Email))
				throw new InvalidEmailException("Invalid Email!!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
		}while(!email.matches(regex_Email));
		
		customer.setEmailId(email);
		
//--------------------password validation----------------------------
		String regx_Password =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";
		do {
			try {
			System.out.println("Enter password");
			password=sc.next();
			if(!password.matches(regx_Password))
				throw new InvalidPasswordException("Invalid Password!!!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(!password.matches(regx_Password));
		
		customer.setPassword(password);
//------------------confirm password-----------------------------------
		do {
		System.out.println("Confirm Password");
		cnfmpassword = sc.next();
		try {
		if(!cnfmpassword.equals(password))
			throw new ConfirmPasswordDoesnotMatchException("Password and ConfirmPassword must be same!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(!cnfmpassword.equals(password));
		
		customer.setConfirmPassword(cnfmpassword);
		

		Address address = getAddress();
		customer.setAddress(address);
		System.out.println(customer);
		
		return customer;
		
	}
	
	public Address getAddress() {
		String addline1=null,addline2=null,city=null,state=null,pincode=null;
		
		Address address = new Address();
		
		System.out.println("Please Enter Address details ");
		
//------------------------address Line 1-----------------------------
		do {
		System.out.println("Enter addressLine1");
		addline1 = sc.next();
		try {
		if(addline1==null || addline1=="")
			throw new EmptyAddressFieldException("This field cannot be empty!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(addline1==null || addline1=="");
		address.setAddressLine1(addline1);
		
//------------------------address Line 2-----------------------------
		do {
		System.out.println("Enter addressLine2");
		addline2 = sc.next();
		try {
		if(addline2==null || addline2=="")
			throw new EmptyAddressFieldException("This field cannot be empty!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(addline2==null || addline2=="");
		address.setAddressLine2(addline2);
		
//------------------------city-----------------------------
		do {
		System.out.println("Enter city");
		city = sc.next();
		try {
		if(city==null || city=="")
			throw new EmptyAddressFieldException("This field cannot be empty!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(city==null || city=="");
		address.setCity(city);
		
//------------------------state-----------------------------
		do {
		System.out.println("Enter state");
		state = sc.next();
		try {
		if(state==null || state=="")
			throw new EmptyAddressFieldException("This field cannot be empty!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(state==null || state=="");
		address.setState(state);
		
//------------------------pincode----------------------------
		do {
		System.out.println("Enter pincode");
		pincode = sc.next();
		try {
		if(pincode==null || pincode=="")
			throw new EmptyAddressFieldException("This field cannot be empty!");
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		}while(pincode==null || pincode=="");
		address.setPincode(pincode);
		
		return address;
		
	}
	
	public void printCustomer() {
		List<Customer> c = cust.getAllCustomer();
		for(Customer c1:c) {
			System.out.println(c1);
		}
		
	}
	
	public void printMessage(String str) {
		System.out.println(str);
	}


	public Customer getLoginDetails() {
		Customer customer = new Customer();
		String password=null;
		String password_regx =  "^(?=.*[0-9])"
				+ "(?=.*[a-z])(?=.*[A-Z])"
				+ "(?=.*[@#$%^&+=])"
				+ "(?=\\S+$).{8,20}$";
		System.out.println("Enter emailId");
		customer.setEmailId(sc.next());
		
		do {
			try {
			System.out.println("Enter password");
			password=sc.next();
			if(!password.matches(password_regx))
				throw new InvalidPasswordException("Invalid Password!!!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(!password.matches(password_regx));
		
		customer.setPassword(password);
		return customer;
	}


	public void welcomeLogin(Customer customer) {
		System.out.println("********************************");
		System.out.println("Welcome " + customer.getFirstName() + " " + customer.getLastName());
		System.out.println("*********************************");
		
	}


	public Account getAccountDetails() {
		Account account = new Account();
		double bal = 0.0;
		String desc=null,acctype=null;
		do {
			System.out.println("Enter Account type [SAVING,CURRENT,LOAN,SALARY]");
			acctype = sc.next();
			if(acctype.equalsIgnoreCase("saving"))
				account.setAccountType(AccountType.SAVING);
			else if(acctype.equalsIgnoreCase("current"))
				account.setAccountType(AccountType.CURRENT);
			else if(acctype.equalsIgnoreCase("loan"))
				account.setAccountType(AccountType.LOAN);
			else if(acctype.equalsIgnoreCase("salary"))
				account.setAccountType(AccountType.SALARY);
			else
				System.out.println("Invalid accountype! Try again");
		}while(acctype==null);

		do {
			try {
			System.out.println("Enter Account balance");
			bal=sc.nextDouble();
			if(bal<1000)
				throw new InvalidBalanceException("balance should be greater than 1000!!!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(bal<1000);
		
		account.setBalance(bal); 
		
		do {
			try {
			System.out.println("Enter Account description");
			desc=sc.next();
			if(desc.equals(null))
				throw new InvalidEmailException("description cannot be empty!!!");
			}catch(Exception e) {
				System.out.println(e.getMessage());
			}
			
		}while(desc.equals(null));
		
		account.setDescription(desc);
		
		account.setOpeningDate(LocalDate.now());
		return account;
	}


	public void printAccounts(List<Account> accounts) {
		int i=1;
		for(Account account:accounts) {
			System.out.println(i + " " + account);
			i++;
		}
	}


	public void printAccounts(Account acc) {
		System.out.println(acc);
		
	}


	public int getAccountNo() {
		System.out.println("Please enter your Account number :");
		return sc.nextInt();
	}


	public double getAmount(String str) {
		if(str.equalsIgnoreCase("deposit"))
			System.out.println("Enter Amount to deposit :");
		else if(str.equalsIgnoreCase("withdraw"))
			System.out.println("Enter Amount to withdraw :");
		else
			System.out.println("Enter funds to transfer :");
		double amount =sc.nextDouble();
		System.out.println("your Enterd Ammount is: "+ amount);
		return amount;
	}


	public int listAllAccounts(List<Account> accounts) {
		
		System.out.println("Choose the account number");
		
		for(Account account:accounts) 
			System.out.println(account.getAccno() + "\t"
					+account.getAccountType() + "\t"
					+ account.getBalance());
			
			System.out.println("Enter your Account No.");
			int accNo = sc.nextInt();
			System.out.println("your Enterd Account No. is: "+ accNo);
		
			return accNo;
	}


	public void printTransactions(List<Transaction> transactions) {
		int i=1;
		for(Transaction transaction:transactions) {
			System.out.println(i + " " + transaction);
			i++;
		
	}
	}


	public List<LocalDate> getTransactionDate() {
		String str;
		String[] strarray;
		List<LocalDate> date = new ArrayList<>();
		System.out.println("Enter to Date in [yyyy-mm-dd]");
		str = sc.next();
		strarray = str.split("-");
		date.add(LocalDate.of(Integer.parseInt(strarray[0]), Integer.parseInt(strarray[1]), Integer.parseInt(strarray[2])));
		
		System.out.println("Enter from Date in [yyyy-mm-dd]");
		str = sc.next();
		strarray = str.split("-");
		date.add(LocalDate.of(Integer.parseInt(strarray[0]), Integer.parseInt(strarray[1]), Integer.parseInt(strarray[2])));
		
		return date;
	}

}
