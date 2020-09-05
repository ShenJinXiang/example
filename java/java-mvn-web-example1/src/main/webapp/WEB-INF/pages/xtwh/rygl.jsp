<%@page import="com.jfinal.ext.plugin.shiro.ShiroMethod"%>
<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%
	String ctx = request.getContextPath();
%>
<script type="text/javascript">
	var ctx = "<%=ctx%>";
	<%--var ssjgid = "${currentRyxx.ssjgid}";--%>
	<%--var ssjgmc = "${currentRyxx.ssjgmc}";--%>
	<%--var grantPer = <%=ShiroMethod.hasPermission("/yygl/rygl/saveRyJs")%>;--%>
	<%--var modifyPer = <%=ShiroMethod.hasPermission("/yygl/rygl/updateRYXX")%>;--%>
	<%--var deletePer = <%=ShiroMethod.hasPermission("/yygl/rygl/delete")%>;--%>
	<%--var resetPwdPer = <%=ShiroMethod.hasPermission("/yygl/rygl/resetPwd")%>;--%>
</script>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/ui.jqgrid.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/jquery-ui.theme.min.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/ztree/zTreeStyle.css") %> 
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<title>人员管理</title>
</head>
<body>
<!-- 操作按钮区 -->
<div class="search_div">
	<form id="searchForm" action="">
		<span class="search_font" style="margin-left: 250px;">人员名称：</span>
		<input id="s_jgmcdm" name="rymc" class="search_input" onkeypress="if(event.keyCode==13) {refreshRYXX();;return false;}"/>
		<button type="button" class="_searchbtn" onclick="refreshRYXX();">查询</button>
		<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
		<span class="fl_r">
			<shiro:hasPermission name="/yygl/rygl/saveRYXX">
			<button type="button" class="_masterbtn" onclick="openAddRyxx();">新增</button>
			</shiro:hasPermission>
			<button type="button" class="_btn" onclick="refreshRYXX();">刷新</button>
		</span>
	</form>
</div>
<!-- 表格区 -->
<div class="left">
	<ul id="orgTree" class="ztree"></ul>
</div>
<div class="right">
	<table id="ryxxGrid"></table>
	<div id="ryxxPager"></div>
</div>
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.js") %>
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.form.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/jquery.jqGrid.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/grid.locale-cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/layer/layer.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.validate.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.metadata.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/messages_cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/ztree/jquery.ztree.core-3.5.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/ztree/jquery.ztree.excheck-3.5.min.js") %>
<%=RenderHelper.includedJavascript(request, "/static/js/common.js") %>
<%=RenderHelper.includedAutoJavascript(request) %>
</body>
</html>