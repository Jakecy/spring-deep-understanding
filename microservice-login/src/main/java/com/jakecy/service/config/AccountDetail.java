package com.jakecy.service.config;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class AccountDetail extends User {

	/**
	 * 
	 */
	private static final long serialVersionUID = 763922754124224742L;
	//在这里定义一个需要返回给前端的
	//当前登录人的账户信息
	private  final String   loginData; 
	
	private  String  username_rq;
	private  String  password_rq;
	
	
	
	public AccountDetail(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked,
			Collection<? extends GrantedAuthority> authorities) {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.loginData=username;
	}



	/**
	 * @return the loginData
	 */
	public String getLoginData() {
		return loginData;
	}



	/**
	 * @return the username_rq
	 */
	public String getUsername_rq() {
		return username_rq;
	}



	/**
	 * @param username_rq the username_rq to set
	 */
	public void setUsername_rq(String username_rq) {
		this.username_rq = username_rq;
	}



	/**
	 * @return the password_rq
	 */
	public String getPassword_rq() {
		return password_rq;
	}

	/**
	 * @param password_rq the password_rq to set
	 */
	public void setPassword_rq(String password_rq) {
		this.password_rq = password_rq;
	}
	
	

}
