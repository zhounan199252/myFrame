<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="bootstrap-3.3.7/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="bootstrap-3.3.7/bootstrap-treeview.min.css">
<script src="js/jquery-3.1.1.min.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/messages_zh.min.js"></script>
<script src="js/jquery.form.min.js"></script>
<script src="bootstrap-3.3.7/bootstrap-3.3.7-dist/js/bootstrap.min.js"></script>
<script src="bootstrap-3.3.7/bootstrap-treeview.min.js"></script>


<script type="text/javascript">

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




