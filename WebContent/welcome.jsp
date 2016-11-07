<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<title>登录界面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>

<body style="background-color: #D2E9FF">

<div class="center-block" style="width: 500px;background-color: red;line-height:100px">
ssssssss
</div>

	<div class="center-block" style="width: 500px; margin-top: 150px">
		<ul id="myTab" class="nav nav-pills nav-justified">
			<li class="active"><a href="#home" data-toggle="tab"> 登录</a></li>
			<li><a href="#ios" data-toggle="tab">注册</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<form class="form-horizontal" role="form" >
					<div class="form-group"  >
						<label for="username" class="col-sm-2 control-label" >用户名</label>
						<div class="col-sm-10" style="background-color: red;line-height:100px">
							<input type="text" class="form-control" id="username" 
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">姓&emsp;名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name"
								placeholder="请输入姓名">
						</div>
					</div>
					
				<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密&emsp;码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="password"
								placeholder="请输入密码">
						</div>
					</div>
				
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>
			<div class="tab-pane fade" id="ios">
				<form class="form-horizontal" role="form">
					<div class="form-group">
						<label for="firstname" class="col-sm-2 control-label">名字</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="firstname"
								placeholder="请输入名字">
						</div>
					</div>
					<div class="form-group">
						<label for="lastname" class="col-sm-2 control-label">姓</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="lastname"
								placeholder="请输入姓">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<div class="checkbox">
								<label> <input type="checkbox">请记住我
								</label>
							</div>
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">登录</button>
						</div>
					</div>
				</form>
			</div>
		</div>

	</div>


</body>
<script>
	$(function() {
		$('#myTab li:eq(0) a').tab('show');
	});
</script>
</html>
