package com.vti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import com.vti.service.IAccountService;
import com.vti.service.ITokenService;

@Component
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private IAccountService service;
	
	@Autowired
	private ITokenService tokenService;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(new BCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.authorizeRequests()
			.antMatchers("/api/v1/departments/**").hasAnyAuthority("Admin", "Manager")
			//.antMatchers("/api/v1/reset-password").permitAll() // cho phep tat ca truy cap
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf().disable()
			.addFilterBefore(new JWTAuthenticationFilter("/api/v1/login",authenticationManager(), service, tokenService), UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(new JWTAuthorizationFilter(service), UsernamePasswordAuthenticationFilter.class);
	}
}