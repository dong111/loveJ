<#compress>
<#import "blog/lib/lovej-blog.ftl" as lj>
<#include "blog/lib/param.ftl"/>
<#macro page title>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="robots" content="index, follow" />
<meta name="Keywords" content="开源blog,开源博客,java开源blog,java开源博客,国内java开源博客,国内java开源blog,lovej"/>
<meta name="Description" content="loveJ Blog是开源的java博客，欢迎大家使用、交流。"/>

<#-- 禁用页面缓存 -->
<#if title==index0 >
<META   HTTP-EQUIV="Pragma"   CONTENT="no-cache">    
<META   HTTP-EQUIV="Cache-Control"   CONTENT="no-cache">    
<META   HTTP-EQUIV="Expires"   CONTENT="0">    
</#if>

<title>${siteConfig.name}-${title}</title>
<!-- CSS -->
<link href="${rc.contextPath}/styles/blog/css/blog.css" rel="stylesheet" type="text/css" media="screen" />
<link rel="shortcut icon" href="${rc.contextPath}/styles/admin/images/favicon.ico" />
<script src="${rc.contextPath}/styles/admin/js/jquery1.7-min.js" type="text/javascript"></script>
<script>
$(document).ready(function(){
	$("#request_locale").change(function(){
		var url = window.location.href;
		if (url.indexOf('?') > -1) {
			if (url.indexOf('?request_locale') > -1) {
				url = url.substring(0, url.indexOf('?request_locale'));
				url += '?request_locale=' + $(this).val();
			} else if (url.indexOf('&request_locale') > -1) {
				url = url.substring(0, url.indexOf('&request_locale'));
				url += '&request_locale=' + $(this).val();
			} else {
				url += '&request_locale=' + $(this).val();
			}
		} else {
			url += '?request_locale=' + $(this).val()
		}
		window.location.href=url;
	});
});
</script>
</head>

<body>
<div class="main">
  <div class="header">
    <div class="header_resize">
      <div class="logo"><a href="${siteConfig.url}"><img src="${rc.contextPath}/styles/admin/images/logo.png"/></a>
  		<div id="language">
      		<@s.message code="form.change.language"/>
			<select name="request_locale" id="request_locale">
			  <option value ="zh_CN">默认</option>
			  <option value ="zh_CN">简体中文</option>
			  <option value ="en_US">English</option>
			</select>
		</div>
      </div>

      <div class="menu_nav">
        <ul>
        	<@lj.menu_nav title=title index=index/>
        </ul>
      </div>
      <div class="clr"></div>
    </div>
  </div>

  <div class="hbg">&nbsp;</div>

  <div class="content">
    <div class="content_resize">
      <div class="mainbar">
      <#-- 插入内容 -->
        <#nested/>
      </div>
      <#-- 侧边栏 -->
        <div class="sidebar">  
  	 
	    <div class="gadget">
	    <h2 class="star">
		<form action="<@s.url relativeUrl="/blog/search"/>" method="post" id="formID">
		<table border="0" cellpadding="0" cellspacing="0" class="tab_search">
			<tr>
				<td>
					<input type="text" name="q" class="searchinput" id="searchinput" size="20"/>
				</td>
				<td>
					<input type="image" width="21px" height="17px" class="searchaction"  src="${rc.contextPath}/styles/blog/images/search_btn_side.gif" border="0" hspace="2"/>
				</td>
			</tr>
		</table>
		</form>
		</h2>
	    </div>  
	    
	    <div class="gadget">
	      <h2 class="star"><span><@s.message code="blog.newest.article"/></span></h2><div class="clr"></div>
	      <ul class="sb_menu">
	      	<#list newestArticles as c>
				<li><#if c.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if>
					<a href="<@s.url relativeUrl="/blog/view/${c.id}"/>">
						<#if c.title?length gt 40>${c.title?substring(0,40)}...<#else>${c.title}</#if>
					</a>
				</li>
			</#list>
	      </ul>
	    </div>
	    
	    <div class="gadget">
	      <h2 class="star"><span><@s.message code="blog.newest.comment"/></span></h2><div class="clr"></div>
	      <ul class="sb_menu">
	      	<#list newestComments as c>
				<li>
					<a href="<@s.url relativeUrl="/blog/view/${c.articleId}#cmts"/>">
						<#if c.content?length gt 40>${c.content?substring(0,40)}...<#else>${c.content}</#if>
					</a>
				</li>
			</#list>
	      </ul>
	    </div>
 		
	    <div class="gadget">
	      <h2 class="star"><span><@s.message code="blog.category"/></span></h2><div class="clr"></div>
	      <ul class="sb_menu">
	      	<#list categories as c>
					<#-- 不显示父类 --><#-- <li><a href="<@s.url action="extendCategory" namespace="/blog" ><@s.param name="category.id" value="#c.id"/></@s.url>">${c.name}</a></li>-->
				<#list c.children as z>
					<li><a href="<@s.url relativeUrl="/blog/extendCategory/${z.id}/1"/>">${z.name}</a></li>
				</#list>
			</#list>
	      </ul>
	    </div>
	    
	    <div class="gadget">
	      <h2 class="star"><span><@s.message code="blog.links"/></span></h2><div class="clr"></div>
	      <ul class="sb_menu">
	      	<#list newestLinks as c>
				<li>
					<a href="${c.site}">
						<#if c.name?length gt 20>${c.name?substring(0,20)}<#else>${c.name}</#if>
					</a>
				</li>
			</#list>
	      </ul>
	    </div>   
		
		 <#if title==index0 >
		    <div class="gadget">
				<object type="application/x-shockwave-flash" data="${rc.contextPath}/styles/dewplayer/dewplayer-playlist.swf" width="240" height="200" id="dewplayer" name="dewplayer">
				<param name="wmode" value="transparent" />
				<param name="movie" value="dewplayer-playlist.swf" />
				<param name="flashvars" value="showtime=true&autoreplay=true&autostart=false&xml=${rc.contextPath}/styles/dewplayer/playlist.xml" />
				</object>
		    </div>    
		 </#if>
  		</div> 
      <#-- end 侧边栏 -->
      <div class="clr"></div>
    </div>
  </div>

  <div class="footer">
    <div class="footer_resize">
      <p class="lf">© Copyright 2011-2012 &nbsp;${siteConfig.icp}&nbsp;<a href="${siteConfig.url}">${siteConfig.name}</a></p>
      <ul class="fmenu">
			<@lj.menu_nav title=title index=index/>
      </ul>
      <div class="clr"></div>
    </div>
  </div>
</div>
</body>
</html>
</#macro>
</#compress>