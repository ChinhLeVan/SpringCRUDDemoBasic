package com.vti.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.vti.entity.Account;

public interface IAccountService extends UserDetailsService {

	List<Account> getAllAccounts();
	
	Account findByUsername(String username);
}
