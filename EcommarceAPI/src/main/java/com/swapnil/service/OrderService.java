package com.swapnil.service;

import java.util.List;

import com.swapnil.exception.AddressException;
import com.swapnil.exception.CartException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.OrderException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Orders;

public interface OrderService {

	public Orders registerOrder(Orders order, String key) throws OrderException, CartException, LoginException,ProductException;
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException;
	public Orders removeOrder(Integer oriderId, String key) throws OrderException,LoginException;
	public Orders viewOrder(Integer orderId,String key) throws OrderException,LoginException;
	public List<Orders> viewAllOrdersByDate(String date,String key) throws OrderException,LoginException;
	public List<Orders> viewAllOrdersByLocation(String city,String key) throws OrderException, AddressException,LoginException;
	public List<Orders> viewAllOrdersByUserId(Integer userid) throws OrderException;
}
