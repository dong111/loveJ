<#compress>
<#import "blog/lib/common.ftl" as com>

<#--title定义-->
<#if category.parent!=null>
	<#assign title>${category.parent.name}</#assign>
<#else>
	<#assign title>${category.name}</#assign>
</#if>

<#escape x as x?html>
<@com.page title='${title}'>
<#list articles as a>
<div class="article">
  <p class="date">${a.postTime?string('yyyy')}<span>${a.postTime?string('MM')}/${a.postTime?string('dd')}</span></p>
  <h2><span><#if a.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if><a target="_blank" href="<@s.url relativeUrl="/blog/view/${a.id}" />">${a.title}</a></span></h2><div class="clr"></div>
  <p><@s.message code="blog.category"/>:<a href="<@s.url relativeUrl="/blog/extendCategory/${a.category.id}/1" />">${a.category.name}</a> &nbsp;|&nbsp; <@s.message code="article.view"/>:${a.view}</p>
  <#if a.quote != null && a.quote != ''>
  <table>
  <tr>
	<td width="20" valign="top" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:left;padding:10px 10px;">“</td>
	<td valign="top" style="padding:4px 10px;font-size:14px;font-weight:bold;color:#424242;">${a.quote}</td>
	<td width="20" valign="bottom" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:right;padding:10px 10px;">”</td>
  </tr>
  </table>
  </#if>
  <p>${a.summary}</p>
  	<p class="spec"><a target="_blank" href="<@s.url relativeUrl="/blog/view/${a.id}" />" class="rm"><@s.message code="blog.article.more"/> &raquo;</a> &nbsp;|&nbsp; <a target="_blank" href="<@s.url relativeUrl="/blog/view/${a.id}" />#cmts" class="com"><@s.message code="blog.article.comment"/> (${a.commentCount}) &raquo;</a></p>
</div>
</#list>


<#if articles?size gt 0>
<p class="pages">Page ${pageInfo.pageIndex} of ${pageInfo.totalPage} &nbsp;&nbsp;&nbsp; 
	<#noescape>
		${pageInfo.pageHtml}
	</#noescape>
</p>
<#else>
	<h2 style="color:red;text-align:center;font-weight:bold;"><@s.message code="blog.category.none"/></h2>
</#if>	
</@com.page>
</#escape>
</#compress>