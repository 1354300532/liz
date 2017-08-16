<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>金科教育</title>
  
    <!-- Bootstrap -->
    <link href="<%=request.getContextPath() %>/js/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- tab页（选项卡）组件 -->
    <link href="<%=request.getContextPath() %>/js/bootStrap-addTabs/bootstrap.addtabs.css" rel="stylesheet">

	<link href="<%=request.getContextPath() %>/js/bootstrap-table/dist/bootstrap-table.min.css" rel="stylesheet">
	<link href="<%=request.getContextPath() %>/js/bootstrap-dialog/dist/css/bootstrap-dialog.min.css" rel="stylesheet">
	<link type="text/css" rel="stylesheet" href="<%=request.getContextPath() %>/js/bootstrap-fileinput/css/fileinput.css" />

	<link href="<%=request.getContextPath() %>/js/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	
	<!-- ztree样式 -->
	<%-- <link rel="stylesheet" href="<%=request.getContextPath() %>/js/ztree_v3/css/demo.css" type="text/css"> --%>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/js/ztree_v3/css/zTreeStyle/zTreeStyle.css" type="text/css">
	
	
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
      <script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style type="text/css">
	.lie {
		/*height:300px;*/
		background: #ffffee;
	}
	.navbar {
		height: 52px;
		background: #000000;
	}
	.container-fluid {
		top: 48px;
		position: relative;
	}
	#nav_h3 {
		text-align:center;
		color: gray;
	}
	
</style>
</head>
<body>
<!-- 导航条 -->
	<nav class="navbar navbar-default navbar-fixed-top">
		<h3 id="nav_h3">金科教育企业平台</h3>
	</nav>
	
	<div class="container-fluid">
		<!-- 栅格系统 -->
		<div class="row">
			<!-- 添加列 -->
			<div class="col-xs-3 lie">
				<!-- 添加列表组（树菜单） -->
				<div id="tree"></div>
			</div>
			<div class="col-xs-9 lie">
				<div id="content-div">
					<!-- 选项卡 -->
					<div id="tabs">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs" role="tablist">
                            <li role="presentation" class="active">
                                <a href="#home" aria-controls="home" role="tab" data-toggle="tab">主页</a></li>
                        </ul>
                        <!-- Tab panes（放置结果页面） -->
                        <div class="tab-content">
                        </div>
                    </div>
				</div>
			</div>
		</div>
	</div>
	
		<!-- jquery -->
	<script src="<%=request.getContextPath() %>/js/jquery.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap/js/bootstrap.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootStrap-addTabs/bootstrap.addtabs.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/bootstrap-treeview/dist/bootstrap-treeview.min.js"></script>
	   
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-table/dist/bootstrap-table.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-table/dist/locale/bootstrap-table-zh-CN.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-dialog/dist/js/bootstrap-dialog.min.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-fileinput/js/fileinput.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-fileinput/js/locales/zh.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
	
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/dateformat/formatDatebox.js"></script>
	
	<!-- 加载ajax重定向设置文件 -->
	<script type="text/javascript" src="js/ajaxSetup.js"></script>
	
	<!-- ztree插件 -->
	<script type="text/javascript" src="js/ztree_v3/js/jquery.ztree.all.min.js"></script>
	<script type="text/javascript">
	var tree_data = [
	                 	{text:"用户管理",
	                 		href:"<%=request.getContextPath() %>/toUserListPage.jk",
	                 		state: {
	                 		    selected: true
	                 		  },},
	                 	{text:"角色管理",
	                 		href:"<%=request.getContextPath() %>/toRoleListPage.jk",},
	                 	{text:"品牌管理",
	                 		href:"sdsds",},
	                 	{text:"员工管理",
	                 		href:"sdsds",},
	                 	{text:"部门管理",
	                 		href:"sdsds",},
	                 ];
	
	//初始化树
	$('#tree').treeview({
		data:tree_data,
		onNodeSelected:function(event, node) {
			if (null != node.href && "" != node.href) {
				//发送ajax请求
        		$.ajax({
        			url:node.href,
        			success:function(data) {
        				$.addtabs.add({id:node.text,title:node.text,content:data});
        			}
        		});
			}
		}
	});
	
	//获取菜单数据
	function getTreeData() {
		var tree_data = [];
		//发送ajax请求
		$.ajax({
			async:false,//请求为同步
			url:"<%=request.getContextPath() %>/selectTreeListJson.jk",
			data:{userID:"${userInfo.userID }"},
			dataType:"json",
			success:function(data) {
				tree_data = data;
			}
		});
		return tree_data
	}
	</script>
</body>
</html>