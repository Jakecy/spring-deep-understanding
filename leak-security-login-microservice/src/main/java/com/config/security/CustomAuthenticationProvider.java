package com.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.config.security.dto.AccountDetail;
import com.config.security.excuter.PwdVerifyExcuter;
import com.service.UserService;
import com.utils.MD5;

@Component("customAuthenticationProvider")
public class CustomAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	/**
	 * 我们要在CustomAuthenticationProvider中完成下面几项工作：
	 * 
	 * 1.根据用户提供的用户名从数据库中获取该用户名所对应的账户信息
	 * 
	 * 2.userDetail和前端传来的token
	 */
	
	//注入UserService
	@Autowired
	private  UserService    userService;
	@Autowired
	private  PwdVerifyExcuter   verifyExcuter;
	
	
	
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		//just do nothing
         return ;
	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken token)
			throws AuthenticationException {
		// 获取用户信息
		AccountDetail details = (AccountDetail) userService.loadUserByUsername(username);
		//调用verifyExcuter完成用户名和密码的校验
		verifyExcuter.verify(details, token);
		return details;
	}

}
