<#compress>
<#import "admin/lib/common.ftl" as com/>
<#include "admin/lib/content-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.commentManage.manage"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<script>
$(document).ready(function(){
	
	$(".delete").click(function(){
		return window.confirm("<@s.message code="form.isConfirm"/>");
	});	
});

   
</script>


<div id="main">
		<h3></h3>
    	<table cellpadding="0" cellspacing="0">
			<tr style="font-weight:bold;">
				<td><@s.message code="article.title"/></td>
				<td><@s.message code="comment.name"/></td>
				<td><@s.message code="comment.status"/></td>
            	<td><@s.message code="comment.postTime"/></td>
            	<td align="center"><@s.message code="form.action"/></td>
        	</tr>
        	<#list comments as p>
        	<tr>
        		<td><a target="_blank" href="<@s.url relativeUrl="/blog/view/${p.articleId}"/>" class="view">${p.article.title}</a></td>
        		<td><#if p.site != null && p.site != ''><a href="${p.site}" target="_blank">${p.name}</a><#else>${p.name}</#if></td>
        		<td>
            		<#if p.status == 'approved'>
            			<@s.message code="comment.status.approved"/>
            		<#elseif p.status == 'wait_for_approve'>
            			<@s.message code="comment.status.wait"/>
            		</#if>
            	</td>
            	<td>${p.postTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            	<td class="action">
            		<a href="<@s.url relativeUrl="/admin/content/updateComment"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}" class="view"><@s.message code="form.update.status"/></a>
            		<a href="<@s.url relativeUrl="/admin/content/deleteComment"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}" class="delete"><@s.message code="form.delete"/></a>
            	</td>
        	</tr>
        	<tr>
				<td colspan="6" style="background: #fbfbfb;">${p.content}<br/><@s.message code="comment.email"/>:${p.email}</td>
        	</tr>
			</#list>
        </table>
        <br/>
        <#if comments?size gt 0>
        	<div class="megas512">
        	<#noescape>
				${pageInfo.pageHtml}
        	</#noescape>
        	</div>
        </#if>	
        <br/><br/>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>