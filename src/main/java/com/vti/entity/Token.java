package com.vti.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "Token")
public class Token implements Serializable {

	private static final long serialVersionUID = 1L;

	@Column(name = "TokenID")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private short id;

	@Column(name = "RefreshTokenCode", length = 200, nullable = false)
	private String refreshTokenCode;
	
	@Column(name = "Username", length = 200, nullable = false)
	private String userName;


	@Column(name = "CreateDate", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreationTimestamp
	private Date createDate;
	
	public Token() {
	}
	
	public Token(String refreshTokenCode, String userName) {
		this.refreshTokenCode = refreshTokenCode;
		this.userName = userName;
	}

	public short getId() {
		return id;
	}

	public void setId(short id) {
		this.id = id;
	}

	public String getRefreshTokenCode() {
		return refreshTokenCode;
	}

	public void setRefreshTokenCode(String refreshTokenCode) {
		this.refreshTokenCode = refreshTokenCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
