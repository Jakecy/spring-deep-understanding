package com.config.security.handler;


import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.config.security.exception.PasswordWrongException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component("customloginFailureHandler")
public class CustomloginFailureHandler implements AuthenticationFailureHandler {

	@Autowired
    private  ObjectMapper objectMapper;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException, ServletException {

		// 设置响应格式
		response.setHeader("Content-type", "text/html;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setStatus(HttpStatus.OK.value());
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		//根据security抛出的异常类型来进行不同的响应
		if(authenticationException instanceof PasswordWrongException){
			objectMapper.writeValue(response.getWriter(),"登录密码错误!!!" );
		}
		if(authenticationException instanceof UsernameNotFoundException){
			objectMapper.writeValue(response.getWriter(), "用户不存在!");
		}else{
			objectMapper.writeValue(response.getWriter(), "未知错误！！！");
		}

	}

}
