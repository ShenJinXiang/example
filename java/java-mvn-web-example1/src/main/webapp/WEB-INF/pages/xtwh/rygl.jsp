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

	var addPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/addRyxx")%>;
	var modifyPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/updRyxx")%>;
	var deletePer = <%=ShiroMethod.hasPermission("/xtwh/rygl/delRyxx")%>;
	var grantPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/sqRyxx")%>;
	var resetPwdPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/resetPwd")%>;
	var jyryPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/jyRy")%>;
	var qyryPer = <%=ShiroMethod.hasPermission("/xtwh/rygl/qyRy")%>;
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
		<input id="s_rymc" name="s_rymc" class="search_input" onkeypress="if(event.keyCode==13) {refreshGrid();;return false;}"/>
		<button type="button" class="_searchbtn" onclick="refreshGrid();">查询</button>
		<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
		<span class="fl_r">
			<shiro:hasPermission name="/xtwh/rygl/addRyxx">
			<button type="button" class="_masterbtn" onclick="openAddRyxx();">新增</button>
			</shiro:hasPermission>
			<button type="button" class="_btn" onclick="refreshGrid();">刷新</button>
		</span>
	</form>
</div>
<!-- 表格区 -->
<div class="left">
	<ul id="bmTree" class="ztree"></ul>
</div>
<div class="right">
	<table id="tableGrid"></table>
	<div id="tablePager"></div>
</div>


<div id="ryxxContent" style="display: none;background-color: #F5F6FA;">
	<div class="megbox">
		<form id="ryxxForm" name="ryxxForm" class="megbox_form">
			<dl class="megbox_dl">
				<dd>
					<label>用户编号：</label>
					<span class="megbox_span">
						<input id="ryid" name="ryid"  type="hidden">
						<input id="rybh" name="rybh" class="megbox_input inp_readonly" readonly type="text">
					</span>
				</dd>
				<dd>
					<label for="ryzh">用户账号：</label>
					<span class="megbox_span">
						<input id="ryzh" name="ryzh" class="{validate:{required:true,messages:{required:'用户账号为必填项！'}}} megbox_input" type="text">
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label for="rymc">用户姓名：</label>
					<span class="megbox_span">
						<input id="rymc" name="rymc" class="{validate:{required:true,messages:{required:'用户姓名为必填项！'}}} megbox_input" type="text">
					</span>
				</dd>
				<dd>
					<label for="lxdh">联系电话：</label>
					<span class="megbox_span">
						<input id="lxdh" name="lxdh" class="megbox_input" type="text">
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label>所属部门：</label>
					<span class="megbox_span">
						<input id="ssbmid" name="ssbmid" type="hidden"/>
						<input id="ssbmmc" type="text" readonly class="megbox_input inp_readonly lange" />
					</span>
				</dd>
			</dl>
			<dl class="megbox_dl">
				<dd>
					<label>备注</label>
					<span class="megbox_span">
						<textarea id="bz" name="bz"  class="megbox_input"></textarea>
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
						<button type="button" class="_masterbtn" onclick="saveRyxx();">保存</button>
					</p>
				</dd>
			</dl>
		</form>
	</div>
</div>

<div id="grantContent" style="display: none">
	<div id="jsTree" class="ztree" style="height:300px; width:290px;overflow: auto;"></div>
	<p class="fl_r">
		<input type="hidden" id="js_ryid">
		<button type="button" class="_masterbtn" onclick="saveGrant();" style="margin-right: 20px;margin-bottom: 20px;">保存</button>
	</p>
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