<%@ page import="com.shenjinxiang.mvn.rapid.consts.RapidConsts" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
var isAllScr = <%=RapidConsts.isIsAllScr()%>;
function openView(){
	if(isAllScr){
		var MainWindow = window.open('<%=RapidConsts.LOGIN_URI%>', "_blank",
				"left=0"
				+ ", top=0"
				+ ", width="+screen.availWidth
				+ ", height=" + screen.availHeight
				+ ", titlebar=no"
				+ ", menubar=no"
				+ ", toolbar=no"
				+ ", location=no"
				+ ", status=yes"
				+ ", resizable=no"
				);
			if (MainWindow != null) {
				//跳转到主窗口
				MainWindow.opener = MainWindow;
				parent.window.opener = window;
				parent.window.close();
			}
	}else{
		window.open('<%=RapidConsts.LOGIN_URI%>','_top');
	}
}
setTimeout("openView();",1000);
</script>
</head>
<body>
  正在跳转到登录界面 ......
</body>
</html>