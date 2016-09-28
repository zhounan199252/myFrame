<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/jquery-1.11.1.min.js"></script>

<!-- begin:网页小图标 -->
<link rel="shortcut icon" href="${pageContext.request.contextPath}/globle/images/logo.ico" />
<!-- end:网页小图标 -->


<!-- begin: My97DatePicker日历控件-->
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/My97DatePicker/lang/zh-cn.js"></script>
<!-- end: My97DatePicker日历控件-->

<!-- begin:jquery_validate jQuery表单验证控件 -->
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/jquery-validation-1.14.0/dist/jquery.validate.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/jquery-validation-1.14.0/dist/additional-methods.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/jquery-validation-1.14.0/dist/messages_zh.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/globle/js/jquery-validation-1.14.0/cmxform.css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/globle/js/jquery-validation-1.14.0/pic.css" />
<!-- end:jquery_validate jQuery表单验证控件 -->


<!-- back bootstrap begin -->
<link href="${pageContext.request.contextPath }/globle/js/assets/css/dpl-min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/globle/js/assets/css/bui-min.css" rel="stylesheet" type="text/css" />
<link href="${pageContext.request.contextPath }/globle/js/assets/css/main-min.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/assets/js/bui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/globle/js/assets/js/config.js"></script>
<!-- back bootstrap end -->


<script type="text/javascript">
	$(function() {
		//设定jqueryValidate的一些默认值，提高用户体验 begin
		jQuery.validator.setDefaults({
			// debug : true, //调试模式，打开后，就算全部表单项验证通过也不会提交表单 
			success : "valid"// 验证通过的表单项会默认加入一个class="valid"
			/*
			 * success : function(label) {
			 * //也可以指定一个function来处理事件，label是自动添加的显示错误信息的label
			 * label.addClass("valid").text("Ok!") }
			 */
			,
			highlight : function(element, errorClass) { // 高亮显示验证失败的输入框
				$(element).fadeOut(function() {
					$(element).fadeIn();
				});
			}
		});
		//设定jqueryValidate的一些默认值，提高用户体验 end

	});
	

	function doSearch() {
		
		$("#form_search").submit();
		
	}

	function doAdd(url) {
		location.href = url;
	}
	
	function doUpdate(url) {
		
		var checkboxs = $("[name=ids]:checked");
		
		if(checkboxs.length <= 0) {
			BUI.Message.Alert("请选择一条记录进行编辑！");
			return false;
		}
		
		if(checkboxs.length > 1) {
			BUI.Message.Alert("每次只能选择一条记录进行编辑！");
			return false;
		}
	
		var id = $(checkboxs).prop("value");
		var _url = url + "?id=" + id
		location.href = _url;
	}

	function doRemove(url) {
		var checkboxs = $("[name=ids]:checked");
		
		if(checkboxs.length <= 0) {
			BUI.Message.Alert("请至少选择一条记录进行删除！");
			return false;
		}
		
		 
		BUI.Message.Confirm('确定要删除选中的记录吗？', function() {

			var id = "";
			for (var i = 0; i < checkboxs.length; i++) {
				id += $(checkboxs[i]).prop("value") + ",";
			}

			var _url = url + "?id=" + id.substring(0, id.length - 1);
			location.href = _url;
		}, 'question');
	}

	function doClear() {
		$("#form_search :input").not(":button, :submit, :reset, :hidden").val("").removeAttr("checked").remove("selected");
	}

	function goBack() {
		history.go(-1);
	}
</script>

<style>
	th {
		text-align: right
	}
</style>






