package com.swapnil.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.CustomerException;
import com.swapnil.model.Customer;
import com.swapnil.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer Customer) throws CustomerException{
		
		Customer c=cService.registerCustomer(Customer);
		
		return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
		
	}
}
