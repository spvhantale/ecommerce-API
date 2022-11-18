package com.swapnil.service;

import com.swapil.dto.LoginDTO;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.CurrentUserSession;

public interface SessionService {
	
	
	public CurrentUserSession loginUserCustomer(LoginDTO loginDTO)throws CustomerException,LoginException;

	public String logoutUserCustomer(String key)throws LoginException;
}
