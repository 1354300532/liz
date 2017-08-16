/** 
 * <pre>项目名称:ssi-01 
 * 文件名称:UserResponse.java 
 * 包名:com.jk.pojo.user 
 * 创建日期:2017年7月17日上午11:00:42 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book;

/**
 * 
 * <pre>项目名称：test-02    
 * 类名称：UserResponse    
 * 类描述：    
 * 创建人：李智  
 * 创建时间：2017年7月20日 下午12:01:49    
 * 修改人：李智    
 * 修改时间：2017年7月20日 下午12:01:49    
 * 修改备注：       
 * @version </pre>
 */
public class UserResponse extends User {
	private static final long serialVersionUID = 1L;

	
	private long loginFailDate;


	public long getLoginFailDate() {
		return loginFailDate;
	}

	public void setLoginFailDate(long loginFailDate) {
		this.loginFailDate = loginFailDate;
	}
	
}
