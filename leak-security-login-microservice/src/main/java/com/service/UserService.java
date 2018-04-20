package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.config.security.dto.AccountDetail;
import com.dao.AccountMapper;
import com.model.Account;

import java.util.ArrayList;
import java.util.Collection;
 
@Service("userService")
public class UserService implements UserDetailsService {
 
   /**
    * 把加密器注入其中
    */
   @Autowired
   private  PasswordEncoder passwordEncoder;
   @Autowired
   private AccountMapper 	accountMapper;
	
	
    /**
     * 在loadUserByUsername中当获取到数据库中的用户名和密码之后，
     * 把加密后的userDetails对象返回给userService的调用者
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByUsername()方法的参数user是:"+username);
        //
        //从库中获取账户类型信息
        Account account = accountMapper.selectByMobile(username);
        //
        if(account==null){
        	 throw new UsernameNotFoundException("User not found with login: " + username);
        }
        //把库中的用户信息返回
        AccountDetail aDetail=new AccountDetail(account.getMobile(), account.getUserPwd(), account.getPwdSalt());
        System.out.println("info getted from the db is : "+account.toString());
        //判断用户名和密码是否
        if(!"".equals(username)) {
        	/**
        	 * 此处使用encode来加密本真密码
        	 */
    		//在这里把Authentication对象打印出来
    		//把查询出的用户信息返回
            return aDetail;
        } else {
            throw new UsernameNotFoundException("User not found with login: " + username);
        }
    }
}
