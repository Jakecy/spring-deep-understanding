package com.model;



import java.io.Serializable;


/**
 * @table  account
 */
public class Account implements Serializable {

	private static final long serialVersionUID = -4613896877313600753L;
	private Integer id;// id

	private String mobile;

	private String userPwd;

	private String pwdSalt;

	private Integer status;

	/**
	 * VO
	 */
	private String accountName;

	private String realName;

	private Integer pid;

	private String headerPath;

	private Boolean providerInfo;

	public Boolean getProviderInfo() {
		return providerInfo;
	}

	public void setProviderInfo(Boolean providerInfo) {
		this.providerInfo = providerInfo;
	}

	public String getHeaderPath() {
		return headerPath;
	}

	public void setHeaderPath(String headerPath) {
		this.headerPath = headerPath;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getAccountName() {
		return accountName;
	}

	public String getAccount() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getMobile() {
		return this.mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getUserPwd() {
		return this.userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public String getPwdSalt() {
		return this.pwdSalt;
	}

	public void setPwdSalt(String pwdSalt) {
		this.pwdSalt = pwdSalt;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", mobile=" + mobile + ", userPwd=" + userPwd + ", pwdSalt=" + pwdSalt
				+ ", status=" + status + "]";
	}

}