<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page import="com.shenjinxiang.mvn.rapid.consts.RapidConsts" %>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String ctx = request.getContextPath();
%>
<script type="text/javascript">
	var ctx = "<%=ctx%>";
</script>
<%=RenderHelper.includedStyle(request, "/static/css/reset.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/index.css") %>
<link rel="icon" type="image/x-icon" href="favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="favicon.ico" />
<link rel="bookmark" type="image/x-icon" href="favicon.ico" />
<title><%=RapidConsts.RAPID_NAME %></title>
</head>
<body>
<div id="container">
	<header class="clearfix">
		<a id="logo" href="index.html"><img src="<%=ctx %>/static/images/logo.png"/></a>
		<p id="tips"><a href="#">10月1日进行升级更新</a></p>
		<div id="bar">
			
			<div id="user">
				<a href="javascript:void(0)" class="tips">
					<span class="icon"></span>
					<span class="title">${currentRyxx.rymc }</span>
					<span class="arrow"></span>
				</a>
				<ul class="list">
					<li><a href="#">修改密码</a></li>
					<li><a href="#">用户切换</a></li>
				</ul>
			</div>
			<div class="line"></div>
			<div id="handle">
				<a href="javascript:void(0)" class="tips">
					<span class="icon"></span>
					<span class="title">退出</span>
					<span class="arrow"></span>
				</a>
				<ul class="list">
					<li><a href="<%=ctx%>/logout">重新登陆</a></li>
					<li><a href="#">用户切换</a></li>
				</ul>
			</div>
		</div>
	</header>
	<section id="layout">
		<nav>
			<a href="javascript:void(0)" id="fold"></a>
			<dl id="nav">
				<dt>
					<a zyid="00" href="http://www.baidu.com">
						<span class="icon icon_gzt"></span>
						<span class="title">工作看板</span>
					</a>
				</dt>
				<c:forEach items="${yjzyList }" var="yjzy">
					<dt>
						<a zyid="${yjzy.zyid }" href="${yjzy.zylj }">
							<span class="icon ${yjzy.zytb }"></span>
							<span class="title">${yjzy.zymc }</span>
							<c:if test="${yjzy.ejzySize>0 }"><span class="arrow"></span></c:if>
						</a>
					</dt>
					<c:if test="${yjzy.ejzySize>0 }">
					<dd>
					<ul>
						<li>
						<c:forEach items="${yjzy.ejzyList }" var="ejzy">
							<a zyid="${ejzy.zyid }" href="${ejzy.zylj }">
								<span class="point"></span>
								<span class="title">${ejzy.zymc }</span>
							</a>
						</c:forEach>
						</li>
					</ul>
					</dd>
					</c:if>
				</c:forEach>
			</dl>
		</nav>
		<section id="main">
			<div id="tabs">
				<div class="tabs_header">
					<span zyid="00" class="active">工作看板</span>
				</div>
				<div class="tabs_container">
					<iframe zyid="00" src="<%=ctx%>/gzkb"></iframe>
				</div>
			</div>
		</section>
	</section>
</div>
<div class="contextMenu" id="myMenu1">
	<ul>
		<li id="closeCurrent">&nbsp;&nbsp;&nbsp;关闭当前页</li>
	  	<li id="closeOther">&nbsp;&nbsp;&nbsp;关闭其他页</li>
	  	<li id="closeAll">&nbsp;&nbsp;&nbsp;关闭所有页</li>
	  	<li id="refreshCurrent">&nbsp;&nbsp;&nbsp;刷新</li>
	</ul>
</div>
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.contextmenu.r2.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/js/common.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/js/index.js") %> 
</body>
</html>