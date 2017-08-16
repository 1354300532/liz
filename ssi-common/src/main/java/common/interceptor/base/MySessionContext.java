/** 
 * <pre>项目名称:ssi-03 
 * 文件名称:MySessionContext.java 
 * 包名:common.base 
 * 创建日期:2017年7月21日下午4:28:37 
 * Copyright (c) 2017, chenjh123@gmail.com All Rights Reserved.</pre> 
 */  
package common.interceptor.base;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.google.gson.JsonParser;

/**
 * 
 * <pre>项目名称：test-02    
 * 类名称：MySessionContext    
 * 类描述：    
 * 创建人：李智  
 * 创建时间：2017年7月21日 下午7:29:40    
 * 修改人：李智    
 * 修改时间：2017年7月21日 下午7:29:40    
 * 修改备注：       
 * @version </pre>
 */
public class MySessionContext {
	
	private static Map<String, HttpSession> sessionMap = new HashMap<String, HttpSession>();
	
	public static void addSession(String userID, HttpSession session) {
		sessionMap.put(userID, session);
	}
	
	public static void removeSession(String userID) {
		HttpSession httpSession = sessionMap.get(userID);
		if (null != httpSession) {
			//使原来存在的session失效
			//判断session是否失效，没有失效的让他失效
			String json = new Gson().toJson(httpSession);
			boolean valid = new JsonParser().parse(json).getAsJsonObject()
					.get("session").getAsJsonObject()
					.get("isValid").getAsBoolean();
			if (valid) {
				httpSession.invalidate();
			}
		}
	}
	
	public static HttpSession getSession(String userID) {
		return sessionMap.get(userID);
	}

}
