package com.jakecy.service.config;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

@Component("myAuthenticationFailtureHandler")
public class MyAuthenticationFailtureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		//
	   PrintWriter  out=null;
	   out= response.getWriter();
	   //把数据告知客户端浏览器
	   out.write("Unknown Error");
	   out.close();
	}

}
