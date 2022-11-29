package com.swapnil.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapnil.exception.CategoryException;
import com.swapnil.exception.LoginException;
import com.swapnil.exception.ProductException;
import com.swapnil.model.Category;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Product;
import com.swapnil.repository.CategoryDAO;
import com.swapnil.repository.ProductDAO;
import com.swapnil.repository.SessionDAO;
@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	private ProductDAO pDao;
	@Autowired
	private SessionDAO sDao;
	@Autowired
	private CategoryDAO cDao;
	
	
	@Override
	public Product registerProduct(Product product, String key) throws LoginException {

		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login first");
		}else {
			if(cUser.getUserId()==123456) {
				Category c=product.getCategory();
						c.setProduct(product);
					Category ca=cDao.save(c);	
					product.setCategory(ca);
				Product p=pDao.save(product);
				return p;
			}else {
				throw new LoginException("You are not allowed use this method"+cUser.getUuid());
			}
		}
	}

	@Override
	public Product updateProduct(Product product, String key) throws LoginException, ProductException,CategoryException {
		
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login first");
		}else {
			if(cUser.getUserId()==123456) {
				Optional<Product> popt=pDao.findById(product.getProId());
				if(popt.isPresent()) {
						Category c=product.getCategory();
					if(c.getCatId()==popt.get().getCategory().getCatId()) {
							c.setProduct(product);
							Category ca=cDao.save(c);
							product.setCategory(ca);
							Product p=pDao.save(product);
							return p;
					}else {
						throw new CategoryException("Catgory not found");
					}
				  }else {
					  throw new ProductException("Product is not prsent");
				  }
			}else {
				throw new LoginException("You are not allowed to use this");
			}
		}
	}

	@Override
	public Product deleteProduct(Integer productId, String key) throws LoginException, ProductException {
	
		CurrentUserSession cUser=sDao.findByUuid(key);
		if(cUser==null) {
			throw new LoginException("Login first");
		}else {
			if(cUser.getUserId()==123456) {
				Optional<Product> popt=pDao.findById(productId);
				if(popt.isPresent()) {
					Product p=popt.get();
					pDao.delete(p);
					return p;
				}else {
					throw new ProductException("Product is not present");
				}
				
			}else {
				throw new LoginException("Not allowed to use this");
			}
			}
	}

	@Override
	public Product viewProduct(Integer productId) throws ProductException {
		Optional<Product> popt=pDao.findById(productId);
		if(popt.isPresent()) {
			Product p=popt.get();
			return p;
		}else {
			throw new ProductException("Product is not present");
		}
		
	}

	@Override
	public List<Product> viewAllProduct() throws ProductException {
		List<Product> plist=pDao.findAll();
		if(plist.isEmpty()) {
			throw new ProductException("products are not present");
		}
		return plist;
	}

}
