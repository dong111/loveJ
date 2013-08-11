<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.categoryManage.manage"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<#-- form验证 -->
<script type="text/javascript" src="${rc.contextPath}/styles/fancybox/jquery.fancybox-1.3.4.pack.js"></script>
<link rel="stylesheet" type="text/css" href="${rc.contextPath}/styles/fancybox/jquery.fancybox-1.3.4.css" media="screen" />

<script>
$(document).ready(function(){
	$(".view").fancybox({
		'width'				: 320,
		'height'			: 380,
		'autoScale'			: false,
		'transitionIn'		: 'none',
		'transitionOut'		: 'none',
		'type'				: 'iframe'
	});
	
	$(".delete").click(function(){
		return window.confirm("<@s.message code="form.isConfirm"/>");
	}); 
});  
</script>


<div id="main">
		<h3 class="lovej-action">
			
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<h3>
			<@s.message code="form.notice.priority"/>
		</h3>
    	<table cellpadding="0" cellspacing="0">
			<tr style="font-weight:bold;">
				<td style="width:200px;"><@s.message code="category.name"/></td>
				<td><@s.message code="category.priority"/></td>
            	<td><@s.message code="category.type"/></td>
            	<#--
            	<td><@s.message code="category.secret"/></td>
            	-->
            	<td><@s.message code="category.createTime"/></td>
            	<td align="center"><@s.message code="form.action"/></td>
        	</tr>
        	<#list parents as p>
        	<tr>
				<td>${p.name}</td>
            	<td>${p.priority}</td>
            	<td>${p.type}</td>
            	<#--
            	<td><#if p.secret == true><@s.message code="category.secret.public"/><#else><@s.message code="category.secret.private"/></#if></td>
            	-->
            	<td>${p.createTime?string('yyyy-MM-dd')}</td>
            	<td class="action">
            		<a href="<@s.url relativeUrl="/admin/content/viewCategory"/>?id=${p.id}" class="view"><@s.message code="form.view"/></a>
            		<a href="<@s.url relativeUrl="/admin/content/updateCategory"/>?id=${p.id}" class="edit"><@s.message code="form.edit"/></a>
            		<a href="<@s.url relativeUrl="/admin/content/deleteCategory"/>?id=${p.id}" class="delete"><@s.message code="form.delete"/></a>
            	</td>
        	</tr>   
        		<#list p.children as c>
        		<tr style="color:#c66653;">
					<td>----${c.name}</td>
	            	<td>${c.priority}</td>
	            	<td>${c.type}</td>
	            	<#--
	            	<td><#if c.secret == true><@s.message code="category.secret.public"/><#else><@s.message code="category.secret.private"/></#if></td>
	            	-->
	            	<td>${c.createTime?string('yyyy-MM-dd')}</td>
	            	<td class="action">
	            		<a href="<@s.url relativeUrl="/admin/content/deleteCategory"/>?id=${c.id}" class="view"><@s.message code="form.view"/></a>
	            		<a href="<@s.url relativeUrl="/admin/content/updateCategory"/>?id=${c.id}" class="edit"><@s.message code="form.edit"/></a>
	            		<a href="<@s.url relativeUrl="/admin/content/deleteCategory"/>?id=${c.id}" class="delete"><@s.message code="form.delete"/></a>
            		</td>
	        	</tr>   
        		</#list>
			</#list>
                       
        </table>
        <br/><br/><br/>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>