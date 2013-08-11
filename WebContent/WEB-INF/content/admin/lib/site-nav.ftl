<#--title定义-->
<#assign title><@s.message code="site.siteConfig"/></#assign>

<#--sideNav组合-->
<#assign sideNav0><@s.message code="site.trackManage.manage"/></#assign>
<#assign sideNav1><@s.message code="site.siteConfigManage.manage"/></#assign>
<#-- 
<#assign sideNav2><@s.message code="site.siteConfigManage.index.create"/></#assign>
-->

<#--sideNavUrl-->
<#assign sideNavUrl0><@s.url relativeUrl="/admin/site/readTrack" /></#assign>
<#assign sideNavUrl1><@s.url relativeUrl="/admin/site/updateSiteConfig" /></#assign>
<#-- 
<#assign sideNavUrl2><@s.url relativeUrl="/admin/site/readIndex" /></#assign>
-->

<#--parentNav定义-->
<#assign parentNav><@s.message code="site.siteConfig"/></#assign>

<#--parentNavUrl定义-->
<#assign parentNavUrl><@s.url relativeUrl="/admin/site/readTrack" /></#assign>

<#-- 
<#assign sideNav=["${sideNav0}","${sideNav1}","${sideNav2}"]> 
<#assign sideNavUrl=["${sideNavUrl0}","${sideNavUrl1}","${sideNavUrl2}"]> 
-->
<#assign sideNav=["${sideNav0}","${sideNav1}"]> 
<#assign sideNavUrl=["${sideNavUrl0}","${sideNavUrl1}"]> 