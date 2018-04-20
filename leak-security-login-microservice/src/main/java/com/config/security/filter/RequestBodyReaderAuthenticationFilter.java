package com.config.security.filter;

import com.config.security.dto.LoginRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.IOUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RequestBodyReaderAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
	 
    //private static final Log LOG = LogFactory.getLog(RequestBodyReaderAuthenticationFilter.class);
 
    private static final String ERROR_MESSAGE = "Something went wrong while parsing /login request body";
 
    private final ObjectMapper objectMapper = new ObjectMapper();
   
    private    AuthenticationManager  authenticationManager ;
    
    
    /**
     * 自定义一个antMatcher了并注入进来
     */
    public RequestBodyReaderAuthenticationFilter() {
    	AntPathRequestMatcher requestMatcher=new AntPathRequestMatcher("/login", "GET");
    	this.setRequiresAuthenticationRequestMatcher(requestMatcher);
    }
 
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String requestBody;
        // requestBody = IOUtils.toString(request.getReader());
        	UsernamePasswordAuthenticationToken token = null;
			try {
				//String username = (String) request.getAttribute("user");
				
				String params = request.getQueryString();
				System.out.println("queryString is ... :"+params);
				System.out.println("----------------------------");
				String username = 	request.getParameter("username");
				System.out.println("------------参数  username的值:"+username);
				String password=(String)request.getParameter("password");
				System.out.println("------------参数  password的值:"+password);
				BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
				String body = IOUtils.toString(reader);
			//	System.out.println("..........服务器接收到的参数是      :"+body);
				//LoginRequest authRequest = objectMapper.readValue(requestBody, LoginRequest.class);
				LoginRequest authRequest =new LoginRequest();
				authRequest.setUsername(username);
				authRequest.setPassword(password);
				//我们看UsernamePasswordAuthenticationToken的构造方法
				// UsernamePasswordAuthenticationToken(Object principal, Object credentials)
				System.out.println("----------:token对象的principal是:"+authRequest.getUsername()+"---"+authRequest.getPassword());
				token = new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword());
                System.out.println("生成的token是:"+token.toString());
				// Allow subclasses to set the "details" property
				setDetails(request, token);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
 
            return this.getAuthenticationManager().authenticate(token);
    }
}
