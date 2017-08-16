package com.jk.entity.book;

public class UserRequest extends User{
	
	private static final long serialVersionUID = -76607894191520830L;

	private String userImgCode;
	
	private String sysImgCode;

	public String getUserImgCode() {
		return userImgCode;
	}

	public void setUserImgCode(String userImgCode) {
		this.userImgCode = userImgCode;
	}

	public String getSysImgCode() {
		return sysImgCode;
	}

	public void setSysImgCode(String sysImgCode) {
		this.sysImgCode = sysImgCode;
	}
	
	
}
