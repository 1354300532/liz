/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:Menu.java 
 * 包名:com.jk.pojo.menu 
 * 创建日期:2017年7月26日下午3:38:05 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book.menu;

/** 
 * <pre>项目名称：ssi-pojo-01    
 * 类名称：Menu    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月26日 下午3:38:05    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月26日 下午3:38:05    
 * 修改备注：       
 * @version </pre>    
 */
public class Menu {
	
	private int menuID;
	
	private String url;
	
	private int pid;

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getMenuID() {
		return menuID;
	}

	public void setMenuID(int menuID) {
		this.menuID = menuID;
	}

}
