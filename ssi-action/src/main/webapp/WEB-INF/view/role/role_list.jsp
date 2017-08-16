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
	<table id="role_dg"></table>
	
<script type="text/javascript">
	$(function() {
		init_role_dg();
	});

	function init_role_dg() {
		//初始化数据表格
		$('#role_dg').bootstrapTable({
			url:"<%=request.getContextPath() %>/selectRoleListJson.jk",
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
			toolbar:"#role_tb",
			//设置后台分页
			sidePagination:"server",
			//开启搜索框
			/* search:true, */
			//显示刷新按钮
			showRefresh:true,
			//拼接查询参数
			//queryParams:function(params) {
				//params.userAccount = $("#query_user_account").val();
			//	console.log(params);
			//	return params;
			//},
			//queryParamsType:"",
		    columns: [{
		        checkbox:true
		    }, {
		        field: 'roleID',
		        title: '主键'
		    }, {
		        field: 'roleName',
		        title: '角色名称'
		    }, {
		        field: 'roleDesc',
		        title: '角色描述'
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
		        	+ '<button type="button" class="btn btn-xs btn-success" onclick="edit_role_menu(\'' + row.roleID + '\')">菜单操作</button>'
		        	+ '</div>';
		        	return zc_btn_group;
		        }
		    },]
		});
	}
	
	//编辑用户的角色
	function edit_role_menu(role_id) {
		BootstrapDialog.show({
			title:"角色管理>>角色赋权限",
			message: $('<div></div>').load('<%=request.getContextPath() %>/toRoleMenuPage.jk?roleID=' + role_id),
			buttons: [{
                label: '确定',
                cssClass:"btn btn-success",
                action: function(dialogItself){
                	var menu_json_array = get_selection_tree_nodes();
                	//使用ajax保存结果
                	$.ajax({
                		url:"<%=request.getContextPath() %>/insertRoleMenuList.jk",
                		data:JSON.stringify(menu_json_array),
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