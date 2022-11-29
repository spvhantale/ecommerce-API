package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.Address;
import com.swapnil.service.AddressService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class AddressController {

	@Autowired
	private AddressService aService;
	
	@PostMapping("/addresses")
	public ResponseEntity<Address> registerAddress(@Valid @RequestBody Address add,@RequestParam String key) throws AddressException, LoginException, CustomerException{
		
		Address a=aService.registerAddress(add,key);
		
		return new ResponseEntity<Address>(a,HttpStatus.CREATED);
	}
	
	
	@PutMapping("/addresses")
	public ResponseEntity<Address> updateAddress(@Valid @RequestBody Address add,@RequestParam String key) throws AddressException, LoginException, CustomerException{
		
		Address a=aService.updateAddress(add,key);
		
		return new ResponseEntity<Address>(a, HttpStatus.OK);
	}
	
	@DeleteMapping("/addresses")
	public ResponseEntity<Address> removeAddress(@RequestParam Integer addressId,@RequestParam String key) throws AddressException, LoginException, CustomerException{
		
		Address a=aService.deleteAddress(addressId,key);
		
		return new ResponseEntity<Address>(a, HttpStatus.OK);
	}
	
	@GetMapping("/addresses")
	public ResponseEntity<List<Address>> viewAllAddress(@RequestParam String key) throws AddressException, CustomerException, LoginException{
		
		List<Address> alist=aService.viewAllAddress(key);
		
		return new ResponseEntity<List<Address>>(alist, HttpStatus.OK);
	}

	@GetMapping("/addresses/{addressId}")
	public ResponseEntity<List<Address>> viewAllAddress(@PathVariable("addressId") Integer addressId ,@RequestParam String key) throws AddressException, CustomerException, LoginException{
		
		List<Address> alist=aService.viewAddress(addressId,key);
		
		return new ResponseEntity<List<Address>>(alist, HttpStatus.OK);
	}
	
	
}
