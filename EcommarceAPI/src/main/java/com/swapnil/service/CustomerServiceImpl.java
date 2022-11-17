package com.swapnil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.exception.CustomerException;
import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO cDao;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException {
		
		Customer cust=cDao.findByMobile(customer.getMobile());
		if(cust==null) {
		Customer c=cDao.save(customer);
		
		return c;
		}else {
			throw new CustomerException("Already mobile number present");
		}
		
		
	
	}

	
}
