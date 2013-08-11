<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<link href="${pageContext.request.contextPath}/styles/blog/css/blog.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="shortcut icon" type="image/x-icon" href="${pageContext.request.contextPath}/styles/admin/images/favicon.ico" />
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
      <div class="mainbar">
      	<br/>
        <h2 style="font-weight: bold;">Error 404, not found your page.</h2>
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
