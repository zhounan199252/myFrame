<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%> 
<!DOCTYPE html>
<html>
<head>
<title>登录界面</title>
<meta http-equiv="content-type" content="text/html; charset=UTF-8">
</head>
<jsp:include page="/js/base.jsp"></jsp:include>
<style>
html, body {
	height: 100%;
	background-color: #D2E9FF;
}
</style>
<script type="text/javascript">
	$(function() {
		//$("ul>li").addClass("glyphicon glyphicon-stop");

		  $("body>div").css("border-style", "solid");
		  $("body>div").css("border-width", "0.1px");
		  $("body>div").css("border-color", "#ddd");
		  
		$("div").css("font-size", "25px");
		$("ul>li").css("font-size", "20px");
		$("ul>li>div").css("font-size", "20px");
		$("td").css("font-size", "18px");
		
		
		$('div[data-toggle="collapse"]').on('shown.bs.collapse', function () {
		$(this).addClass("glyphicon glyphicon-plusp");
		})


	})
</script>
<body>
	<div
		style="width: 100%; height: 10%; text-align: center; background-color: rgb(94, 135, 176)">
		<h2>城市管廊智能化综合管理系统</h2>
	</div>

	<div style="width: 25%; height: 90%; float: left; overflow:auto;background-color:white"   
		data-toggle="collapse">

		<div data-toggle="collapse" data-target="#tuceng"  ><span class="glyphicon glyphicon-book">&nbsp;图层管理</span></div>
		<div id="tuceng" class="collapse">
			<ul class="list-group">
				<li class="list-group-item ">主体</li>
				<li class="list-group-item">电器</li>
				<li class="list-group-item">风管</li>
				<li class="list-group-item">消防设备</li>
				<li class="list-group-item">家具</li>
			</ul>

		</div>

		<div data-toggle="collapse" data-target="#huozai"  ><span class="glyphicon glyphicon-bell">&nbsp;火灾报警系统</span></div>
		<div id="huozai" class="collapse">
			<ul class="list-group">
				<li class="list-group-item">
				<div data-toggle="collapse" data-target="#huozai1">火灾报警</div>
					<table id="huozai1" class="collapse">
						<tr>
							<td style="width: 130px"><span >1号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >2号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >3号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >4号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
					</table>
				</li>
				<li class="list-group-item">
				<div data-toggle="collapse" data-target="#huozai2">火灾报警2</div>
					<table id="huozai2" class="collapse">
						<tr>
							<td style="width: 130px"><span >1号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >2号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >3号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >4号烟感</span></td>
							<td style="width: 150px" id=""><span ></span>正常</td>
						</tr>
					</table>
				</li>
				
			</ul>

		</div>

		<div data-toggle="collapse" data-target="#hjjcxt"  ><span class="glyphicon glyphicon-signal">&nbsp;环境监测系统</span></div>
		<div id="hjjcxt" class="collapse">
			<ul class="list-group">
				<li class="list-group-item">
				<div data-toggle="collapse" data-target="#yyht">一氧化碳</div>
					<table id="yyht" class="collapse">
						<tr>
							<td style="width: 130px"><span >一氧化碳1</span></td>
							<td style="width: 150px" id=""><span ></span>1.3 pci/L</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >一氧化碳2</span></td>
							<td style="width: 150px" id=""><span ></span>0 pci/L</td>
						</tr>
					</table>
				</li>
				<li class="list-group-item">
				<div data-toggle="collapse" data-target="#huozai4">氧气</div>
					<table id="huozai4" class="collapse">
						<tr>
							<td style="width: 130px"><span >氧气1</span></td>
							<td style="width: 150px" id=""><span ></span>21.24 %vol</td>
						</tr>
						<tr>
							<td style="width: 130px"><span  >氧气2</span></td>
							<td style="width: 150px" id=""><span ></span>20.21 %vol</td>
						</tr>
					</table>
				</li>
				
			</ul>

		</div>



	</div>

	<div style="width: 75%; height: 90%; float: right;">
	
	 <%    
       request.setAttribute("m",null); 
	    pageContext.setAttribute("mm","aaa");
        request.setAttribute("mm","bbb");  
        session.setAttribute("mm","ccc");
        application.setAttribute("mm", "ddd");
   %> 
   
  
   <%=pageContext.getAttribute("mm") %>
    ${mm}
    ${pageScope.mm}
    ${request.mm}
    ${session.mm}
    ${application.mm}
    <c:out value="${m}" default="nnnnnn"/><br>    
    <c:out value="${mm}" default="nnnnnnlllllll"/><br>    
   <c:set value="hello word." var="cset" scope="page"/>    
      ${cset}<br>    
    <c:if test="true" var="t">    
       is true    
   </c:if>    
	
	
	</div>
</body>
</html>
