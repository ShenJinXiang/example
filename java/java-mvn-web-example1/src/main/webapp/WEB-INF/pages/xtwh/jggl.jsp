<%@page import="com.jfinal.ext.plugin.shiro.ShiroMethod"%>
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
	var ssjgid = "${currentRyxx.ssjgid}";
	var ssjgmc = "${currentRyxx.ssjgmc}";
	var addPer = <%=ShiroMethod.hasPermission("/yygl/jggl/saveJGXX")%>;
	var modifyPer = <%=ShiroMethod.hasPermission("/yygl/jggl/updateJGXX")%>;
	var deletePer = <%=ShiroMethod.hasPermission("/yygl/jggl/delete")%>;
</script>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/ui.jqgrid.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/jqgrid/css/jquery-ui.theme.min.css") %>
<%=RenderHelper.includedStyle(request, "/static/plugin/layer/skin/layer.css") %>
<%=RenderHelper.includedStyle(request, "/static/css/common.css") %>
<title>机构管理</title>
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
<span class="search_font">机构名称（代码）：</span>
<input id="s_jgmcdm" name="s_jgmcdm" class="search_input" onkeypress="if(event.keyCode==13) {refreshJGXX();;return false;}"/>
<button type="button" class="_searchbtn" onclick="refreshJGXX();">查询</button>
<button type="button" class="_btn" onclick="formReset('searchForm');">重置</button>
<span class="fl_r">
<shiro:hasPermission name="/yygl/jggl/saveJGXX">
<button type="button" class="_masterbtn" onclick="openAddJgxx();">新增</button>
</shiro:hasPermission>
<button type="button" class="_btn" onclick="refreshJGXX();">刷新</button>
</span>
</form>
</div>
<!-- 表格区 -->
<table id="jgxxGrid"></table> 
<div id="jgxxPager"></div>	
<!-- 机构信息 -->
<div id="jgxxContent" style="display: none;background-color: #F5F6FA;">
<div class="megbox">
<form id="jgxxForm" name="jgxxForm" class="megbox_form">
<dl class="megbox_dl">
<dd><label>机构代码：</label><span class="megbox_span">
<input id="jgid" name="jgid"  type="hidden">
<input id="jgdm" name="jgdm" class="{validate:{required:true,messages:{required:'机构代码为必填项！'}}} megbox_input" type="text">
</span></dd>
<dd><label>机构名称：</label><span class="megbox_span"><input id="jgmc" name="jgmc" class="{validate:{required:true,messages:{required:'机构名称为必填项！'}}} megbox_input" type="text"></span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>机构类型：</label><span class="megbox_span">
<select id="jglx" name="jglx" class=" megbox_select">
<option value="1">生产</option>
<option value="2">销售</option>
<option value="3">生产+销售</option>
</select></span></dd>
<dd><label>机构级别：</label><span class="megbox_span">
<select id="jgjb" name="jgjb" class=" megbox_select">
<option value="1">公司</option>
<option value="2">部门</option>
</select></span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>所属机构：</label><span class="megbox_span">
<input id="sjjgmc" readonly="readonly"  class="megbox_input inp_readonly" type="text">
<input id="sjjgid" name="sjjgid" type="hidden">
</span></dd>
<dd><label>电话：</label><span class="megbox_span"><input id="dh" name="dh" class="megbox_input" type="text"></span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>地址：</label><span class="megbox_span"><input id="dz" name="dz" class="megbox_input" type="text" style="width:491px;"></span></dd>
</dl>
<dl class="megbox_dl">
<dd><label>维护人：</label><span class="megbox_span"><input class="megbox_input inp_readonly" readonly type="text" value="admin"></span></dd>
<dd><label>维护时间：</label><span class="megbox_span"><input class="megbox_input inp_readonly" readonly type="text" value="2015-11-09"></span></dd>
</dl>
<dl class="megbox_dl">
<dd style="width:564px;"><p class="fl_r">
<button type="button" class="_masterbtn" onclick="saveJGXX();">保存</button>
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