package com.vti.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vti.entity.Token;

public interface ITokenRepository extends JpaRepository<Token, Integer> {

	//public Account findByUsername(String username);
}
