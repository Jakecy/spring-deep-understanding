package com.config.security.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component("customLoginSuccessHandler")
public class CustomLoginSuccessHandler implements AuthenticationSuccessHandler {

	
	@Autowired
    private  ObjectMapper objectMapper;
	
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		  //当流程完整地从AuthenticationFilter中出来之后，我们会得到一个Authentication对象
	   	   //我现在要看的是这个authentication对象的isAuthenticated属性
	   	   System.out.println("-----------------Authentication对象-------");
	   	   System.out.println("------------我们现在得到的authentication对象的isAuthenticated属性值是:"+authentication.isAuthenticated());
		        
	   	   //设置响应格式
	   	   response.setHeader("Content-type", "text/html;charset=UTF-8");
		   response.setCharacterEncoding("UTF-8");
	   	   response.setStatus(HttpStatus.OK.value());
		        
		   objectMapper.writeValue(response.getWriter(), "Yayy you logged in!");
	}

}
