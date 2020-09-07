<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page import="com.shenjinxiang.mvn.rapid.consts.RapidConsts" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
 
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/reset.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/index.css") %>

<link rel="icon" type="image/x-icon" href="<%=ctx%>/favicon.ico" />
<link rel="shortcut icon" type="image/x-icon" href="<%=ctx%>/favicon.ico" />
<link rel="bookmark" type="image/x-icon" href="<%=ctx%>/favicon.ico" />
	<title><%=RapidConsts.RAPID_NAME%></title>
	<style type="text/css">


		.title-jzzx {
			display: block;
			width: 140px;
			float: left;
			margin-top: 13px;
			margin-left: 10px;
			font-size: 20px;
			color: #fff;
			text-decoration: none;
		}

	</style>
</head>
<body>
<div id="container">

    <header class="clearfix ">
        <a id="logo" class="title-jzzx" href="#" >
            <%=RapidConsts.RAPID_NAME%>
		</a>
        <div  id="tips" >
            <dl id="ticker" style="width: 300px;"></dl>
		</div>
           
        
		<div id="bar">
			<div id="user">
				<a href="javascript:void(0)" class="tips">
					<span class="icon"></span>
					<span class="title">${currentRyxx.rymc }</span>
					<span class="arrow"></span>
				</a>
				<ul class="list">
					<li><a href="javascript:void(0);">修改密码</a></li>
				</ul>
			</div>
			<div class="line"></div>
			<div id="handle"  style="cursor: pointer;">
                <a href="<%=ctx%>/logout"  class="tips">
					 <span class="icon"></span>
					 <span class="title">切换用户</span>
                 </a>  
			</div>
		</div>
	</header>
	<section id="layout">
		<nav>
			<a href="javascript:void(0)" id="fold"></a>
			<dl id="nav"> 
				<dt>
					<a zyid="00" href="javascript:void(0)">
<%--						<span class="icon icon_gzt"></span>--%>
						<span class="title">工作台</span>
					</a>
				</dt>
				<c:forEach items="${yjzyList }" var="yjzy">
					<dt>
						<a zyid="${yjzy.zyid }" href="${yjzy.zylj }">
<%--							<span class="icon ${yjzy.zytb }"></span>--%>
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
				<div class="tabs_div">
					<button id="left_movebtn" class="rlmove_btn lmovebtn"></button>
					<div class="tabs_header" >
					   <span zyid="00" class="active">工作台</span>  
				   </div>
				   <button id="right_movebtn" class="rlmove_btn rmovebtn"></button>
				</div>
				<div class="tabs_container">
					<iframe zyid="00" src="http://www.shenjinxiang.com"></iframe>
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
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/jquery.jqGrid.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/grid.locale-cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/layer/layer.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.validate.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.metadata.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/messages_cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/js/jquery.carouFredSel.packed.js") %>


</body>
</html>