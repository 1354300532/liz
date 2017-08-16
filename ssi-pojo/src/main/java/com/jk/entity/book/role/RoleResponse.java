/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:RoleResponse.java 
 * 包名:com.jk.pojo.role 
 * 创建日期:2017年7月25日下午12:18:14 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book.role;

/** 
 * <pre>项目名称：ssi-pojo-01    
 * 类名称：RoleResponse    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月25日 下午12:18:14    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月25日 下午12:18:14    
 * 修改备注：       
 * @version </pre>    
 */
public class RoleResponse extends Role {
	
	private int id;
	
	private String name;
	
	private int pid;
	
	private boolean checked;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPid() {
		return pid;
	}

	public void setPid(int pid) {
		this.pid = pid;
	}

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
