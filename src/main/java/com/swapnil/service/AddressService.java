package com.swapnil.service;

import java.util.List;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.Address;

public interface AddressService {

	public Address registerAddress(Address address,String key) throws CustomerException,LoginException;
	
	public Address updateAddress(Address address ,String key) throws CustomerException,AddressException,LoginException;
	
	public Address deleteAddress(Integer addressId,String key) throws CustomerException,AddressException,LoginException;
	
	public List<Address> viewAddress(Integer addressId,String key) throws CustomerException,AddressException,LoginException;

	public List<Address> viewAllAddress(String key) throws AddressException,LoginException;
}
