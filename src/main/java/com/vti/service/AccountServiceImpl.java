package com.vti.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.vti.entity.Account;
import com.vti.repository.AccountRepository;

@Service
public class AccountServiceImpl implements IAccountService {

	@Autowired
	private AccountRepository repository;

	@Override
	public List<Account> getAllAccounts() {
		return repository.findAll();
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Check user exists by username
		Account account = repository.findByUsername(username);

		if (account == null) {
			throw new UsernameNotFoundException(username);
		}

		return new org.springframework.security.core.userdetails.User(
				account.getUsername(), 
				account.getPassword(),
				AuthorityUtils.createAuthorityList(account.getRole()));
	}

	@Override
	public Account findByUsername(String username) {
		return repository.findByUsername(username);
	}


}
