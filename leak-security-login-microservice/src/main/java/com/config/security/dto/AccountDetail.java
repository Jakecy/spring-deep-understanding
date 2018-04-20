package com.config.security.dto;


import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AccountDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7823479345235119776L;
	
	
	private String username;
	private String password;
	private String salt;
	
	public AccountDetail(String username, String password,String salt, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
		this.username = username;
		this.password = password;
		this.salt = salt;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the salt
	 */
	public String getSalt() {
		return salt;
	}

	/**
	 * @param salt the salt to set
	 */
	public void setSalt(String salt) {
		this.salt = salt;
	}



}
