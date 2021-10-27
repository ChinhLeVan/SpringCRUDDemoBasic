package com.vti.service;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vti.dto.AccountDto;
import com.vti.entity.Account;
import com.vti.entity.Token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenService {
	
    private static final long EXPIRATION_TIME = 864000000; // 10 days
    private static final String SECRET = "123456";
    private static final String PREFIX_TOKEN = "Bearer";
    private static final String AUTHORIZATION = "Authorization";
    
    public static void addJWTTokenToHeader(HttpServletResponse response, String username, IAccountService service, ITokenService tokenService) throws IOException {
        String JWT = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET)
                .compact();
        response.addHeader(AUTHORIZATION, PREFIX_TOKEN + " " + JWT);
        
        // add refresh TOKEN
        UUID uuid = UUID.randomUUID();
        String refreshTokenCode = uuid.toString();
        // save -> DB
     	tokenService.saveToken(new Token(refreshTokenCode, username));
        
        // add body
		Account account = service.findByUsername(username);
		AccountDto accountDto = new AccountDto(account.getId(), account.getUsername() , account.getRole(), account.getFullName(), refreshTokenCode );
		response.setStatus(HttpStatus.OK.value());
		String json = new ObjectMapper().writeValueAsString(accountDto);
		response.getWriter().write(json);
		response.flushBuffer();
    }

    public static Authentication parseTokenToUserInformation(HttpServletRequest request, IAccountService service ) {
        String token = request.getHeader(AUTHORIZATION);
        
        if (token == null) {
        	return null;
        }
        
        // parse the token
        String username;
		try {
			username = Jwts.parser()
	                .setSigningKey(SECRET)
	                .parseClaimsJws(token.replace(PREFIX_TOKEN, ""))
	                .getBody()
	                .getSubject();
		} catch (MalformedJwtException e) {
			return null;
		}
        Account account = service.findByUsername(username);
        
        if(account == null) {
        	return null;
        }

        return username != null ?
                new UsernamePasswordAuthenticationToken(
                		username, 
                		null, 
                		AuthorityUtils.createAuthorityList(account.getRole())) :
                null;
    }
}