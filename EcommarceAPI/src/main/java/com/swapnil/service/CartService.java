package com.swapnil.service;

import java.util.List;

import com.swapil.dto.ProductDTO;
import com.swapnil.exception.CartException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Cart;
import com.swapnil.model.Product;

public interface CartService {

	public Cart addProductToCart(Integer productId, int quantity,String key) throws CartException, LoginException, ProductException,CustomerException ;
	
	public List<Product> removeProductFromCart(Integer productId,String key) throws CartException, ProductException, LoginException  ;
	
	public List<Product> updateProductQuantity(Integer productId,Integer quantity,String key) throws CartException, LoginException, ProductException ;
	
	public Cart removeAllProducts(String key) throws CartException, LoginException ;
	
	public List<Product> viewAllProducts(String key)  throws CartException, LoginException;
	
}
