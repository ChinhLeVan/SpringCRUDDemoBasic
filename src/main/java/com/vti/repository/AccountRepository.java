package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

	public Account findByUsername(String username);
}
