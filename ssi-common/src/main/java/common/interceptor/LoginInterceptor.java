package common.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.druid.util.StringUtils;

public class LoginInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("天王盖地虎，小鸡炖蘑菇");
		//获取session对象
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		
		//获取访问的url地址
		String requestURI = request.getRequestURI();
		System.out.println(requestURI);
		
		String queryString = request.getQueryString();
		System.out.println(queryString);
		
		//获取客户端主机
		String remoteHost = request.getRemoteHost();
		System.out.println(remoteHost);
		
		//判断用户是否登陆过
				if (null != session.getAttribute("userInfo")) {
					//已登陆
					return true;
				} else {
					//未登录，重定向页面到登陆页面
					//判断是否是ajax请求
					
					String type = request.getHeader("X-Requested-With");// XMLHttpRequest
					// 重定向
					String path = request.getContextPath();
					String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";
					// 转发
					if (StringUtils.equals("XMLHttpRequest", type)) {
						// ajax请求
						response.setHeader("SESSIONSTATUS", "TIMEOUT");
						response.setHeader("CONTEXTPATH", basePath+"login.jsp");
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
					} else {
						//常规
						response.sendRedirect(request.getContextPath() + "/login.jsp");
					}
				}
				// true:继续执行;false:返回前台
				return false;
	}
}
