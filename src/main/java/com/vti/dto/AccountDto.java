package com.vti.dto;

public class AccountDto {

	private short id;
	private String username;
	private String role;
	private String fullName;

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
}
