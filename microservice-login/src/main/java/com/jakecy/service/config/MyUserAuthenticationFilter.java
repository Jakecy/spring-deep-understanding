package com.jakecy.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Component;


/**
 * 通过继承UsernamePasswordAuthenticationFilter来自定义一个Filter,
 * 并把该Filter放入到spring容器中
 * @author 池豪杰
 *
 */
@Component("myUserAuthenticationFilter")
public class MyUserAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	
	//把
	//RequestMatcher
    //successHandler
	//failtureHandler
	//...
	//注入到Filter中
	
	/**
	 * 这里采用构造方式来注入依赖
	 */
	public MyUserAuthenticationFilter() {
		AntPathRequestMatcher requestMatcher=new AntPathRequestMatcher("/login", "POST");
		AuthenticationSuccessHandler successHandler=new MyAuthenticationSuccessHandler();
		AuthenticationFailureHandler failureHandler= new MyAuthenticationFailtureHandler();
		//为Filter注入依赖的对象
		System.out.println("---------------------------");
		this.setRequiresAuthenticationRequestMatcher(requestMatcher);
		this.setAuthenticationSuccessHandler(successHandler);
		this.setAuthenticationFailureHandler(failureHandler);
	}
	
	
	//注入authenticationManager
	@Override
	@Autowired
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		super.setAuthenticationManager(authenticationManager);
	}

	
}
