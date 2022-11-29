package com.swapnil.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.criteria.Order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapil.dto.AddressDTO;
import com.swapil.dto.ProductDTO;
import com.swapnil.exception.AddressException;
import com.swapnil.exception.CartException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.OrderException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Address;
import com.swapnil.model.Cart;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Customer;
import com.swapnil.model.Orders;
import com.swapnil.model.Product;
import com.swapnil.repository.AddressDAO;
import com.swapnil.repository.CartDAO;
import com.swapnil.repository.CustomerDAO;
import com.swapnil.repository.OrderDAO;
import com.swapnil.repository.SessionDAO;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderDAO oDao;
	
	@Autowired
	private SessionDAO sDao;
	
	@Autowired
	private CustomerDAO cDao;
	
	@Autowired
	private CartDAO caDao;
	
	@Autowired
	private AddressDAO aDao;

	@Override
	public Orders registerOrder(Orders order, String key) throws OrderException, CartException, LoginException,ProductException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		
		if(cUser!=null)
		{
              Integer customerId =cUser.getUserId();
			 
			 Optional<Customer> ourCustomer = cDao.findById(customerId);
			 
			 List<Address> ad = ourCustomer.get().getAddresses();
			 
			 Address addr=ad.get(0);
			 Orders currOrder = new Orders();
			 
			 currOrder.setOrderDate(order.getOrderDate());
			 AddressDTO adto=new AddressDTO(addr.getStreetNo(), addr.getHouseNumber(), addr.getCity(), addr.getState(), addr.getCountry(), addr.getPincode());
			currOrder.setOrderAddress(adto);
			 
			 currOrder.setCustomer(ourCustomer.get());
			 currOrder.setOrderStatus("Order confirmed");
			 
			 
			 Cart ca=caDao.findByCustomer(ourCustomer.get());
			 
			 List<Product> products=ca.getProducts();
			 if(products.size()==0) {
				 throw new ProductException("Products not prsent");
			 }
			 
			 
			 List<Product> productList = new ArrayList<>();

			 Double total = 0.0 ;
			 
			 for(Product proDto : products) {
				 
				 productList.add(proDto);
				 
				 total += (proDto.getProductPrice() * proDto.getQuantity()) ;
				 
			 }
			 
			 currOrder.setTotal(total);	
			 currOrder.setProductList(productList); 
			 
			 Cart customerCart = caDao.findByCustomer(ourCustomer.get()) ;
			 
			 customerCart.setProducts(new ArrayList<>());
			 
			 caDao.save(customerCart);
			 
			 return oDao.save(currOrder);
			 
		 }
		 else {
			 throw new LoginException("Login first");
		 }
		
		
	}

	@Override
	public Orders updateOrder(Orders order, String key) throws OrderException, LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("User not found "+key);
		}else {
			Optional<Orders> o=oDao.findById(order.getOrderId());
			if(o.isPresent()) {
				o.get().setOrderDate(order.getOrderDate());
				Orders ord=o.get();
				Orders or=oDao.save(ord);
				return or;
			}else {
				throw new OrderException("Order not found");
			}
			
		}
	}

	@Override
	public Orders removeOrder(Integer oriderId, String key) throws OrderException,LoginException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("User not found "+key);
		}else {
		
		Orders existingorder=oDao.findById(oriderId).orElseThrow(()-> new OrderException("Order does not exist with this id"));
		oDao.delete(existingorder);
		return existingorder;
		}
	}

	@Override
	public Orders viewOrder(Integer orderId,String key) throws OrderException,LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("User not found "+key);
		}else {
			Optional<Orders> optorder=oDao.findById(orderId);
			Orders order=optorder.get();
			return order;
		}
		
		
	}

	@Override
	public List<Orders> viewAllOrdersByDate(String date,String key) throws OrderException,LoginException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("User not found "+key);
		}else {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		
		  LocalDate localDate = LocalDate.parse(date, formatter);
		
		List<Orders> orders= oDao.findByOrderDate(localDate);
		
		if(orders.size()!=0) {
			return orders;
		}
		else {
			throw new OrderException("Order doesn't exist on this date.");
		}
		}
	}

	@Override
	public List<Orders> viewAllOrdersByLocation(String city,String key) throws OrderException, AddressException,LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("User not found "+key);
		}else {
		List<Orders> olist=oDao.getOrderByCity(city);
		
		if(olist.size()==0) {
			throw new AddressException("Order not found"+city);
		}else {
			
			return olist;
		}
		}
		
	}

	@Override
	public List<Orders> viewAllOrdersByUserId(Integer userid) throws OrderException {

		List<Orders> olist=oDao.getOrdersByuserId(userid);
		
		if(olist.size()==0) {
		
			throw new OrderException("User not found"+userid);
		}
		return olist;
	}
	
}
