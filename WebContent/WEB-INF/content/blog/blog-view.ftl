<#compress>
<#import "blog/lib/common.ftl" as com>
<#--title定义-->
</#compress>
<#escape x as x?html>
<@com.page title='${article.title}'>
<div class="article">
  <h2><span><#if article.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if>${article.title}</h2><div class="clr"></div>
  <p><@s.message code="blog.category"/>:<a href="<@s.url relativeUrl="/blog/extendCategory?categoryId=${article.categoryId}"/>">${article.category.name}</a> &nbsp;|&nbsp; <@s.message code="article.view"/>:${article.view} &nbsp;|&nbsp;  ${article.postTime?string('yyyy-MM-dd HH:mm:ss')} </p>
  <#if article.quote != null && article.quote != ''>
  <table>
  <tr>
	<td width="20" valign="top" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:left;padding:10px 10px;">“</td>
	<td valign="top" style="padding:4px 10px;font-size:14px;font-weight:bold;color:#424242;">${article.quote}</td>
	<td width="20" valign="bottom" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:right;padding:10px 10px;">”</td>
  </tr>
  </table>
  </#if>
  <div class="love-article-content">
  <#noescape>${article.content}</#noescape>
  <p>
  	<#if attaches?size gt 0>
  	<font style="color:red;font-size:15px;font-weight:bold;"><@s.message code="form.download"/></font>
  	</#if>
  	<#list attaches as a>
  	<li><a href="<@s.url relativeUrl="/blog/download/${a.id}"/>">${a.url}</a>&nbsp;&nbsp;(<@s.message code="form.download"/>:${a.download})</li>
	</#list>
  </p>	
  </div>
</div>

<div class="love-prenext">
<#if articles?size gt 0>
	<p><span>
		<#if articles[0] != null>
			<a href="<@s.url relativeUrl="/blog/view/${articles[0].id}" />"><@s.message code="blog.pre"/>:${articles[0].title}</a>
		<#else>
			<@s.message code="blog.pre"/>:<@s.message code="blog.none"/>
		</#if>
	</span></p>
	<p><span>
		<#if articles[1] != null>
			<a href="<@s.url relativeUrl="/blog/view/${articles[1].id}" />"><@s.message code="blog.next"/>:${articles[1].title}</a>
		<#else>
			<@s.message code="blog.next"/>:<@s.message code="blog.none"/>
		</#if>
	</span></p>
</#if>
</div>

<#-- form验证 -->
<link rel="stylesheet" href="${rc.contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-${(rc.locale)!'zh_CN'}.js" type="text/javascript" charset="utf-8"></script>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        // binds form submission and fields to the validation engine
        jQuery("#formID").validationEngine();
    });
    
    jQuery(document).ready(function(){
    	$("#refresh").click(function(){
    		$("#captcha").attr("src", "${rc.contextPath}/Captcha.jpg");
    		return false;
    	});
    });
</script>
<#-- 代码高亮 -->
<link rel="stylesheet" href="${rc.contextPath}/styles/kindeditor-4.1/plugins/code/prettify.css" type="text/css"/>
<script charset="utf-8" src="${rc.contextPath}/styles/kindeditor-4.1/plugins/code/prettify.js"></script>

<#-- DWR -->
<script type='text/javascript' src='${rc.contextPath}/dwr/engine.js'> </script>
<script type='text/javascript' src='${rc.contextPath}/dwr/util.js'> </script>
<script type='text/javascript' src='${rc.contextPath}/dwr/interface/ArticleCommentService.js'> </script>
<script type="text/javascript">
	var comments;

	/**
	 * 时间对象的格式化
	 */
	Date.prototype.format = function(format) {
		/*
		 * format="yyyy-MM-dd HH:mm:ss";
		 */
		var o = {
			"M+" : this.getMonth() + 1,
			"d+" : this.getDate(),
			"h+" : this.getHours(),
			"m+" : this.getMinutes(),
			"s+" : this.getSeconds(),
			"q+" : Math.floor((this.getMonth() + 3) / 3),
			"S" : this.getMilliseconds()
		}
		if (/(y+)/.test(format)) {
			format = format.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(format)) {
				format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
						: ("00" + o[k]).substr(("" + o[k]).length));
			}
		}
		return format;
	}
	
	jQuery(document).ready(function(){
    	$(".pages a").click(function(){
    		var pageIndex = $(this).attr("href");
    		pager(${article.id}, pageIndex);
    		return false;
    	});
    });

	function pager(articleId, pageIndex) {
		ArticleCommentService.findPageComments(articleId, pageIndex, initComments);
	}
		
	function initComments(data) {
		comments = data;
		ArticleCommentService.getCommentPage(showPages);
		//alert(data.length);
	}
	
	function showPages(data) {
		<#-- 显示评论 -->
		var content = "";
		var floor = 1 + (data.pageIndex - 1) * data.pageSize;
		for (var i = 0;i<comments.length;i++) {
			content += '<div class="comment">';
			var site;
			if (comments[i].site != null && comments[i].site != '') {
				site = comments[i].site;
			} else {
				site = '#';
			}
			content += '<a href="' + site + '"><img src="${rc.contextPath}/styles/blog/images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>' 
			content += '<p>' + (floor+i) + '<@s.message code="blog.reply.floor"/>:&nbsp;';
			
			if (site == '#') {
				content += '<a>'+comments[i].name+'</a>';
			} else {
				content += '<a href="' + site + '">' + comments[i].name + '</a>';
			}
			
			var d=comments[i].postTime; 
			content += '&nbsp;Says:<br />at&nbsp;' + d.format("yyyy-MM-dd hh:mm:ss") + '</p>';
			
			content += '<p>'+ comments[i].content +'</p>';
			content += '</div>';
			
		}
		$("#allComments").replaceWith('<span id="allComments">'+content+'</span>');
		
		<#-- 显示页码 -->
		$(".pages").replaceWith('<p class="pages">' +'Page '+data.pageIndex+' of '+data.totalPage+' &nbsp;&nbsp;&nbsp;'+data.pageHtml+'</p>');
		$(".pages a").click(function(){
    		var pageIndex = $(this).attr("href");
    		pager(${article.id}, pageIndex);
    		return false;
    	});
	}
</script>
	
<div class="article">
<hr id="cmts"/>
<#if comments?size gt 0>
<h2><span>${commentPage.totalRec}</span> <@s.message code="blog.reply.sum"/></h2><div class="clr"></div>
</#if>
<span id="allComments">
<#list comments as c>
   <#assign index>${c_index + 1 + (commentPage.pageIndex - 1) * commentPage.pageSize}</#assign>
   <div class="comment">
   	 	<a href="<#if c.site != null && c.site != ''>${c.site}<#else>#</#if>"><img src="${rc.contextPath}/styles/blog/images/userpic.gif" width="40" height="40" alt="user" class="userpic" /></a>
    	<p>${index}<@s.message code="blog.reply.floor"/>:&nbsp;<#if c.site != null && c.site != ''><a href="${c.site}">${c.name}</a><#else><a>${c.name}</a></#if>&nbsp;Says:<br />at&nbsp;${c.postTime?string('yyyy-MM-dd HH:dd:ss')}</p>
    	<p>${c.content}</p>
   </div>
</#list>
</span>
</div>

<#noescape>
<#if comments?size gt 0>
<p class="pages">Page ${commentPage.pageIndex} of ${commentPage.totalPage} &nbsp;&nbsp;&nbsp; 
		${commentPage.pageHtml}
</p>
</#if>
</#noescape>

<div class="article">
  <h2><span><@s.message code="blog.reply.article"/></span><font color="red" style="font-weight:bold;"><#if failure??><@s.message code="form.code.wrong"/></#if></font></h2>
  <form action="<@s.url relativeUrl="/blog/reply" />" class="jNice" method="POST" id="formID">
  <input type="hidden" name="articleId" value="${article.id}"/>
  <ol><li>
    <label for="comment.name"><@s.message code="comment.name"/><font color="red">*</font></label>
    <input id="name" name="name" class="text validate[required,maxSize[50]]" value="${(comment.name)!""}"/>
  </li><li>
    <label for="comment.email"><@s.message code="comment.email"/><font color="red">*</font></label>
    <input id="email" name="email" class="text validate[required,maxSize[255],custom[email]]" value="${(comment.email)!""}"/>
  </li><li>
    <label for="comment.site"><@s.message code="comment.site"/>(http://)</label>
    <input id="website" name="site" class="text validate[optional,maxSize[255],custom[url]]" value="${(comment.site)!""}"/>
  </li><li>
    <label for="code"><@s.message code="form.code"/><font color="red">*</font></label>
    <input id="j_captcha_response" name="j_captcha_response" class="text validate[required,maxSize[10]]" />
    <label for="code"><img id="captcha" src="${rc.contextPath}/Captcha.jpg" border="0">&nbsp;&nbsp;<a id="refresh" href="#"><@s.message code="form.code.refresh"/></a></label>
  </li><li>
    <label for="comment.content"><@s.message code="comment.content"/><font color="red">*</font></label>
    <textarea id="message" name="content" rows="8" cols="50" class="validate[required,maxSize[500]]">${(comment.content)!""}</textarea>
  </li><li>
    <input type="image" src="${rc.contextPath}/styles/blog/images/submit.gif" class="send" />
    <div class="clr"></div>
  </li></ol>
  </form>
</div>
<#-- 代码高亮 -->
<script type="text/javascript">
prettyPrint();
</script>
<!-- JiaThis Button BEGIN -->
<script type="text/javascript">var jiathis_config = {data_track_clickback:true};</script>
<script type="text/javascript" src="http://v2.jiathis.com/code/jiathis_r.js?type=left&amp;move=0&amp;uid=1604749" charset="utf-8"></script>
<!-- JiaThis Button END -->
</@com.page>
</#escape>