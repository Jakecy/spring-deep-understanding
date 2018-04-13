package com.jakecy.service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



/**
 * 把spring security的组件注入到该Configuration中
 * @author dell
 *
 */
@Configuration
public class MySecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserAuthenticationFilter   loginAuthenticationFilter;
	
	@Autowired
	private MyLoginAuthenticationProvider  loginAuthenticationProvider;
	

	/**
	 * 现在我们通过重写configure方法来达到自定义配置的目的
	 * 可以看出此从的configure就是把我们自定义的组件，告知security
	 * 这样security才能使用
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//super.configure(http);
		//把自定义的filter放入security中
		//把我们的自定义的authenticationProvider放入到Filter当中
		http.
		authorizeRequests()
			.antMatchers("/login").permitAll().and().addFilterAt(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class).authenticationProvider(loginAuthenticationProvider);
		 http.csrf().disable();
		
		
	}

	
}
