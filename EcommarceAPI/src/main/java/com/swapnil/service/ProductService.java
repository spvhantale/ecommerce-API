package com.swapnil.service;

import java.util.List;

import com.swapnil.exception.CategoryException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Product;

public interface ProductService {

	public Product registerProduct(Product product,String key) throws LoginException;
	
	public Product updateProduct(Product product,String key) throws LoginException,ProductException,CategoryException;
	
	public Product deleteProduct(Integer productId,String key) throws LoginException,ProductException;
	
	public Product viewProduct(Integer productId) throws ProductException;
	
	public List<Product> viewAllProduct() throws ProductException;
	
	
}
