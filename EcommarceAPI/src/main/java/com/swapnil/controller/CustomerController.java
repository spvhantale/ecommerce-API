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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.Customer;
import com.swapnil.service.CustomerService;


@RestController
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@PostMapping("/customers")
	public ResponseEntity<Customer> registerCustomer(@Valid @RequestBody Customer customer) throws CustomerException, AddressException{
		
		Customer c=cService.registerCustomer(customer);
		
		return new ResponseEntity<Customer>(c, HttpStatus.CREATED);
		
	}
	@PutMapping("/customers")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@RequestParam String key) throws CustomerException, AddressException, LoginException{
		
		Customer c=cService.updateCustomer(customer,key);
		
		return new ResponseEntity<Customer>(c, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/customers/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable("customerId") Integer customerId,@RequestParam String key) throws CustomerException, AddressException, LoginException{
		
		Customer c=cService.deleteCustomer(customerId,key);
		
		return new ResponseEntity<Customer>(c, HttpStatus.ACCEPTED);
	}
	@GetMapping("/customers/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable("customerId") Integer customerId,@RequestParam String key) throws CustomerException, AddressException, LoginException{
		
		Customer c=cService.viewCustomer(customerId,key);
		
		return new ResponseEntity<Customer>(c, HttpStatus.ACCEPTED);
	}
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> viewAllCustomer(@RequestParam String key) throws CustomerException, LoginException{
		
		List<Customer> c=cService.viewAllCustomer(key);
		
		return new ResponseEntity<List<Customer>>(c, HttpStatus.ACCEPTED);
	}
}
