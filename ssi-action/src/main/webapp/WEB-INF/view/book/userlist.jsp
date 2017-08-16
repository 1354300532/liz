<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- datagrid -->
	<table id="user_dg"></table>

	<script type="text/javascript">
	$(function() {
		init_user_dg();
	});

		//初始化数据表格
	function init_user_dg() {
		$('#user_dg').bootstrapTable({
			url:"<%=request.getContextPath() %>/selectUserListJson.jk",
			dataType:"json",
			//请求方式
			method:"post",
			//必须的，操你大爷！！！！不然会造成中文乱码
			contentType: "application/x-www-form-urlencoded",
			//斑马线
			striped:true,
			//设置分页
			pagination:true,
			paginationLoop:true,
			pageNumber:1,
			pageSize:5,
			pageList:[3,5,8,10], 
			//工具条
			// toolbar:"#book_tb", 
			//设置后台分页
			sidePagination:"server", 
			//开启搜索框
			search:true, 
			//显示刷新按钮
			showRefresh:true,
		    columns: [{
		        checkbox:true
		    }, {
		        field: 'bookID',
		        title: '主键'
		    }, {
		        field: 'bookName',
		        title: '账号'
		    }, {
		        field: 'cz',
		        title: '操作',
		        formatter:function(value, row, index) {
		        	var zc_btn_group = '<div class="btn-group">'
		        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_dialog(\'' + row.bookID + '\')">编辑</button>'
		        	+ '</div>&nbsp;&nbsp;'
		        	+ '<div class="btn-group">'
		        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_book(\'' + row.bookID + '\')">删除</button>'
		        	+ '</div>&nbsp;&nbsp;'
		        	+ '<div class="btn-group">'
		        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_user_role(\'' + row.bookID + '\')">角色操作</button>'
		        	+ '</div>';
		        	return zc_btn_group;
		       }
			  },<%-- {
		        field: 'userPwd',
		        title: '密码'
		    }, {
		        field: 'bookType',
		        title: '书籍类型'
		    }, {
		        field: 'date',
		        title: '出版日期',
		        //width:92,
		        formatter:formatDatebox
		    }, {
		        field: 'bookAuthor',
		        title: '书籍封面',
		        formatter:function(value, row, index) {
		        	var zc_btn_group = '<img src="<%=request.getContextPath() %>/images/def/01.JPG" width="60" height="28"/>';
		        	if (null != row.attachID && "" != row.attachID) {
		        		zc_btn_group = '<img src="<%=request.getContextPath() %>/book/loadMainImg.action?book.attachID=' + row.attachID + '" width="60" height="28"/>';
		        	}
		        	return zc_btn_group;
		        }
		    }, {
		        field: 'cz',
		        title: '操作',
		        formatter:function(value, row, index) {
		        	var zc_btn_group = '<div class="btn-group">'
		        	+ '<button type="button" class="btn btn-xs btn-success" onclick="show_edit_dialog(\'' + row.bookID + '\')">编辑</button>'
		        	+ '</div>&nbsp;&nbsp;'
		        	+ '<div class="btn-group">'
		        	+ '<button type="button" class="btn btn-xs btn-danger" onclick="delete_checked_book(\'' + row.bookID + '\')">删除</button>'
		        	+ '</div>';
		        	return zc_btn_group;
		        }
		    } --%>]
		});
		}
		
		
	//编辑用户的角色
	function edit_user_role(bookid) {
		BootstrapDialog.show({
			title:"用户管理>>用户赋角色",
			message: $('<div></div>').load('<%=request.getContextPath() %>/toUserRolePage.jk?bookID=' + bookid),
			buttons: [{
                label: '确定',
                cssClass:"btn btn-success",
                action: function(dialogItself){
                	var role_json_array = get_selection_tree_nodes();
                	//使用ajax保存结果
                	$.ajax({
                		url:"<%=request.getContextPath() %>/insertUserRoleList.jk",
                		data:JSON.stringify(role_json_array),
                		dataType:"json",
                		type:"post",
                		success:function(data) {
                			//关闭对话框
                			dialogItself.close();
                		},
                		contentType:"application/json"
                	});
                }
            }, {
                label: '取消',
                cssClass:"btn btn-danger",
                action: function(dialogItself){
                	dialogItself.close();
                }
            }]
		});
	}
	</script>
</body>
</html>