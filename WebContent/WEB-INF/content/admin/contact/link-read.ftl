<#compress>
<#import "admin/lib/common.ftl" as com/>
<#include "admin/lib/contact-link-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.linkManage.manage"/></#assign>

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
				<td style="width:100px;"><@s.message code="link.name"/></td>
				<td style="width:150px;"><@s.message code="link.site"/></td>
				<td><@s.message code="link.status"/></td>
            	<td><@s.message code="link.createTime"/></td>
            	<td align="center"><@s.message code="form.action"/></td>
        	</tr>
        	<#list links as p>
        	<tr>
        		<td>${p.name}</td>
        		<td><a href="${p.site}" target="_blank">${p.site}</a>
        		<td>
            		<#if p.status == 'show'>
            			<@s.message code="link.status.show"/>
            		<#elseif p.status == 'hidden'>
            			<@s.message code="link.status.hidden"/>
            		</#if>
            	</td>
            	<td>${p.createTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            	<td class="action">
            		<a href="<@s.url relativeUrl="/admin/contact/updateLink"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}" class="edit"><@s.message code="form.edit"/></a>
            		<a href="<@s.url relativeUrl="/admin/contact/deleteLink"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}" class="delete"><@s.message code="form.delete"/></a>
            	</td>
        	</tr>
        	<tr>
				<td colspan="5" style="background: #fbfbfb;"><@s.message code="link.description"/>:${p.description}</td>
        	</tr>
			</#list>
        </table>
        <br/>
        <#if links?size gt 0>
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