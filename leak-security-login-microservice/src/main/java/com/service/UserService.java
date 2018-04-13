package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
 
import java.util.ArrayList;
import java.util.Collection;
 
@Service("userService")
public class UserService implements UserDetailsService {
 
   /**
    * 把加密器注入其中
    */
   @Autowired
   private  PasswordEncoder passwordEncoder;
	
	
    /**
     * 在loadUserByUsername中当获取到数据库中的用户名和密码之后，
     * 把加密后的userDetails对象返回给userService的调用者
     */
    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        if("user".equals(login)) {
        	/**
        	 * 此处使用encode来加密本真密码
        	 */
            return new User(login, passwordEncoder.encode("password"), (Collection<? extends GrantedAuthority>) new ArrayList<GrantedAuthority>());
        } else {
            throw new UsernameNotFoundException("User not found with login: " + login);
        }
    }
}
