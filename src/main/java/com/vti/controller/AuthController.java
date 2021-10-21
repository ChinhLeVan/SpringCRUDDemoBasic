package com.vti.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.service.IAccountService;

@RestController
@RequestMapping(value = "api/v1/login")
@CrossOrigin("*")
public class AuthController {

	@Autowired
	private IAccountService service;

	@GetMapping()
	public ResponseEntity<?> login(Principal principal){
		String username = principal.getName();
		Account account = service.findByUsername(username);
		AccountDto accountDto = new AccountDto(account.getId(), account.getUsername() , account.getRole(), account.getFullName());
		return new ResponseEntity<>(accountDto, HttpStatus.OK);
	}	
}