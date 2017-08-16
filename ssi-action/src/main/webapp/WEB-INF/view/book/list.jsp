<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery/jquery-1.10.2.js"></script>
</head>
<body>
<center>
	<table border = "1">
		<tr>
			<th><input type="checkbox"></th>
			<td>书籍ID</td>
			<td>书籍名称</td>
			<td>书籍封面</td>
			<td>操作</td>
		</tr>
		<c:forEach var="book" items="${list}">
		<tr>
			<th><input type="checkbox" name="ids" value="${book.bookID}"></th>
			<td>${book.bookID}</td>
			<td>${book.bookName}</td>
			<td><img src="${book.bookPhoto}" width="100px"></td>
			<td>
				<input type="button" value="删除" onclick="toRemove(${book.bookID})">
			</td>
		</tr>
		</c:forEach>
	</table>
				<input type="button" value="修改" onclick="toUpdate()">
	<a href="<%=request.getContextPath()%>/toAddBook.jk">新增</a>
	</center>
</body>
<script type="text/javascript">
	function toUpdate(){
		var arr = $("[name='ids']:checked"); 
		if(arr.length==1){
			
		var id = arr[0].value;
		location.href="<%=request.getContextPath()%>/querytoUpdateBook.jk?bookID="+id;
		}else{
			alert("请选择一条")
		}	
	}
	
	function toRemove(id){
		location.href="<%=request.getContextPath()%>/deleteBook.jk?bookID="+id;
	}
</script>
</html>