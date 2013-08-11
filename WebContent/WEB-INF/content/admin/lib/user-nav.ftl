<#--title定义-->
<#assign title><@s.message code="site.userManage"/></#assign>

<#--sideNav组合-->
<#assign sideNav0><@s.message code="site.userManage.updateBase"/></#assign>
<#assign sideNav1><@s.message code="site.userManage.updatePassword"/></#assign>

<#--sideNavUrl-->
<#assign sideNavUrl0><@s.url relativeUrl="/admin/user/updateBase" /></#assign>
<#assign sideNavUrl1><@s.url relativeUrl="/admin/user/updatePassword" /></#assign>

<#--parentNav定义-->
<#assign parentNav><@s.message code="site.userManage"/></#assign>

<#--parentNavUrl定义-->
<#assign parentNavUrl><@s.url relativeUrl="/admin/user/updateBase" /></#assign>

<#assign sideNav=["${sideNav0}","${sideNav1}"]> 
<#assign sideNavUrl=["${sideNavUrl0}","${sideNavUrl1}"]> 