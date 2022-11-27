package com.swapnil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.Address;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Customer;
import com.swapnil.repository.AddressDAO;
import com.swapnil.repository.CustomerDAO;
import com.swapnil.repository.SessionDAO;

@Service
public class CustomerServiceImpl implements CustomerService{

	@Autowired
	private CustomerDAO cDao;
	@Autowired
	private AddressDAO aDao;
	
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public Customer registerCustomer(Customer customer) throws CustomerException,AddressException {
		
		Customer cust=cDao.findByMobile(customer.getMobile());
		if(cust==null) {
			
			if(customer.getAddresses()==null) {
				throw new AddressException("Address not present");
			}else {
				List<Address> list=customer.getAddresses();
				for(Address ad:list) {
					ad.setCustomer(customer);
					aDao.save(ad);
				}
			}
		Customer c=cDao.save(customer);
		
		return c;
		}else {
			throw new CustomerException("Already mobile number present");
		}
		
		
	
	}

	@Override
	public Customer updateCustomer(Customer customer, String key) throws CustomerException,LoginException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login First");
		}else {
			if(cUser.getUserId()==customer.getCustomerId()) {
				List<Address> list=customer.getAddresses();
				if(list==null) {
					throw new CustomerException("address is not present");
				}else {
					for(Address a:list) {
						a.setCustomer(customer);
						aDao.save(a);
					}
					Customer c=cDao.save(customer);
					return c;
				}
			}else {
				throw new CustomerException("You don't have permission login with same user");
			}
		}
	}

	@Override
	public Customer deleteCustomer(Integer customerId, String key) throws CustomerException,LoginException {
	
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login First");
		}else {
			if(cUser.getUserId()==customerId) {
				Optional<Customer> cust=cDao.findById(customerId);
				sDao.delete(cUser);
				cDao.delete(cust.get());
				return cust.get();
			}else {
				throw new CustomerException("You don't have permission login with same user");
			}
		}
	}

	@Override
	public List<Customer> viewAllCustomer(String key) throws CustomerException,LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login First");
		}else {
			if(cUser.getUserId()==123456) {
				List<Customer> clist=cDao.getCustomers();
				return clist;
			}else {
				throw new CustomerException("You are not allowed to view this information");
			}
		}
	}

	@Override
	public Customer viewCustomer(Integer customerId, String key) throws CustomerException,LoginException {
		
		
		
		
		return null;
	}

}
