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
public class AddressServiceImpl implements AddressService{

	@Autowired
	private AddressDAO aDao;
	@Autowired
	private CustomerDAO cDao;
	@Autowired
	private SessionDAO sDao;
	
	@Override
	public Address registerAddress(Address address, String key) throws CustomerException, LoginException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login first");
		}else {
			Optional<Customer> c=cDao.findById(cUser.getUserId());
			if(c.isPresent()) {
				address.setCustomer(c.get());
			Address a=aDao.save(address);
			c.get().getAddresses().add(address);
			Customer cu=cDao.save(c.get());
			return a;
		}else {
			throw new CustomerException("Customer not found");
		}
		}
	}

	@Override
	public Address updateAddress(Address address, String key)
			throws CustomerException, AddressException, LoginException {
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login first");
		}else {
			Optional<Customer> c=cDao.findById(cUser.getUserId());
			if(c.isPresent()) {
				List<Address> alist=c.get().getAddresses();
				if(alist.contains(address)) {
					address.setCustomer(c.get());
					Address a=aDao.save(address);
					c.get().getAddresses().add(address);
					Customer cu=cDao.save(c.get());
					return a;
				}else {
					throw new AddressException("Address not found");
				}
			
		}else {
			throw new CustomerException("Customer not found");
		}
		}
	}

	@Override
	public Address deleteAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException {
		
		CurrentUserSession loginUser=sDao.findByUuid(key);
		if(loginUser==null) {
			throw new LoginException("please login");
		}else {
			Optional<Address> a=aDao.findById(addressId);
			if(a.isPresent()) {
			Address addr=a.get();
			Optional<Customer> c=cDao.findById(loginUser.getUserId());
			List<Address> alist=c.get().getAddresses();
			for(int i=0;i<alist.size();i++) {
				if(alist.get(i).getAddressId()==addressId) {
					alist.remove(i);
				}
			}
			c.get().setAddresses(alist);
			cDao.save(c.get());
			aDao.delete(addr);
			return addr;
			}else {
			throw new AddressException("Address not found");
			}
		}
	}

	@Override
	public List<Address> viewAddress(Integer addressId, String key)
			throws CustomerException, AddressException, LoginException {
		
		List<Address> alist=aDao.findAll();
		if(alist.size()==0) {
			throw new AddressException("Address not found");
		}
		
		return alist;
	}

	@Override
	public List<Address> viewAllAddress(String key) throws  AddressException, LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser!=null) {
			if(cUser.getUserId()==123456) {
				List<Address> alist=aDao.findAll();
				if(alist.size()==0) {
					throw new AddressException("Address not found");
				}else {
					return alist;
				}
			 }else {
				 throw new LoginException("Not allowed to view this method");
			 }
		}else {
			throw new LoginException("Login First");
		}
		
	}

	
}
