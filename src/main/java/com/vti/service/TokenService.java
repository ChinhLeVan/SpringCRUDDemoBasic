package com.vti.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vti.entity.Token;
import com.vti.repository.ITokenRepository;

@Service
@Transactional
public class TokenService implements ITokenService {
	
	@Autowired
	ITokenRepository tokenRepository;
	
	@Override
	public void saveToken(Token token) {
		
		tokenRepository.save(token);
	}

	
}
