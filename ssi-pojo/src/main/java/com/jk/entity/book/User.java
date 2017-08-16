package com.jk.entity.book;

import java.io.Serializable;

import com.google.gson.annotations.Expose;

import common.interceptor.util.Page;

public class User extends Page implements Serializable{
	
	private static final long serialVersionUID = -2803469691306090399L;

	private int userID;
	
	private String userAccount;
	
	@Expose
	private String userPwd;

	private int loginFailNum;
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getUserPwd() {
		return userPwd;
	}

	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}

	public int getLoginFailNum() {
		return loginFailNum;
	}

	public void setLoginFailNum(int loginFailNum) {
		this.loginFailNum = loginFailNum;
	}
}
