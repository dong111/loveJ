<#compress>
<#import "admin/lib/common.ftl" as com/>
<#include "admin/lib/contact-link-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.contactManage.manage"/></#assign>

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
				<td style="width:200px;"><@s.message code="contact.name"/></td>
				<td><@s.message code="contact.status"/></td>
            	<td><@s.message code="contact.postTime"/></td>
            	<td align="center"><@s.message code="form.action"/></td>
        	</tr>
        	<#list contacts as p>
        	<tr>
        		<td><#if p.site?? && p.site != ''><a href="${p.site}" target="_blank">${p.name}</a><#else>${p.name}</#if></td>
        		<td>
            		<#if p.status == 'new'>
            			<@s.message code="contact.status.new"/>
            		<#elseif p.status == 'readed'>
            			<@s.message code="contact.status.read"/>
            		<#elseif p.status == 'replied'>
            			<@s.message code="contact.status.reply"/>
            		</#if>
            	</td>
            	<td>${p.postTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            	<td class="action">
            		<a href="<@s.url relativeUrl="/admin/contact/updateContact"/>?id=${p.id}&status=readed&pageIndex=${pageInfo.pageIndex}" class="view"><@s.message code="contact.status.read"/></a>
            		<a href="<@s.url relativeUrl="/admin/contact/updateContact"/>?id=${p.id}&status=replied&pageIndex=${pageInfo.pageIndex}" class="view"><@s.message code="contact.status.reply"/></a>
            		<a href="<@s.url relativeUrl="/admin/contact/deleteContact"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}" class="delete"><@s.message code="form.delete"/></a>
            	</td>
        	</tr>
        	<tr>
				<td colspan="4" style="background: #fbfbfb;">${p.content}<br/><@s.message code="contact.email"/>:${p.email}</td>
        	</tr>
			</#list>
        </table>
        <br/>
        <#if contacts?size gt 0>
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