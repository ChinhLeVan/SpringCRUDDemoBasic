package com.vti.dto;

public class AccountDto {

	private short id;
	private String username;
	private String role;
	private String fullName;
	private String refreshToken;

	public AccountDto(short id, String fullName) {
		this.id = id;
		this.fullName = fullName;
	}
	
	public AccountDto(short id, String username, String role, String fullName) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.fullName = fullName;
	}

	public AccountDto(short id, String username, String role, String fullName,String refreshToken) {
		this.id = id;
		this.username = username;
		this.role = role;
		this.fullName = fullName;
		this.refreshToken = refreshToken;
	}

	public short getId() {
		return id;
	}

	public String getFullName() {
		return fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
	
}
