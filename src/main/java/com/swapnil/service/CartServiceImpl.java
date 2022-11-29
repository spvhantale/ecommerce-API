package com.swapnil.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.dto.ProductDTO;
import com.swapnil.exception.CartException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Cart;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Customer;
import com.swapnil.model.Product;
import com.swapnil.repository.CartDAO;
import com.swapnil.repository.CustomerDAO;
import com.swapnil.repository.ProductDAO;
import com.swapnil.repository.SessionDAO;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	private CartDAO caDao;
	
	@Autowired
	private SessionDAO sDao;
	
	@Autowired
	private ProductDAO pDao;
	
	@Autowired
	private CustomerDAO cDao;
	
	@Override
	public Cart addProductToCart(Integer productId, int quantity, String key)
			throws CartException, LoginException, ProductException,CustomerException {
		System.out.println("swapnil");
		CurrentUserSession cUser = sDao.findByUuid(key);
		if(cUser == null) {
			throw new LoginException("please login first");
		}
		
		Optional<Customer>  cus=cDao.findById(cUser.getUserId());
		if(cus.isPresent()) {
			Customer currentcutomer=cus.get();
			Optional<Product> optproduct=pDao.findById(productId);
			
			if(optproduct.isEmpty()) {
				throw new ProductException("Product if not found");
			}
	      Product currentproduct=optproduct.get();
	      if(currentproduct.getQuantity()<quantity) {
	    	  throw new ProductException("Product quantity not available");
	      }
			Cart customercart=caDao.findByCustomer(currentcutomer);
			if(customercart==null) {
				customercart=new Cart();
				customercart.setCustomer(currentcutomer);
				List<Product> list=customercart.getProducts();
			currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
			list.add(currentproduct);
			pDao.save(currentproduct);
			caDao.save(customercart);
			return customercart;
			}
			else {
				List<Product> list=customercart.getProducts();
			currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
			list.add(currentproduct);
			caDao.save(customercart);
			pDao.save(currentproduct);
			return customercart;
			
			}
		}else {
			throw new CustomerException("customer not found"+cUser.getUserId());
		}
		
		

	}

	@Override
	public List<Product> removeProductFromCart(Integer productId, String key)
			throws CartException, ProductException, LoginException {

		CurrentUserSession cUser = sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("please login");
		}
		Optional<Customer> cus = cDao.findById(cUser.getUserId());
		Customer currentcutomer=cus.get();
	Optional<Product> optproduct=pDao.findById(productId);
	if(optproduct.isEmpty()) {
		throw new ProductException("product is not available");
	}
	Product currentproduct=optproduct.get();
	Cart customerCart=caDao.findByCustomer(currentcutomer);
	if(customerCart!=null) {
		List<Product> list=customerCart.getProducts();
		boolean flag=false;
		for(int i=0;i<list.size();i++) {
			Product product=list.get(i);
			if(product.getProId()==productId) {
				pDao.deleteById(productId);
				flag=true;
				currentproduct.setQuantity(currentproduct.getQuantity()+product.getQuantity());
				pDao.save(currentproduct);
				list.remove(i);
				break;
			}
		}
		if(!flag) {
			throw new ProductException("there is no prduct with"+productId);
			
		}
		customerCart.setProducts(list);
		caDao.save(customerCart);
		return list;
	}
	else {
		throw new ProductException("Cart is Empty");
	}
	
		
	}

	@Override
	public List<Product> updateProductQuantity(Integer productId, Integer quantity, String key)
			throws CartException, LoginException, ProductException {

		CurrentUserSession cUser =sDao.findByUuid(key);
		if(cUser==null) 
		{
			throw new LoginException("please login");
		}
		Optional<Customer> cus = cDao.findById(cUser.getUserId());
		Customer currentcutomer=cus.get();
	Optional<Product> optproduct=pDao.findById(productId);
	if(optproduct.isEmpty())
	{
		throw new ProductException("product is not available");
	}
	Product currentproduct=optproduct.get();
	if(currentproduct.getQuantity() < quantity) 
	{
		throw new ProductException("Product Out of stock") ;
	}
	
	Cart customerCart = caDao.findByCustomer(currentcutomer);
	if(customerCart!=null) 
	{
		List<Product> list=customerCart.getProducts();
		boolean flag=false;
		List<Product> updateList=new ArrayList<>();
		for(Product productdto:list)
		{
			if(productdto.getProId()==productId)
			{
				flag=true;
				currentproduct.setQuantity(currentproduct.getQuantity()-quantity);
				pDao.save(currentproduct);
				productdto.setQuantity(productdto.getQuantity()+ quantity);
				Product pr=pDao.save(productdto);
				updateList.add(pr);
				break;
			}
		}
	
		if(!flag) {
			throw new ProductException("You have no product with productid"+productId);
		}
	       return updateList;
	}
	       
	       
	       else {
		throw new ProductException("You have no product in cart");
	}
	
	}

	@Override
	public Cart removeAllProducts(String key) throws CartException, LoginException {
		CurrentUserSession cUser = sDao.findByUuid(key);
		if(cUser==null) 
		{
			throw new LoginException("please login");
		}
		Optional<Customer> cus = cDao.findById(cUser.getUserId());
		Customer currentcutomer=cus.get();
		Cart customerCart = caDao.findByCustomer(currentcutomer);
		List<Product> list=customerCart.getProducts();
		if(list.size()>0) {
			for(Product productdto:list) {
				Optional<Product> opt=pDao.findById(productdto.getProId());
				Product currentproduct=opt.get();
				currentproduct.setQuantity(currentproduct.getQuantity()+productdto.getQuantity());
				pDao.delete(productdto);
				pDao.save(currentproduct);
			}
		}
		customerCart.setProducts(new ArrayList<>());
		return caDao.save(customerCart);
		
	}

	@Override
	public List<Product> viewAllProducts(String key) throws CartException, LoginException {

		CurrentUserSession optCurUser = sDao.findByUuid(key);
		if(optCurUser==null) 
		{
			throw new LoginException("please login");
		}
		Optional<Customer> cus = cDao.findById(optCurUser.getUserId());
		Customer currentcutomer=cus.get();
		Cart customercart=caDao.findByCustomer(currentcutomer);
		if(currentcutomer==null) {
			throw new CartException("cart is empty");
		}
		return customercart.getProducts();
	
	}
	
}
