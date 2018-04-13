package com.jakecy.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

/**
 * 自定义一个AuthenticationProvider
 * @author chihaojie
 * 如果我们采用的是AbstractUserDetailAuthenticationProvider的话，
 * 我们必须要向该Provider中提供UserDetailsService实现
 */

@Component("myLoginAuthenticationProvider")
public class MyLoginAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	//注入UserService实现
	@Autowired
	private   UserDetailsService   userDetailsService;
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authentication)
			throws AuthenticationException {
		  //在这里
		  //调用userDetailsService来获取用户信息
		UserDetails userDetails = userDetailsService.loadUserByUsername("jack");
		return userDetails;
	}

}
