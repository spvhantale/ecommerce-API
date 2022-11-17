package com.swapnil.service;

import com.swapnil.exception.CustomerException;
import com.swapnil.model.Customer;

public interface CustomerSerivce {

	
	public Customer registerCustomer(Customer customer) throws CustomerException;
}
