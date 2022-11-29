package com.swapnil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.swapnil.dto.AdminDTO;
import com.swapnil.dto.LoginDTO;
import com.swapnil.exception.AdminDTOException;
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
	
	@GetMapping("/logout")
	public ResponseEntity<String> logoutUserCustomer(@RequestParam String key) throws LoginException{
		
		String str=sService.logoutUserCustomer(key);
		
		return new ResponseEntity<String>(str, HttpStatus.ACCEPTED);
	}

	@PostMapping("/adminlogin")
	public ResponseEntity<CurrentUserSession> loginAdminUser(@RequestBody AdminDTO admin) throws AdminDTOException{
		
		CurrentUserSession c=sService.loginUserAdmin(admin);
	
		return new ResponseEntity<CurrentUserSession>(c, HttpStatus.CREATED);
	}
}
