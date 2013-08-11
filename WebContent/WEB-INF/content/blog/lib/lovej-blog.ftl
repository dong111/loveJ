<#macro menu_nav title index>
  <li <#if title==index[0]>class="active"</#if>><a href="<@s.url relativeUrl="/blog/home" />">${index[0]}</a></li>
  	<#list categories as c>
		<li <#if title==c.name>class="active"</#if>><a href="<@s.url relativeUrl="/blog/extendPage/${c.id}/1" />">${c.name}</a></li>
	</#list>
  <li <#if title==index[1]>class="active"</#if>><a href="<@s.url relativeUrl="/blog/search" />">${index[1]}</a></li>
  <li <#if title==index[2]>class="active"</#if>><a href="<@s.url relativeUrl="/blog/contact" />">${index[2]}</a></li>
</#macro>
