<#compress>
<#import "blog/lib/common.ftl" as com>
<#--title定义-->
<#assign title><@s.message code="blog.search"/></#assign>

<@com.page title='${title}'>

<div class="article" style="padding-top:30px;">
  <form action="search" method="POST" name="search">
    <input id="kw" maxlength="100" name="q"  value="${q}"/>
    <input value="<@s.message code="blog.search"/>" id="su" class="btn" onmousedown="this.className='btn btn_h'" onmouseout="this.className='btn'" type="submit">
  </form>
</div>

<#list articles as a>
<div class="article">
  <h3><span><#if a.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if><a href="<@s.url relativeUrl="/blog/view/${a.id}"/>">${a.title}</a></span></h3><div class="clr"></div>
  <p>
  <#if a.quote != null && a.quote != ''>
  <table>
  <tr>
	<td width="20" valign="top" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:left;padding:10px 10px;">“</td>
	<td valign="top" style="padding:4px 10px;font-size:14px;font-weight:bold;color:#424242;">${a.quote}</td>
	<td width="20" valign="bottom" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:right;padding:10px 10px;">”</td>
  </tr>
  </table>
  </#if>
  </p>
  <p>${a.summary}</p>
  <p class="spec">${a.postTime?string('yyyy-MM-dd HH:mm:ss')}&nbsp;|&nbsp;
  <@s.message code="blog.category"/>:<a href="<@s.url relativeUrl="/blog/extendCategory/${a.category.id}}/1"/>">${a.category.name}</a> &nbsp;|&nbsp; <@s.message code="article.view"/>:${a.view}&nbsp;|&nbsp;
  <a href="<@s.url relativeUrl="/blog/view/${a.id}"/>" class="rm">Read more &raquo;</a>
  <a href="<@s.url relativeUrl="/blog/view/${a.id}#cmts"/>" class="com">Comments (${a.commentCount}) &raquo;</a></p>
</div>
</#list>

<#if articles??>
	<#if articles?size gt 0>
		<p class="pages">Page ${pageInfo.pageIndex} of ${pageInfo.totalPage} &nbsp;&nbsp;&nbsp; 
			${pageInfo.pageHtml}
		</p>
	<#else>
		<h2 style="color:red;text-align:center;font-weight:bold;"><@s.message code="blog.search.none"/></h2>
	</#if>
</#if>	

</@com.page>
</#compress>