<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录界面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<jsp:include page="/js/base.jsp"></jsp:include>
<script>

$(function() {
	function getTree() {
		var tree = [{text: "后台管理",
		             nodes: [{text: "用户管理"},{text: "菜单管理"}]
		            },
		            {text: "菜单"}];                  

	    return tree;
	}

	$('#tree').treeview({
		data: getTree(),
		backColor:'#D2E9FF',
		});
	
})

</script>
<style>
html,body,div{height:100%;font-size:10px}
</style>
<body>

<div style="width:15%;background-color:#D2E9FF;font-size:20px">
<div id="tree"   ></div>

</div>

</body>
</html>
