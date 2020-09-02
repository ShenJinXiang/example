<%@page import="com.qysoft.rapid.utils.RenderHelper"%>
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
</script>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/ui.jqgrid.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/jquery-ui.theme.min.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/ztree/zTreeStyle.css") %> 
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<title>角色管理</title>
</head>
<body>
<!-- 操作按钮区 -->
<div class="search_div">
<!-- <span class="search_font">会计制度：</span> -->
<!-- <select class="search_select"> -->
<!-- <option>企业会计制度1</option> -->
<!-- <option>企业会计制度2</option> -->
<!-- <option>企业会计制度3</option> -->
<!-- </select> -->
<form id="searchForm" action="">
<span class="search_font">角色名称：</span>
<input id="s_jsmc" name="s_jsmc" class="search_input" onkeypress="if(event.keyCode==13) {refreshJSXX();;return false;}"/>
<button type="button" class="_searchbtn" onclick="refreshJSXX();">查询</button>
<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
<span class="fl_r">
<button type="button" class="_masterbtn" onclick="openAddJsxx();">新增</button>
<button type="button" class="_btn" onclick="refreshJSXX();">刷新</button>
</span>
</form>
</div>
<!-- 表格区 -->
<table id="jsxxGrid"></table> 
<div id="jsxxPager"></div>	
<!-- 机构信息 -->
<div id="jsxxContent" style="display: none;background-color: #F5F6FA;">
<div class="megbox">
<form id="jsxxForm" name="jsxxForm" class="megbox_form">
<dl class="megbox_dl">
<dd><label>角色名称：</label><span class="megbox_span">
<input id="jsid" name="jsid"  type="hidden">
<input id="jsmc" name="jsmc" class="{validate:{required:true,messages:{required:'角色名称为必填项！'}}} megbox_input" type="text">
</span></dd>
<dd><label>角色描述：</label><span class="megbox_span">
<input id="jsms" name="jsms" class="{validate:{required:true,messages:{required:'角色描述为必填项！'}}} megbox_input" type="text">
</span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>角色类型：</label><span class="megbox_span">
<select id="jslx" name="jslx" class=" megbox_select">
<option value="0">平台</option>
<option value="1">生产</option>
<option value="2">销售</option>
</select></span></dd>
<dd><label>数据权限：</label>
<span class="megbox_span">
<select id="sjqx" name="sjqx" class=" megbox_select">
<option value="1">公司数据</option>
<option value="2">部门数据</option>
<option value="3">个人数据</option>
</select>
</span></dd>
</dl>
<dl class="megbox_dl">
<dd style="width:564px;"><p class="fl_r">
<button type="button" class="_masterbtn" onclick="saveJSXX();">保存</button>
</p></dd>
</dl>
</form>
</div>
</div>

<div id="zyDiv" style="display: none">
<div id="zyTree" class="ztree" style="height:300px; width:290px;overflow: auto;"></div>
<p class="fl_r">
<button type="button" class="_masterbtn" onclick="saveGrant();" style="margin-right: 20px;">保存</button>
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