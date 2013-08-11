<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.articleManage.manage"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<script>
$(document).ready(function(){
	
	$(".delete").click(function(){
		return window.confirm("<@s.message code="form.isConfirm"/>");
	});
	
	$(".edit").click(function(){
		var url = $(this).attr("href");
		var sc = "${(category.id)!""}";
		url += "&categoryId=" + sc;
		//alert(url);
		window.location.href=url;
		return false;
	});
	
	$("#selectCategory").change(function(){
		var url = "<@s.url relativeUrl="/admin/content/readArticle"/>";
		url = url + "?categoryId=" + $(this).val();
		window.location.href=url;
	});

	<#-- 设置select值 -->
	var sc = "${categoryId!""}";
	if (sc != "") {
		$("#selectCategory").attr("value", sc);
	}
	
});
</script>
<div id="main">
		<h3><@s.message code="category.name"/>
			<select id="selectCategory" name="category.id">
				<option value=""><@s.message code="form.select.all"/></option>
				<#list parents as p>
						<option value="${p.id}">${p.name}</option>
					<#list p.children as c>
						<option value="${c.id}">${c.name}</option>
					</#list>
				</#list>
			</select>
		</h3>
    	<table cellpadding="0" cellspacing="0">
			<tr style="font-weight:bold;">
				<td style="width:180px;"><@s.message code="article.title"/></td>
				<td><@s.message code="article.status"/></td>
            	<td><@s.message code="article.view"/></td>
            	<td><@s.message code="article.postTime"/></td>
            	<td align="center"><@s.message code="form.action"/></td>
        	</tr>
        	<#list articles as p>
        	<tr>
				<td><#if p.topTime??><font style="color:red;">[<@s.message code="form.top"/>]</font></#if>${p.title}</td>
            	<td>
            		<#if p.status == 'publish'>
            			<@s.message code="article.status.publish"/>
            		<#elseif p.status == 'private'>
            			<@s.message code="article.status.private"/>
            		<#elseif p.status == 'draft'>
            			<@s.message code="article.status.draft"/>
            		</#if>
            	</td>
            	<td>${p.view}</td>
            	<td>${p.postTime?string('yyyy-MM-dd HH:mm:ss')}</td>
            	<td class="action">
            		<a target="_blank" href="<@s.url relativeUrl="/blog/view/${p.id}"/>" class="view"><@s.message code="form.view"/></a>
            		<a href="<@s.url relativeUrl="/admin/content/updateArticle"/>?id=${p.id}&categoryId=${categoryId!''}&pageIndex=${pageInfo.pageIndex}" class="edit"><@s.message code="form.edit"/></a>
            		<a href="<@s.url relativeUrl="/admin/content/deleteArticle"/>?id=${p.id}&pageIndex=${pageInfo.pageIndex}&categoryId=${categoryId!''}" class="delete"><@s.message code="form.delete"/></a>
            	</td>
        	</tr>
			</#list>
        </table>
        <br/>
        <#if articles?size gt 0>
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