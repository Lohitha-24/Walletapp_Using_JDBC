package org.cap.demo.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.model.Account;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.model.Transaction;
import org.cap.demo.service.CustomerServiceDbImp;
import org.cap.demo.service.CustomerServiceImp;
import org.cap.demo.service.ICustomerService;
import org.cap.demo.util.InvalidPasswordException;
import org.cap.demo.view.UserInterface;

public class BootClass {
	
	public static Customer authorized_customer;
	public static void main(String[] args) throws InvalidPasswordException {
		
		UserInterface userInterface = new UserInterface();
//		ICustomerService customerService = new CustomerServiceImp();
		ICustomerService customerService = new CustomerServiceDbImp();
		
		Scanner sc =new Scanner(System.in);
		String opt1=null,opt=null;
		boolean flag = false;
		
		do {
			int option = userInterface.mainmenu();
			
			switch (option) {
			
				case 1:{
					Customer customer =  userInterface.getCustomerDetails();
					
					
					try {
						flag = customerService.addCustomer(customer);
					} catch (DataInsertionException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if(flag)
						userInterface.printMessage("Customer added successfully.");
					userInterface.printCustomer();
					break;
					}
			
				case 2:{
					Customer customer = userInterface.getLoginDetails();
					authorized_customer = customerService.loginCustomer(customer.getEmailId(), customer.getPassword());
					if(authorized_customer!=null) {
					
					
						do {
							userInterface.welcomeLogin(authorized_customer);
							
							do {
								
								int choice = userInterface.loginmenu();
								switch (choice) {
								case 1:{
									Account account = userInterface.getAccountDetails();
									Account acc = customerService.createAccount(account, authorized_customer);
									if(acc!=null)
										userInterface.printMessage("Account created successfully");
									userInterface.printAccounts(acc);
									break;
								}
								case 2:{
									Account fromaccount=null;
									Account toaccount = null;

									do {
										List<Account> accounts = customerService.getAllAccountByCustomer(authorized_customer.getCustomerId(),"fromaccount");
										int accNo = userInterface.listAllAccounts(accounts);
										fromaccount = customerService.getAccountByAccountNo(accNo);
										if(fromaccount==null)
											userInterface.printMessage("Incorrect Account no! Please enter again.");
									}while(fromaccount==null);
									
									do {
										List<Account> accounts = customerService.getAllAccountByCustomer(authorized_customer.getCustomerId(),"toaccount");
										int accNo = userInterface.listAllAccounts(accounts);
										toaccount = customerService.getAccountByAccountNo(accNo);
										if(toaccount==null)
											userInterface.printMessage("Incorrect Account no! Please enter again.");
									}while(toaccount==null);
									
									double amount = userInterface.getAmount("Transfer");
									
									if(fromaccount.getBalance()<amount) 
										userInterface.printMessage("Insufficient Balance!");
									else {
										try {
										fromaccount = customerService.depositAndWithdraw(fromaccount,amount,"debit");
										toaccount = customerService.depositAndWithdraw(toaccount,amount,"credit");
										} catch (InvalidAccountNumberException e) {
											e.printStackTrace();
										}
										if(fromaccount!=null && toaccount!=null) {
											userInterface.printMessage("Amount withdrawn from Account successfully!");
										}
									}
//									}
									break;
									}
								case 3:{
									
									Account account=null;
									do {
										List<Account> accounts = customerService.getAllAccountByCustomer(authorized_customer.getCustomerId(),"fromaccount");
										int accNo = userInterface.listAllAccounts(accounts);
										account = customerService.getAccountByAccountNo(accNo);
										if(account==null)
											userInterface.printMessage("Incorrect Account no! Please enter again.");
									}while(account==null);
	//								
									 
									double amount = userInterface.getAmount("deposit");
									
									
									try {
										account = customerService.depositAndWithdraw(account,amount,"credit");
									} catch (InvalidAccountNumberException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									if(account!=null) {
										userInterface.printMessage("Amount deposited into Account successfully!");
										System.out.println(account);
									}
									
									break;
								}
								case 4:{
									Account account=null;
									do {
									List<Account> accounts = customerService.getAllAccountByCustomer(authorized_customer.getCustomerId(),"fromaccount");
									int accNo = userInterface.listAllAccounts(accounts);
	//								
									account = customerService.getAccountByAccountNo(accNo);
									if(account==null)
										userInterface.printMessage("Incorrect Account no! Please enter again.");
								}while(account==null);
	//								
	//								
									double amount = userInterface.getAmount("withdraw");
									
	//					
									if(account.getBalance()<amount) 
										userInterface.printMessage("Insufficient Balance!");
									else {
										try {
										account = customerService.depositAndWithdraw(account,amount,"debit");
										} catch (InvalidAccountNumberException e) {
											e.printStackTrace();
										}
										if(account!=null) {
											userInterface.printMessage("Amount withdrawn from Account successfully!");
											System.out.println(account);
										}
									}
										
									break;
								}
								case 5:{
									List<LocalDate> date = userInterface.getTransactionDate();
									List<Transaction> transactions = customerService.getAllTransactionByCustomer(authorized_customer.getCustomerId(), date);
									userInterface.printTransactions(transactions);
									break;
								}
								case 6:{
									List<Account> accounts = customerService.viewAllAccounts(authorized_customer);
									userInterface.printAccounts(accounts);
									
									break;
								}
								case 7:{
									authorized_customer=null;
									opt="N";
									flag=true;
									System.out.println("Signing out..");
									break;
								}
		
								default:
									throw new IllegalArgumentException("Unexpected value" + opt1);
									
								}
								if(!flag)
									opt1 = userInterface.iterateOptions();
							}while(opt1.equalsIgnoreCase("y"));	
						}while((authorized_customer!=null));
					}else
						System.out.println("Invalid Login! Try again!");
					
					
					break;
				}
					
				case 3:
					System.exit(0);
					break;
		
				default:
					System.out.println("Enter correct options");
					break;
				}
				opt1 = userInterface.iterateOptions();
		}while(opt1.equalsIgnoreCase("y"));
			
		
		}
}
