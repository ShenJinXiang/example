<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page import="com.shenjinxiang.mvn.rapid.consts.RapidConsts" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<%=RenderHelper.includedStyle(request, "/static/plugin/bootstrap/3.3.7/bootstrap.min.css") %>
	<%=RenderHelper.includedStyle(request, "/static/css/login.css") %>
	<title><%=RapidConsts.RAPID_NAME%></title>
	<%
		String ctx = request.getContextPath();
	%>
</head>
<body>
<div class="container">
	<form class="form-horizontal" action="<%=ctx%>/validateLogin" method="post">
		<div class="form-group">
			<label for="ryzh" class="col-sm-4 control-label">用户名：</label>
			<div class="col-sm-6">
				<input type="text" class="form-control" id="ryzh" name="ryzh" value="${ryzh}" placeholder="用户名">
			</div>
		</div>
		<div class="form-group">
			<label for="mm" class="col-sm-4 control-label">密码：</label>
			<div class="col-sm-6">
				<input type="password" class="form-control" id="mm" name="mm" value="${mm}" placeholder="密码">
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-6">
				<p class="text-danger">${error}<p>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-4 col-sm-6">
				<button type="submit" class="btn btn-default">登录</button>
			</div>
		</div>
	</form>
</div>
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.js") %>
<%=RenderHelper.includedJavascript(request, "/static/plugin/bootstrap/3.3.7/bootstrap.min.js") %>
</body>
</html>