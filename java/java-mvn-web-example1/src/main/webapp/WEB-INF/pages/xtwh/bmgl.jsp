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
	var currentRyxxJsonStr = '${currentRyxxJson}';
	var currentRyxx = JSON.parse(currentRyxxJsonStr);
	var addPer = <%=ShiroMethod.hasPermission("/xtwh/bmgl/addBm")%>;
	var modifyPer = <%=ShiroMethod.hasPermission("/xtwh/bmgl/updBm")%>;
	var deletePer = <%=ShiroMethod.hasPermission("/xtwh/bmgl/delBm")%>;
</script>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/ui.jqgrid.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/jquery-ui.theme.min.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<title>部门管理</title>
</head>
<body>
<!-- 操作按钮区 -->
<div class="search_div">
	<form id="searchForm" action="">
		<span class="search_font">部门名称（编号）：</span>
		<input id="s_bmmcdm" name="s_bmmcdm" class="search_input" onkeypress="if(event.keyCode==13) {refreshGrid();;return false;}"/>
		<button type="button" class="_searchbtn" onclick="refreshGrid();">查询</button>
		<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
		<span class="fl_r">
			<shiro:hasPermission name="/xtwh/bmgl/addBm">
				<button type="button" class="_masterbtn" onclick="openAddBmxx();">新增</button>
			</shiro:hasPermission>
			<button type="button" class="_btn" onclick="refreshGrid();">刷新</button>
		</span>
	</form>
</div>
<!-- 表格区 -->
<table id="tableGrid"></table>
<div id="tablePager"></div>
<!-- 部门信息 -->
<div id="bmxxContent" style="display: none;background-color: #F5F6FA;">
	<div class="megbox">
		<form id="bmxxForm" name="bmxxForm" class="megbox_form">
			<dl class="megbox_dl">
				<dd>
					<label>部门编号：</label>
					<span class="megbox_span">
						<input id="bmid" name="bmid"  type="hidden">
						<input id="bmbh" name="bmbh" class="megbox_input inp_readonly" readonly type="text">
					</span>
				</dd>
				<dd>
					<label>部门名称：</label>
					<span class="megbox_span">
						<input id="bmmc" name="bmmc" class="{validate:{required:true,messages:{required:'部门名称为必填项！'}}} megbox_input" type="text">
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label>部门描述：</label>
					<span class="megbox_span">
						<textarea id="bmms" name="bmms"  class="megbox_input"></textarea>
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label>上级部门：</label>
					<span class="megbox_span">
						<input id="sjbmid" name="sjbmid" type="hidden"/>
						<input id="sjbmmc" type="text" readonly class="megbox_input inp_readonly lange" />
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label>录入人：</label>
					<span class="megbox_span">
						<input id="lrrmc" class="megbox_input inp_readonly" readonly type="text">
					</span>
				</dd>
				<dd>
					<label>录入时间：</label>
					<span class="megbox_span">
						<input id="lrrq" class="megbox_input inp_readonly" readonly type="text">
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd style="width:566px;">
					<p class="fl_r">
						<button type="button" class="_masterbtn" onclick="saveBMXX();">保存</button>
					</p>
				</dd>
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