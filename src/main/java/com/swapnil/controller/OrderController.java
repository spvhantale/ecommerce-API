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
import com.swapnil.exception.CartException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.OrderException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Orders;
import com.swapnil.service.OrderService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class OrderController {

	@Autowired
	private OrderService oService;
	
	@PostMapping("/orders")
	public ResponseEntity<Orders> registerOrder(@Valid @RequestBody Orders order,@RequestParam String key) throws OrderException, CartException, LoginException, ProductException{
		
		Orders o=oService.registerOrder(order, key);
		
		return new ResponseEntity<Orders>(o,HttpStatus.CREATED);
	}
	
	@PutMapping("/orders")
	public ResponseEntity<Orders> updateOrder(@Valid @RequestBody Orders order,@RequestParam String key) throws OrderException, LoginException{
		
		Orders o=oService.updateOrder(order, key);
		
		return new ResponseEntity<Orders>(o, HttpStatus.ACCEPTED);
		
	}
	@DeleteMapping("/orders/{orderId}")
	public ResponseEntity<Orders> removeOrder(@PathVariable("orderId") Integer orderId,@RequestParam String key) throws OrderException, LoginException{
		Orders o=oService.removeOrder(orderId, key);
		
		return new ResponseEntity<Orders>(o, HttpStatus.OK);
	}
	@GetMapping("/orders/{orderId}")
	public ResponseEntity<Orders> viewOrder(@PathVariable("orderId") Integer orderId,@RequestParam String key) throws OrderException, LoginException{
		
		Orders o=oService.viewOrder(orderId ,key);
		
		return new ResponseEntity<Orders>(o, HttpStatus.OK);

	}

	@GetMapping("/getorders")
	public ResponseEntity<List<Orders>> viewAllOrder(@RequestParam String date,@RequestParam String key) throws OrderException, LoginException{
		
		List<Orders> olist=oService.viewAllOrdersByDate(date,key);
		
		return new ResponseEntity<List<Orders>>(olist, HttpStatus.OK);
	}
	
	@GetMapping("/orders")
	public ResponseEntity<List<Orders>> viewAllOrders(@RequestParam String city,@RequestParam String key) throws OrderException, AddressException, LoginException{
		
		List<Orders> olist=oService.viewAllOrdersByLocation(city,key);
		
		return new ResponseEntity<List<Orders>>(olist, HttpStatus.OK);
	}
	
	@GetMapping("/listorders/{userid}")
	public ResponseEntity<List<Orders>> viewAllOrders(@PathVariable("userid") Integer userId) throws OrderException, AddressException{
		
		List<Orders> olist=oService.viewAllOrdersByUserId(userId);
		
		return new ResponseEntity<List<Orders>>(olist, HttpStatus.OK);
	}
	
	
}
