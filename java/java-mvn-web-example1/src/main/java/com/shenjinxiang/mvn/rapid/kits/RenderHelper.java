package com.shenjinxiang.mvn.rapid.kits;


import com.shenjinxiang.mvn.rapid.consts.RapidConsts;

import javax.servlet.http.HttpServletRequest;
import java.io.File;

/**
 * 页面引入js、css文件工具类
 * @author Liuy(liugong修改，Liuy原创)
 *
 */
public class RenderHelper {
	/**
	 * 在原路径的后面增加时间戳（防止ie缓存）
	 */
	public static String addTimestamp(HttpServletRequest request, String path) {
		String contextPath = request.getContextPath();
//		File file = new File(request.getSession().getServletContext().getRealPath(path));
                File file = new File(request.getSession().getServletContext().getRealPath("/")+path);//修改使用glassfish时，页面没有js时报错
		if (file.exists()&& RapidConsts.IS_DEV_MODE)
			return contextPath + path + "?t=" + file.lastModified();
		else if(file.exists())
			return contextPath + path;
		else 
			return "";
	}

	/**
	 * 引入一个带时间戳的js文件（防止ie缓存）
	 * @param request
	 * @param js文件名(含路径) /path/name.js
	 * @return <script type='text/javascript' src='/context/path/name.js?123456.js'></script>
	 */
	public static String includedJavascript(HttpServletRequest request, String path) {
		StringBuffer sb = new StringBuffer();
		sb.append("<script type='text/javascript'");
		sb.append(" src='").append(addTimestamp(request, path)).append("'>");
		sb.append("</script>");
		return sb.toString();
	}

	/**
	 * 引入一个与jsp同名的带时间戳的js文件（防止ie缓存）
	 * @param request
	 * @param js文件名(相对路径) path/name.js
	 */
	public static String includedRelativeJavascript(HttpServletRequest request, String relativePath) {
		String fullPath = request.getRequestURI();
		String jsPath = fullPath.substring(0, fullPath.lastIndexOf("/") + 1) + relativePath;
		jsPath = jsPath.substring(request.getContextPath().length());
		return includedJavascript(request, jsPath);
	}

	/**
	 * 引入一个带时间戳的css文件
	 * @param request
	 * @param path
	 * @return <link type='text/css' rel='stylesheet' href='/context/path/name.css?123456.css'/>
	 */
	public static String includedStyle(HttpServletRequest request, String path) {
		StringBuffer sb = new StringBuffer();
		sb.append("<link type='text/css' rel='stylesheet'");
		sb.append(" href='").append(addTimestamp(request, path)).append("'/>");
		return sb.toString();
	}

	/**
	 * 引入一个与jsp同名的带时间戳的js文件（防止ie缓存）
	 * @param request
	 * @return
	 */
	public static String includedAutoJavascript(HttpServletRequest request) {
		if (!request.getRequestURI().endsWith(".jsp"))
			return "";
		String jsFullPath = request.getRequestURI().replaceAll(".jsp", ".js").replace("/WEB-INF/pages", "/static/js");
		String jsPath = jsFullPath.substring(request.getContextPath().length());
		return includedJavascript(request, jsPath);
	}
}