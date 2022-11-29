package com.swapnil.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.exception.CategoryException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Product;
import com.swapnil.service.ProductService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class ProductController {

	@Autowired
	private ProductService pService;
	
	@PostMapping("/products")
	public ResponseEntity<Product> registerProduct(@Valid @RequestBody Product product,@RequestParam String key) throws LoginException{
		
		Product p=pService.registerProduct(product, key);
		return new ResponseEntity<Product>(p, HttpStatus.CREATED);
	}
	
	@PutMapping("/products")
	public ResponseEntity<Product> updateProduct(@Valid @RequestBody Product product,@RequestParam String key) throws LoginException, ProductException, CategoryException{
		
		Product p=pService.updateProduct(product, key);
		return new ResponseEntity<Product>(p, HttpStatus.ACCEPTED);
	}
	@DeleteMapping("/products/{productId}")
	public ResponseEntity<Product> deleteProduct(@PathVariable("productId") Integer productId ,@RequestParam String key) throws LoginException, ProductException, CategoryException{
		
		Product p=pService.deleteProduct(productId, key);
		return new ResponseEntity<Product>(p, HttpStatus.ACCEPTED);
	}
	@GetMapping("/products/{productId}")
	public ResponseEntity<Product> getProduct(@PathVariable("productId") Integer productId ) throws ProductException{
		Product p=pService.viewProduct(productId);
		return new ResponseEntity<Product>(p, HttpStatus.OK);
	}
	@GetMapping("/products")
	public ResponseEntity<List<Product>> getAllProduct() throws ProductException{
		List<Product> p=pService.viewAllProduct();
		return new ResponseEntity<List<Product>>(p, HttpStatus.OK);
	}
}
