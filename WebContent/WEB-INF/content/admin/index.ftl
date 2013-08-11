<#import "admin/lib/common.ftl" as com>

<#--title定义-->
<#assign title><@s.message code="site.home"/></#assign>

<#--sideNav组合-->
<#assign sideNav0><@s.message code="site.home"/></#assign>

<#--url-->

<#--parentNav,currentNav定义-->
<#assign parentNav><@s.message code="site.home"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav0 sideNavUrl=["#"] parentNav=parentNav parentNavUrl="#" currentNav=''>

<div id="main">
	<div  class="jNice">
	<h3>简介</h3>
    <table cellpadding="0" cellspacing="0">
			<tr>
                <td colspan="2">                    	
	<div class="love-article-content">
		<p>
			&nbsp; &nbsp; <span style="font-size: 12px;">&nbsp;
				&nbsp;&nbsp;</span><span style="font-size: 12px;">loveJ是一个完全开源的、简单的个人博客，可以用来发布内容，图片，链接，附件，留言等功能。</span>
		</p>
		<p>
			<span style="font-size: 12px;">&nbsp; &nbsp; &nbsp; &nbsp;本人</span><span
				style="font-size: 12px;">为了学习spring3 mvc +
				freemarker的架构，在网上搜索了许多资料，但都是一些基本的、零碎的信息，没有一个完整的，可用的具体项目用于参考学习，所以产生了创造loveJ的念头。</span>
		</p>
		<p>
			<span style="font-size: 12px;">开发环境：</span>
		</p>
		<p>
			<span style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;JAVA
				EE IDE（</span><span style="white-space: nowrap; font-size: 12px;">Version:
				Indigo</span><span style="font-size: 12px;">）， mysql（</span><span
				style="white-space: nowrap; font-size: 12px;">5.5.15</span><span
				style="font-size: 12px;">）</span>
		</p>
		<p>
			<span style="font-size: 12px;">涉及技术：</span>
		</p>
		<p>
			<span style="font-size: 12px;">&nbsp; &nbsp; &nbsp;
				&nbsp;loveJ的表现层使用了freemarker，UI主要应用了jquery的各种插件，文本编辑器使用了KindEditor，使用了DWR做ajax刷新。</span>
		</p>
		<p>
			<span style="font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&nbsp;控制层使用了spring3 mvc， spring3&nbsp;AOP，做了国际化，使用了dom4j做xml文件处理，使用了</span><span
				style="white-space: nowrap; font-size: 12px;">commons-fileupload做文件上传</span><span
				style="font-size: 12px;">。</span>
		</p>
		<p>
			<span style="font-size: 12px;">&nbsp; &nbsp; &nbsp;
				&nbsp;业务数据层使用了spring3 jdbcTemplate，使用了</span><span
				style="white-space: nowrap; font-size: 12px;">BasicDataSource数据源，使用了spring的事务控制。</span>
		</p>
		<p>
			<br />
		</p>
		<p>
			<span style="white-space: nowrap; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><span
				style="font-size: 12px;">&nbsp;大体上，就这些，基本上把spring3
				mvc在实际开发中所涉及的技术都有所应用。</span><span style="font-size: 12px;">如果大家喜欢，请下载学习，欢迎留言交流。</span>
		</p>
		<p>
			<span style="white-space: nowrap; font-size: 12px;">&nbsp;
				&nbsp; &nbsp; &nbsp;本人才疏学浅，如有不当之处，请大家包涵。</span>
		</p>
		<p>
			<span style="white-space: nowrap; font-size: 12px;">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;如果你有好的建议和意见请给我留言，或者</span><a
				href="http://ketayao.com/blog/contact" style="white-space: nowrap;"><span
				style="font-size: 12px;">联系我</span></a><span
				style="white-space: nowrap; font-size: 12px;">。</span>
		</p>
		<p></p>
	</div>
    		    </td>
            </tr>                                               
        </table>

		<br/>
        <br/>
        <br/>
    </div>
</div>
<#-- // #main -->

</@com.page>

</#escape>