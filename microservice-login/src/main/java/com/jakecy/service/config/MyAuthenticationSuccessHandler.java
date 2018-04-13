package com.jakecy.service.config;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component("myAuthenticationSuccessHandler")
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

	/**
	 * 当认证成功后，会执行该onAuthenticationSuccess()方法
	 * 
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		//在走完认证逻辑后，我就得到了一个full  populated authentication对象
		//获取返回AccountDetail对象
		AccountDetail details = (AccountDetail) authentication.getPrincipal();
	    //判断用户名和密码是否正确
		
	

	}

}
