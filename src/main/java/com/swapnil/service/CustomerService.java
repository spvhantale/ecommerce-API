package com.swapnil.service;

import java.util.List;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.Customer;

public interface CustomerService {

	
	public Customer registerCustomer(Customer customer) throws CustomerException,AddressException;
	public Customer updateCustomer(Customer customer,String key)throws CustomerException,LoginException;
	public Customer deleteCustomer(Integer customerId,String key) throws CustomerException,LoginException;
	public List<Customer> viewAllCustomer(String key) throws CustomerException,LoginException;
	public Customer viewCustomer(Integer customerId,String key) throws CustomerException,LoginException;

}
