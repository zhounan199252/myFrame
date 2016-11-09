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
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
</head>
<script>


$(function() {
	
	$('#form_login').keydown(function(event) { // 回车事件（登陆）
		if (event && event.which == 13) {
			$('#but_register').click();
			return false;
		}
		return true;	
		
	});
	$('#form_register').keydown(function(event) { // 回车事件（登陆）
		if (event && event.which == 13) {
			$('#but_register').click();
			return false;
		}
		return true;	
		
	});
	
	
	// jqueryValidate 表单验证开始
	$("#form_register").validate({
		submitHandler:function(form){
		$(form).ajaxSubmit({
				type : "post",
                success:function(data){	
                $("#but_register>div").remove(".alert");
				$("#but_register").after("<div class='alert alert-success'>"+data+"</div>");
				$("#form_register").clearForm();}
       });  
        } ,   
		errorPlacement : function(error, element) {
			error.appendTo(element.parent("div")); // 指定显示错误信息的位置
		},
		rules : { // 指定验证规则
			username : { // field,email,password,rePassword等指的都是表单项的name属性
				required : true,
				remote: {
				    url: "user!unique.action",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        username: function() {
				            return $("#username").val();
				        }
				    }
				},
				rangelength:[2,10]
			},
			name:{
				required : true,
				rangelength:[2,10]
			},
			password : {
				required : true,
				rangelength:[6,20]
			}
		},
		messages : { // 当验证不通过时显示的错误信息，也可以不指定，使用默认的
			username : { // 提示的位置要跟上面rules指定的位置一一对应
				required : "请输入用户名",
			},
			name : {
				required : "请输入姓名",
			},
			password : {
				required : "请输入密码"
			}
		}
	});
	

	
});


// 点击获取新的验证码
function changeValidateCode(obj) {
	// 获取当前的时间作为参数,没实际意义，只为确保页面不会缓存
	var timenow = new Date().getTime();
	$(obj).attr("src", "${pageContext.request.contextPath}/securityCode!getCode.action?d=" + timenow);
}

/**
 * 提交登录表单
 */
function login() {
	$.ajax({
		url : "${pageContext.request.contextPath}/user!login.action",
		type : "post",
		data : $("#form_login").serialize(),
		success : function(res) {
			var data = $.parseJSON(res);
			if (data.success) {
				$("#but_login>div").remove(".alert");
				$("#but_login").after("<div class='alert alert-success'>"+data.message+"</div>");
      			location.href = "${pageContext.request.contextPath}/" + data.object;
			} else {	
				$("#but_login>div").remove(".alert");
				$("#but_login").after("<div class='alert alert-warning'>"+data.message+"</div>");
			}
		}
	});
}


</script>

<body style="background-color: #D2E9FF">



	<div class="center-block" style="width: 500px; margin-top: 150px">
		<ul id="myTab" class="nav nav-pills nav-justified">
			<li class="active"><a href="#home" data-toggle="tab"> 登录</a></li>
			<li><a href="#ios" data-toggle="tab">注册</a></li>
		</ul>
		<div id="myTabContent" class="tab-content">
			<div class="tab-pane fade in active" id="home">
				<form class="form-horizontal"   role="form" id="form_login" >
					<div class="form-group"  style="margin-top:20px">
						<label for="username" class="col-sm-2 control-label" >用户名</label>
						<div class="col-sm-10" >
							<input type="text" class="form-control"   name="username"
								placeholder="请输入用户名">
						</div>
					</div>				
				<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密&emsp;码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"   name="password"
								placeholder="请输入密码">
						</div>
					</div>
				<div class="form-group">
						<label for="password" class="col-sm-2 control-label">验证码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control"   name="securityCode"
								placeholder="请输入验证码">
						<img src="${pageContext.request.contextPath }/securityCode!getCode.action" 
						style="width: 80px; height: 30px" onclick="changeValidateCode(this)" title="点击图片刷新验证码" />
						</div>
					</div>	
				
				</form>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button  class="btn btn-default"   onclick="login(); "  id="but_login">登录</button>
						</div>
					</div>
				
				
			</div>
			<div class="tab-pane fade" id="ios">
				<form class="form-horizontal" role="form"  id="form_register"  action="user!register.action">
					<div class="form-group"  style="margin-top:20px">
						<label for="username" class="col-sm-2 control-label" >用户名</label>
						<div class="col-sm-10" >
							<input type="text" class="form-control" id="username"  name="username"
								placeholder="请输入用户名">
						</div>
					</div>
					<div class="form-group">
						<label for="name" class="col-sm-2 control-label">姓&emsp;名</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="name" name="name"
								placeholder="请输入姓名">
						</div>
					</div>
					
				<div class="form-group">
						<label for="password" class="col-sm-2 control-label">密&emsp;码</label>
						<div class="col-sm-10">
							<input type="text" class="form-control " id="password" name="password"
								placeholder="请输入密码">
						</div>
					</div>
			    <div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button  type="submit" class="btn btn-default" id="but_register"  >注册</button>
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
