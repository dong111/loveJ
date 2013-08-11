<%@page import="org.apache.commons.logging.LogFactory"%>
<%@page import="org.apache.commons.logging.Log"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" import="java.io.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>500</title>
<link href="${pageContext.request.contextPath}/styles/blog/css/blog.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/styles/admin/images/favicon.ico" />
<script src="${pageContext.request.contextPath}/styles/admin/js/jquery1.7-min.js" type="text/javascript"></script>
<style>

</style>
</head>
<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><a href="${siteConfig.url}"><img src="${pageContext.request.contextPath}/styles/admin/images/logo.png"/></a>
      </div>

      <div class="menu_nav">
        <ul>
        	<li><a href="${siteConfig.url}">${siteConfig.name}</a></li>
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>

  <div class="hbg">&nbsp;</div>

  <div class="content">
    <div class="content_resize">
      <div class="mainbar" style="color:#323a3f;">
      	<div style="display:none;">
        <%=exception.getMessage()%><br>
		<%=exception.getLocalizedMessage()%>
		<%
		Log logger = LogFactory.getLog("505.jsp");
		StringWriter sw=new StringWriter();
		PrintWriter pw=new PrintWriter(sw);
		exception.printStackTrace(pw);
		//out.print(sw);
		logger.error(sw.toString(), exception);
		%>
		</div>
      	<br/>
        <h3>正在进行系统升级或者更新，网络服务暂时停止使用，请稍候继续访问，对您造成不便敬请谅解。您可以报告问题<a href="mailto:ketayao@gmail.com">ketyao.com Service</a>给站长。</h3>
		<h3>ooops!!,server error, may by updating.if any questions report to <a href="mailto:ketayao@gmail.com">ketyao.com Service</a>.</h3>
		<br/>
      </div>
        <div class="sidebar">  

  		</div> 
      <div class="clr"></div>
    </div>
  </div>

  <div class="footer">
    <div class="footer_resize">
      <p class="lf">© Copyright 2011-2012 &nbsp;${siteConfig.icp}&nbsp;<a href="${siteConfig.url}">${siteConfig.name}</a></p>
      <ul class="fmenu">
			<li><a href="${siteConfig.url}">${siteConfig.name}</a></li>
      </ul>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>
