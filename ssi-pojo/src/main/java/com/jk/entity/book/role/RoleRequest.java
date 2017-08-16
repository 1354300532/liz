/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:RoleRequest.java 
 * 包名:com.jk.pojo.role 
 * 创建日期:2017年7月25日下午12:17:56 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book.role;

/** 
 * <pre>项目名称：ssi-pojo-01    
 * 类名称：RoleRequest    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月25日 下午12:17:56    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月25日 下午12:17:56    
 * 修改备注：       
 * @version </pre>    
 */
public class RoleRequest extends Role {
	
	private static final long serialVersionUID = 1L;
	private int userID;
	
	private Integer bookID;
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public Integer getBookID() {
		return bookID;
	}

	public void setBookID(Integer bookID) {
		this.bookID = bookID;
	}



}
