<#--sideNav组合-->
<#assign sideNav0><@s.message code="site.articleManage.add"/></#assign>
<#assign sideNav1><@s.message code="site.articleManage.manage"/></#assign>
<#assign sideNav2><@s.message code="site.categoryManage.add"/></#assign>
<#assign sideNav3><@s.message code="site.categoryManage.manage"/></#assign>
<#assign sideNav4><@s.message code="site.commentManage.manage"/></#assign>

<#--sideNavUrl-->
<#assign sideNavUrl0><@s.url relativeUrl="/admin/content/createArticle" /></#assign>
<#assign sideNavUrl1><@s.url relativeUrl="/admin/content/readArticle" /></#assign>
<#assign sideNavUrl2><@s.url relativeUrl="/admin/content/createCategory" /></#assign>
<#assign sideNavUrl3><@s.url relativeUrl="/admin/content/readCategory" /></#assign>
<#assign sideNavUrl4><@s.url relativeUrl="/admin/content/readComment" /></#assign>

<#--title定义-->
<#assign title><@s.message code="site.contentManage"/></#assign>

<#--parentNav定义-->
<#assign parentNav><@s.message code="site.contentManage"/></#assign>

<#--parentNavUrl定义-->
<#assign parentNavUrl><@s.url relativeUrl="/admin/content/createArticle" /></#assign>

<#assign sideNav=["${sideNav0}","${sideNav1}","${sideNav2}","${sideNav3}","${sideNav4}"]> 
<#assign sideNavUrl=["${sideNavUrl0}","${sideNavUrl1}","${sideNavUrl2}","${sideNavUrl3}","${sideNavUrl4}"]> 