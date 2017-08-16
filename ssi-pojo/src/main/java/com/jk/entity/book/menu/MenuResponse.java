/** 
 * <pre>项目名称:ssi-pojo-01 
 * 文件名称:MenuResponse.java 
 * 包名:com.jk.pojo.menu 
 * 创建日期:2017年7月26日下午3:38:39 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package com.jk.entity.book.menu;

/** 
 * <pre>项目名称：ssi-pojo-01    
 * 类名称：MenuResponse    
 * 类描述：    
 * 创建人：陈教授 chenjh123@gmail.com    
 * 创建时间：2017年7月26日 下午3:38:39    
 * 修改人：陈教授 chenjh123@gmail.com     
 * 修改时间：2017年7月26日 下午3:38:39    
 * 修改备注：       
 * @version </pre>    
 */
public class MenuResponse extends Menu {
	
	private int id;
	
	private String name;
	
	private boolean checked;
	
	private boolean isParent;

	public boolean isParent() {
		return isParent;
	}

	public void setParent(boolean isParent) {
		this.isParent = isParent;
	}

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

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

}
