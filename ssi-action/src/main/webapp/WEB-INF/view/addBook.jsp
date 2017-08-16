<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="jquery/jquery-1.10.2.js"></script>
<link rel="stylesheet" type="text/css" href="uploadify/uploadify.css" />
<script type="text/javascript" src="uploadify/jquery.uploadify.min.js"></script>
</head>
<body>
	<form action="<%=request.getContextPath()%>/insertBook.jk" method="post">
	<input type="hidden" name="bookID" value="${book.bookID}">
	<input type="hidden" name="bookPhoto" id="myUploadImg23" value="${book.bookPhoto }">
		<table border = "1">
			<tr>
				<td>书籍名称</td>
				<td><input type = "text" name = "bookName" value="${book.bookName}"></td>
			</tr>
			<tr>
				<td>书籍封面</td>
				<td style="padding-left: 60px">
					<img src="${book.bookPhoto}" id="myUploadImg" style="width: 120px"/>
					<input type="file" id="upLoadImg" name="file"/>
				</td>
			</tr>
		</table>
		<input type = "submit" value =  "提交">
	</form>
</body>
<script type="text/javascript">
	
$(function(){
	uoLoad();
})
/*
上传图片
*/
function uoLoad(){
$("#upLoadImg").uploadify({
	'swf':'uploadify/uploadify.swf',//swf uploadify的控制展示属性 flash基础文件 上传的进度条 和上传按钮功能
	'uploader':'uploadPhoto.jk',//声明文件的上传地址 上传到对应的action请求
	'buttonText':'上传海报',
	'mulit':false,
	'fileTypeDesc':'只能上传图片',
	'fileTypeExts':'*.jpg;*.png',
	'fileObjName':'file',
	'onUploadSuccess':function(response,data){//第二个参数为后台返回的数据
	alert(data);
	   //替换图片原有路径 达到上传图片预览的目的
		 $("#loadPhotos").val(data);
	    $("#myUploadImg").attr("src",data);
	    $("#myUploadImg23").val(data);
		
	}
})
}
</script>
</html>