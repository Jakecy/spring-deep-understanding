package com.dao;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.model.Account;


public interface AccountMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Account account);

	Account selectByPrimaryKey(Integer id);

	Account selectByUsername(String username);

	int updateByPrimaryKey(Account account);

	List<Account> selectAccountList(Account account);


	/**
	 * @Title: selectByMobile   
	 * @Description: 根据手机号查询账号  
	 * @param mobile
	 * @return Account  
	 * @throws
	 */
	Account selectByMobile(@Param("mobile") String mobile);

	/**
	 * @Title: selectPlatAccount   
	 * @Description: 根据手机号查询 平台账号  （1服务商2工程师3店长）
	 * @param mobile
	 * @return Account  
	 * @throws
	 */
	Account selectPlatAccount(@Param("mobile") String mobile);


}
