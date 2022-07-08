package org.cap.demo.test;
import java.util.List;

import org.cap.demo.Exeption.DataInsertionException;
import org.cap.demo.Exeption.InvalidAccountNumberException;
import org.cap.demo.dao.CustomerDbImp;
import org.cap.demo.dao.ICustomerDao;
import org.cap.demo.model.Account;
import org.cap.demo.model.AccountType;
import org.cap.demo.model.Address;
import org.cap.demo.model.Customer;
import org.cap.demo.service.CustomerServiceDbImp;
import org.cap.demo.service.CustomerServiceImp;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
@ExtendWith
(MockitoExtension.class)
public class TestCaseWallet {
	@Mock
private ICustomerDao customerdbimp;
	@InjectMocks
private CustomerServiceDbImp customerserviceimp;
@Test
void test_addCustomer() throws DataInsertionException
{
Customer customer=new Customer();
Address address=new Address();
customer.setFirstName("swathi");
customer.setLastName("gunnam");
customer.setEmailId("swathi@gmail.com");
customer.setPassword("Swathi@97");
customer.setConfirmPassword("Swathi@97");
address.setAddressId(1);
address.setAddressLine1("1-106");
address.setAddressLine2("velpuru");
address.setCity("tanuku");
address.setState("andhrapradesh");
address.setPincode("534222");
customer.setAddress(address); 
Mockito.when(customerdbimp.addCustomer(customer)).thenReturn(true);
customerserviceimp.addCustomer(customer);
Mockito.verify(customerdbimp).addCustomer(customer);
}
@Test
void test_loginCustomer(){
Customer customer=new Customer();
customer.getEmailId();
customer.getPassword();
Mockito.when(customerdbimp.loginCustomer("sri@gmail.com", "sri@123")).thenReturn(customer);
customerserviceimp.loginCustomer("sri@gmail.com", "sri@123");
Mockito.verify(customerdbimp).loginCustomer("sri@gmail.com", "sri@123");
}
@Test
void test_createAccount()
{
Account account=new Account();
Customer customer=new Customer();
customer.setFirstName("visha");
customer.setLastName("k");
customer.setEmailId("vish@gmail.com");
customer.setPassword("visha@123");
customer.setConfirmPassword("visha@123");
Mockito.when(customerdbimp.createAccount(account, customer)).thenReturn(account);
customerserviceimp.createAccount(account, customer);
Mockito.verify(customerdbimp).createAccount(account, customer);
}
@Test
void test_depositOrWithdraw() throws InvalidAccountNumberException
{
	Account account=new Account();
	Customer customer=new Customer();
	account.setAccno(1);
	account.setBalance(4000.00);
	account.setAccountType(AccountType.CURRENT);
	Mockito.when(customerdbimp.depositAndWithdraw(account, 1, "current")).thenReturn(account);
	customerserviceimp.depositAndWithdraw(account, 1, "current");
	Mockito.verify(customerdbimp).depositAndWithdraw(account, 1, "current");
	
}
/*
@Test
void test_getAllAccountsByCustomer() {
	Customer customer=new Customer();
	Account account = new Account();
	customer.setCustomerId(1);
	account.setAccountType(AccountType.CURRENT);
	Mockito.when(customerdbimp.getAllAccountByCustomer(1, "CURRENT")).thenReturn(null);
	customerserviceimp.getAllAccountByCustomer(0, null);
	Mockito.verify(customerdbimp).getAllAccountByCustomer(0, null);
} */
/*
@Test
void test_getAllAccounts() {
	Customer customer=new Customer();
	Mockito.when(customerdbimp.getAllCustomer()).thenReturn((List<Customer>) customer);
	customerserviceimp.getAccountByAccountNo(1);
	Mockito.verify(customerdbimp).getAllCustomer();
} /*
/*
 * @Test void test_getAllCustomers() { Customer customer = new Customer();
 * Mockito.when(customerdbimp.getAllCustomer(List<Customer>).thenReturn(List<
 * Customer>); customerserviceimp(account, customer);
 * Mockito.verify(customerdbimp).createAccount(account, customer);
 * 
 * 
 * }
 */

/*
 * @Test void test_getAllAccountsByCustomer() { Customer customer=new
 * Customer(); Account account=new Account(); customer.setCustomerId(1);
 * account.setAccountType();
 * 
 * 
 * }
 */


@Test
void test_addCustomerInstance() {
CustomerServiceImp instance = new CustomerServiceImp();
Mockito.when(new CustomerServiceImp()).thenReturn(instance);
}

@Test
void test_loginCustomerInImpl () {
Customer customer = new Customer();
Mockito.when(customerserviceimp.loginCustomer("swathi@gmail.com","Swathi@97")).thenReturn(customer);
}

}