package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.swapil.dto.LoginDTO;
import com.swapnil.exception.CustomerException;
import com.swapnil.exception.LoginException;
import com.swapnil.model.CurrentUserSession;
import com.swapnil.service.SessionService;

@RestController
public class LoginController {
	
	@Autowired
	private SessionService sService;
	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> loginUserCustomer(@RequestBody LoginDTO login) throws CustomerException, LoginException{
		
		CurrentUserSession cUser=sService.loginUserCustomer(login);
		
		return new ResponseEntity<CurrentUserSession>(cUser, HttpStatus.CREATED);
	}

}
