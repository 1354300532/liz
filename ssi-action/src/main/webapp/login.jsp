<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- 代码开始 -->
	<script src="js/canvas-nest.js" count="200" zindex="-2" opacity="0.5" color="47,135,193" type="text/javascript"></script>
	<center>
	<form id="login_form">
		账号：&nbsp;<input name="userAccount"><br>
		密码：&nbsp;<input type="password" name="userPwd"><br>
		验证码：<input name="userImgCode"><br>
		&nbsp;&nbsp;&nbsp;<span onclick="change_imgcode()">
			<img id="imgcode_src_node" src="<%=request.getContextPath() %>/imgcode">
			<font color="green">点击换一张</font>
		</span>
		</br>
		<input type="button" value="登陆" onclick="user_login()">
	</form>
	</center>
	<!-- 代码结束 -->
	
	<script type="text/javascript">
		
		//ajax登陆
		function user_login() {
			$.ajax({
				url:"<%=request.getContextPath() %>/login.jk",
				data:$("#login_form").serialize(),
				type:"post",
				dataType:"json",
				success:function(data) {
					console.log(data);
					if (1 == data.flag) {
						//成功
						location.href = "index.jsp";
						return;
					}
					if (2 == data.flag) {
						alert("用户不存在");
						return;
					}
					if (3 == data.flag) {
						alert("密码错误" + data.loginfailnum);
						return;
					}
					if (4 == data.flag) {
						alert("验证码错误");
						return;
					}
					if (5 == data.flag) {
						alert("验证码为空");
						return;
					}
					if (6 == data.flag) {
						alert("账号锁定");
						return;
					}
				}
			});
		}
	
		//切换验证码
		function change_imgcode() {
			$("#imgcode_src_node").attr("src", "<%=request.getContextPath() %>/imgcode?time=" + new Date().getTime());
		}
		
	</script>
</body>
</html>