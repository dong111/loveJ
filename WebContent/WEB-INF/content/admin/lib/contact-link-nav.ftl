<#--title定义-->
<#assign title><@s.message code="site.contactAndLinkManage"/></#assign>

<#--sideNav组合-->
<#assign sideNav0><@s.message code="site.contactManage.manage"/></#assign>
<#assign sideNav1><@s.message code="site.linkManage.add"/></#assign>
<#assign sideNav2><@s.message code="site.linkManage.manage"/></#assign>

<#--sideNavUrl-->
<#assign sideNavUrl0><@s.url relativeUrl="/admin/contact/readContact" /></#assign>
<#assign sideNavUrl1><@s.url relativeUrl="/admin/contact/createLink" /></#assign>
<#assign sideNavUrl2><@s.url relativeUrl="/admin/contact/readLink" /></#assign>

<#--parentNav定义-->
<#assign parentNav><@s.message code="site.contactAndLinkManage"/></#assign>

<#--parentNavUrl定义-->
<#assign parentNavUrl><@s.url relativeUrl="/admin/contact/readContact" /></#assign>

<#assign sideNav=["${sideNav0}","${sideNav1}","${sideNav2}"]> 
<#assign sideNavUrl=["${sideNavUrl0}","${sideNavUrl1}","${sideNavUrl2}"]> 