<#compress>
<#import "blog/lib/common.ftl" as com>

<#--title定义-->

<#escape x as x?html>
<@com.page title='${article.title}'>

<div class="article">
  <h2 style="text-align:center;"><#if article.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if>${article.title}</h2>
  <p style="text-align:center;"><@s.message code="article.postTime"/>:${article.postTime?string('yyyy-MM-dd')}</p>
  <#if article.quote != null && article.quote != ''>
  <table>
  <tr>
	<td width="20" valign="top" style="color:#B2B7F2;font-size:36px;font-family:'Times New Roman',serif;font-weight:bold;text-align:left;padding:10px 10px;">“</td>
	<td valign="top" style="padding:4px 10px;font-size:14px;font-weight:bold;color:#424242;">${a.quote}</td>
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
	
</@com.page>
</#escape>
</#compress>