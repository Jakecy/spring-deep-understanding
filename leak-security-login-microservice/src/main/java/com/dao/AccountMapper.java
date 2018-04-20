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
	 * @Title: selectAccountIdByTelephones   
	 * @Description: 根据手机号列表查询账号ID列表  
	 * @param selectAccountIdByTelephones
	 * @return List<Integer>  
	 * @throws
	 */
	List<Integer> selectAccountIdByTelephones(@Param("telephones") List<String> telephones);

	List<Integer> selectAccountIdByArea(@Param("provinceList") List<Integer> provinceList,
			@Param("cityList") List<Integer> cityList, @Param("districtList") List<Integer> districtList,
			@Param("accountTypeInt") Integer accountTypeInt);

	/**
	 * @Title: selectPlatAccountByMobile   
	 * @Description: 根据手机号查询平台账号的数量（平台账号包括1服务商后台账号2APP端师傅账号(服务站下的工程师)3服务站店长（服务站下添加的店长）5大鱼运营后台账号）  
	 * @param mobile 手机号
	 * @return int  
	 * @throws
	 */
	int selectPlatAccountByMobile(@Param("mobile") String mobile);

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
