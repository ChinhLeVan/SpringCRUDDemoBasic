package com.vti.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.vti.entity.Account;
import com.vti.service.IAccountService;
import com.vti.service.ITokenService;
import com.vti.service.JWTTokenService;

public class JWTAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
	
	private IAccountService service;
	
	private ITokenService tokenService;
	
    public JWTAuthenticationFilter(String url, AuthenticationManager authManager, IAccountService service, ITokenService tokenService) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
        this.service = service;
        this.tokenService = tokenService;
    }

    @Override
    public Authentication attemptAuthentication(
    		HttpServletRequest request, 
    		HttpServletResponse response) 
    		throws AuthenticationException, IOException, ServletException {
    	
    	String username = request.getParameter("username");
    	String password = request.getParameter("password");
    	
    	Account user = service.findByUsername(username);
    	
        return getAuthenticationManager().authenticate(
                new UsernamePasswordAuthenticationToken(
                		username,
                		password,
                		AuthorityUtils.createAuthorityList(user.getRole())
                )
        );
    }

    @Override
    protected void successfulAuthentication(
    		HttpServletRequest request, 
    		HttpServletResponse response, 
    		FilterChain chain, 
    		Authentication authResult) throws IOException, ServletException {
        JWTTokenService.addJWTTokenToHeader(response, authResult.getName(), service, tokenService);
    }
}
