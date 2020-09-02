<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%=RenderHelper.includedStyle(request, "/static/css/login.css") %>
<title>login</title>
<%
	String ctx = request.getContextPath(); 
%>
</head>
<body>
	<div id="loginLayout">
    	<form id="loginForm" action="<%=ctx%>/validateLogin" method="post">
        	<ul>
            	<li><input type="text"  placeholder ="请输入用户名" id="rydm" name="rydm" value="${rydm }"/></li>
            	<li><input type="password"  placeholder ="密码" id="mm" name="mm" value="${mm }"/>
<!--             	<input type="text"  placeholder="请输入验证码" id="yzm"/> -->
            	</li>
                <li><p>${error }</p></li> 
                <li id="btns">
                	<input type="submit" value="" id="login"/>
                    <input type="button" value="" id="cancel" onclick="javascirpt:window.close();"/>
                </li>
            </ul>
        </form>
    </div>
</body>
</html>