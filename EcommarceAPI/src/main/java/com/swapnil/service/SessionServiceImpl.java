package com.swapnil.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.swapil.dto.AdminDTO;
import com.swapil.dto.LoginDTO;
import com.swapnil.exception.AdminDTOException;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.model.Customer;
import com.swapnil.repository.CustomerDAO;
import com.swapnil.repository.SessionDAO;

import net.bytebuddy.utility.RandomString;
@Service
public class SessionServiceImpl implements SessionService{

	@Autowired
	private CustomerDAO cDao;
	
	@Autowired
	private SessionDAO lDao;
	
	@Override
	public CurrentUserSession loginUserCustomer(LoginDTO loginDTO) throws CustomerException,LoginException {
		
		Customer c= cDao.findByMobile(loginDTO.getMobileNumber());
		
		if(c!=null) {
			
			Optional<CurrentUserSession> cUser=lDao.findById(c.getCustomerId());
			if(cUser.isPresent()) {
				throw new LoginException("Already login"+loginDTO.getMobileNumber());
			}else {
				if(loginDTO.getPassword().equals(c.getPassword())) {
					String key=RandomString.make(6);
					CurrentUserSession cUserS=new CurrentUserSession(c.getCustomerId(), key, LocalDateTime.now());
					
					CurrentUserSession cus=lDao.save(cUserS);
					
					return cus;
				}else {
					throw new LoginException("Password is wrong  "+loginDTO.getMobileNumber());
				}
			}			
		}else {
			throw new CustomerException("User Mobile is wrong "+loginDTO.getMobileNumber());
		}
		
		
	}
	@Override
	public String logoutUserCustomer(String key) throws LoginException {
		
		CurrentUserSession cUser=lDao.findByUuid(key);
		
		if(cUser==null) {
			throw new LoginException("key is wrong "+key);
		}else {
			lDao.delete(cUser);
			return "LogOut";
		}
	}
	@Override
	public CurrentUserSession loginUserAdmin(AdminDTO admin) throws AdminDTOException {
		// TODO Auto-generated method stub
			if(admin.getMobileNumber().equals("9960318010")) {
				Optional<CurrentUserSession> aUser=lDao.findById(admin.getId());
				if(aUser.isPresent()) {
					throw new AdminDTOException("Already login");
				}else {
					if(admin.getPassword().equals("swapnil$4747")) {
						if(admin.getId()==123456) {
						String key=RandomString.make(6);
						CurrentUserSession cUser=new CurrentUserSession(admin.getId(),key , LocalDateTime.now());
							CurrentUserSession c=lDao.save(cUser);
							return c;
						}else {
							throw new AdminDTOException("User id is wrong"+admin.getId());
						}
					}else {
						throw new AdminDTOException("password is wrong");
					}
				}
				
			}else {
				throw new AdminDTOException("Enter valid mobile number");
			}
	}

}
