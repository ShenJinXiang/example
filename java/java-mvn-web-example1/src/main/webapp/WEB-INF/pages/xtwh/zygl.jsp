<%@ page import="com.shenjinxiang.mvn.rapid.kits.RenderHelper" %>
<%@ page import="com.jfinal.ext.plugin.shiro.ShiroMethod" %>
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
	var currentRyxxJsonStr = '${currentRyxxJson}';
	var currentRyxx = JSON.parse(currentRyxxJsonStr);
	var addPer = <%=ShiroMethod.hasPermission("/xtwh/zygl/addZy")%>;
	var updPer = <%=ShiroMethod.hasPermission("/xtwh/zygl/updZy")%>;
	var delPer = <%=ShiroMethod.hasPermission("/xtwh/zygl/delZy")%>;
</script>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/ui.jqgrid.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/jquery-ui.theme.min.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<title>资源管理</title>
</head>
<body>
<!-- 操作按钮区 -->
<div class="search_div">
<form id="searchForm" action="">
<span class="search_font">资源名称（路径）：</span>
<input id="s_zymclj" name="s_zymclj" class="search_input" onkeypress="if(event.keyCode==13) {refreshZYXX();;return false;}"/>
<button type="button" class="_searchbtn" onclick="refreshZYXX();">查询</button>
<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
<span class="fl_r">
    <shiro:hasPermission name="/xtwh/zygl/addZy">
		<button type="button" class="_masterbtn" onclick="openAddZyxx();">新增</button>
	</shiro:hasPermission>
<button type="button" class="_btn" onclick="refreshZYXX();">刷新</button>
</span>
</form>
</div>
<!-- 表格区 -->
<table id="zyxxGrid"></table> 
<div id="zyxxPager"></div>	
<!-- 机构信息 -->
<div id="zyxxContent" style="display: none;background-color: #F5F6FA;">
<div class="megbox">
<form id="zyxxForm" name="zyxxForm" class="megbox_form">
<dl class="megbox_dl">
<dd><label>资源名称：</label><span class="megbox_span">
<input id="zyid" name="zyid"  type="hidden">
<input id="zymc" name="zymc" class="{validate:{required:true,messages:{required:'资源名称为必填项！'}}} megbox_input" type="text">
</span></dd>
<dd><label>上级资源：</label><span class="megbox_span">
<input id="sjzymc" readonly="readonly"  class="megbox_input inp_readonly" type="text">
<input id="sjzyid" name="sjzyid" type="hidden">
</span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>资源路径：</label><span class="megbox_span">
<input id="zylj" name="zylj" class="{validate:{required:true,messages:{required:'资源路径为必填项！'}}} megbox_input" type="text">
</span></dd>
<dd><label>资源图标：</label><span class="megbox_span">
<input id="zytb" name="zytb" class="megbox_input" type="text">
</span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>资源描述：</label><span class="megbox_span">
<input id="zyms" name="zyms" class="megbox_input lange" type="text" >
</span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>资源类型：</label><span class="megbox_span">
<select id="zylx" name="zylx" class=" megbox_select">
<option value="0">菜单</option>
<option value="1">功能</option>
</select></span></dd>
<dd><label>资源排序：</label><span class="megbox_span">
<input id="zypx" name="zypx" class="{validate:{digits:true}} megbox_input" type="text">
</span></dd>
</dl>
<dl class="megbox_dl">
<dd style="width:564px;"><p class="fl_r">
<button type="button" class="_masterbtn" onclick="saveZYXX();">保存</button>
</p></dd>
</dl>
</form>
</div>
</div>
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jquery.form.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/jquery.jqGrid.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/jqgrid/grid.locale-cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/layer/layer.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.validate.min.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/jquery.metadata.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/plugin/validate/messages_cn.js") %> 
<%=RenderHelper.includedJavascript(request, "/static/js/common.js") %>
<%=RenderHelper.includedAutoJavascript(request) %>
</body>
</html>