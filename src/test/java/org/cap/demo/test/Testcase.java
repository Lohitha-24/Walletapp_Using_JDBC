package org.cap.demo.test;
import java.util.List;



import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.dao.CustomerDaoImp;
import org.cap.demo.dao.CustomerDbImp;
import org.cap.demo.dao.ICustomerDao;
import org.cap.demo.model.Account;
import org.cap.demo.model.AccountType;
import org.cap.demo.model.Customer;
import org.cap.demo.service.CustomerServiceDbImp;
import org.cap.demo.service.CustomerServiceImp;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;



@ExtendWith(MockitoExtension.class)
class Testcase {



	@Mock
	private ICustomerDao customerDao;


	@InjectMocks
	private CustomerServiceDbImp customerServiceImpl;


	@Mock
	private CustomerServiceImp customerServiceImp;

	@Mock
	private CustomerDaoImp customerDaoImp;

	@Mock
	private CustomerDbImp cutomerDbImp;





	@Test
	void test_addCustomer() throws DataInsertionException {

		Customer customer= new Customer(1, "swathi","gunnam", "1234512345","swathi@gmail.com",
				"Swathi@97","Swathi@97");



		Mockito.when(customerDao.addCustomer(customer)).thenReturn(true);

		customerServiceImpl.addCustomer(customer);
		Mockito.verify(customerDao).addCustomer(customer);

	}

	@Test
	void test_addCustomerDbImp() throws DataInsertionException {
		Customer customer= new Customer(1, "swathi","gunnam", "1234512345","swathi@gmail.com",
				"Swathi@97","Swathi@97");


		Mockito.when(cutomerDbImp.addCustomer(customer)).thenReturn(true);

	}

	@Test
	void test_loginCustomer () {
		Customer customer = new Customer();
		Mockito.when(customerServiceImpl.loginCustomer("swathi@gmail.com","Swathi@97")).thenReturn(customer);
	}

	@Test
	void test_invalidLoginCustomer () {
		Mockito.when(customerServiceImpl.loginCustomer("tt@gmail.com","rr@97")).thenReturn(null);
	}

	@Test
	void test_createAccount () {
		Customer loggedInCustomer = customerServiceImpl.loginCustomer("swathi@gmail.com","Swathi@97");

		Account newAccount = new Account();
		newAccount.setAccountType(AccountType.SALARY);
		newAccount.setBalance(0);

		Account createdAccount = new Account();
		Mockito.when(customerServiceImpl.createAccount(newAccount, loggedInCustomer)).thenReturn(createdAccount);
	}



	@Test
	void test_viewAllAccount () {
		Customer loggedInCustomer = customerServiceImpl.loginCustomer("swathi@gmail.com","Swathi@97");

		Mockito.when(customerServiceImp.viewAllAccounts(loggedInCustomer)).thenReturn(null);
	}

	@Test
	void test_addCustomerInImpl() throws DataInsertionException {
		Customer customer= new Customer(1, "swathi","gunnam", "1234512345","swathi@gmail.com",
				"Swathi@97","Swathi@97");



		Mockito.when(customerServiceImp.addCustomer(customer)).thenReturn(true);



	}

	@Test
	void test_addCustomerInstance() {
		CustomerServiceImp instance = new CustomerServiceImp();
		Mockito.when(new CustomerServiceImp()).thenReturn(instance);
	}

	@Test
	void test_loginCustomerInImpl () {
		Customer customer = new Customer();
		Mockito.when(customerServiceImp.loginCustomer("swathi@gmail.com","Swathi@97")).thenReturn(customer);
	}

	@Test
	void test_loginCustomer1(){
		Customer customer=new Customer();
		customer.getEmailId();
		customer.getPassword();
		Mockito.when(customerDaoImp.loginCustomer("swathi@gmail.com", "Swathi@97")).thenReturn(customer);
		customerServiceImp.loginCustomer("swathi@gmail.com", "Swathi@97");
		Mockito.verify(customerDaoImp).loginCustomer("swathi@gmail.com", "Swathi@97");
	}
	@Test
	void test_findCustomer() {
		Customer customer = new Customer();


		Mockito.when(customerDaoImp.findCustomer(1)).thenReturn(customer);


	}
	@Test
	void test_findAccount() {
		Account account = new Account();
		Mockito.when(customerDaoImp.findAccount(1)).thenReturn(account);
	}
	@Test
	void test_viewAllAccounts() {
		Customer getAllAccounts;
		Customer account= new Customer();
	//	Mockito.when(customerServiceImp.viewAllAccounts(getAllAccounts).thenReturn(account));
	}
}