package com.config.security;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.UserService;
import com.config.security.filter.RequestBodyReaderAuthenticationFilter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	/**
	 * 把自定义的对象放入config中，
	 * 以便于config使用
	 */
	
	@Autowired
    private  UserService userService;
	@Autowired
    private  ObjectMapper objectMapper;
	
   @Autowired
   private  PasswordEncoder passwordEncoder;
	
	
   @Bean
   public RequestBodyReaderAuthenticationFilter authenticationFilter() throws Exception {
       RequestBodyReaderAuthenticationFilter authenticationFilter
           = new RequestBodyReaderAuthenticationFilter();
       authenticationFilter.setAuthenticationSuccessHandler(this::loginSuccessHandler);
       authenticationFilter.setAuthenticationFailureHandler(this::loginFailureHandler);
       authenticationFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "GET"));
       authenticationFilter.setAuthenticationManager(authenticationManagerBean());
       return authenticationFilter;
   }
   /**
    * 以方法的方式注入一个authenticationProvider对象
    * @param request
    * @param response
    * @param authentication
    * @throws IOException
    */
   @Bean
   public DaoAuthenticationProvider authProvider() {
       DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
       authProvider.setUserDetailsService(userService);
       authProvider.setPasswordEncoder(passwordEncoder);
       return authProvider;
   }
   /**
    * 在这里增加一个全局配置：向authenticationManager中放入我们自定义的authenticationProvider
    */
   @Autowired
   public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
      //这里使用的是方法注入的方式来注入对象
	  //authProvider()方法
	   auth.authenticationProvider(authProvider());
   }

   /**
    * 把配置代码写到configure中
    * security会自动应用configure()中的配置
    */
   @Override
   protected void configure(HttpSecurity http) throws Exception {
       http
        
           .csrf().disable()
           .authorizeRequests()
           .anyRequest().authenticated()
           .and()  //这里的and()就是起到一个结束符的作用
           .authorizeRequests()
		   .antMatchers("/login**").permitAll()

//           .and()
//           .formLogin()
//           .loginProcessingUrl("/login")
//           .usernameParameter("login")
//           .passwordParameter("password")
//           .successHandler(this::loginSuccessHandler)
//           .failureHandler(this::loginFailureHandler)

           .and()
           .addFilterBefore(
               authenticationFilter(),
               UsernamePasswordAuthenticationFilter.class)
           .logout()
           .logoutUrl("/logout")
           .logoutSuccessHandler(this::logoutSuccessHandler)

           .and()
           .exceptionHandling()
           .authenticationEntryPoint(new Http401AuthenticationEntryPoint("401"));
   }
   
   
   /**
    * 此处增加一个安全配置方法
    * @param request
    * @param response
    * @param authentication
    * @throws IOException
    */
   public WebSecurityConfig(UserService userService, ObjectMapper objectMapper, PasswordEncoder passwordEncoder) {
       this.userService = userService;
       this.objectMapper = objectMapper;
       this.passwordEncoder = passwordEncoder;
   }
   
   

   
   
   
   private void loginSuccessHandler(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        Authentication authentication) throws IOException {
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
	 
	    private void loginFailureHandler(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        AuthenticationException e) throws IOException {
	    	   //设置响应格式
    	   response.setHeader("Content-type", "text/html;charset=UTF-8");
 	       response.setCharacterEncoding("UTF-8");
    	   response.setStatus(HttpStatus.OK.value());
	        response.setStatus(HttpStatus.UNAUTHORIZED.value());
	        objectMapper.writeValue(response.getWriter(), "用户不存在!");
	    }
	 
	    private void logoutSuccessHandler(
	        HttpServletRequest request,
	        HttpServletResponse response,
	        Authentication authentication) throws IOException {
	    	//当流程完整地从AuthenticationFilter中出来之后，我们会得到一个Authentication对象
	    	//我现在要看的是这个authentication对象的isAuthenticated属性
	    	System.out.println("-----------------Authentication对象-------");
	    	System.out.println("------------我们现在得到的authentication对象的isAuthenticated属性值是:"+authentication.isAuthenticated());
	 
	        response.setStatus(HttpStatus.OK.value());
	        objectMapper.writeValue(response.getWriter(), "Bye!");
	    }
    

}
