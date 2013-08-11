<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.categoryManage.update"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<#-- form验证 -->
<link rel="stylesheet" href="${rc.contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-${(rc.locale)!'zh_CN'}.js" type="text/javascript" charset="utf-8"></script>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script>
    jQuery(document).ready(function(){
        // binds form submission and fields to the validation engine
        jQuery("#formID").validationEngine();
    });
    
    <#-- 显示父目录 -->
    jQuery(document).ready(function(){    	
    	jQuery("#back").click(function(){
    		window.location.href="<@s.url relativeUrl="/admin/content/readCategory"/>";
    	});
    });
</script>


<div id="main">
	<form action="updateCategory" class="jNice" method="POST" id="formID">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.category.update"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<h3>
			<@s.message code="form.notice.priority"/>
		</h3>
		<fieldset>
			<p>
				<label><@s.message code="category.name"/><font color="red">*</font>:</label><input type="text" name="name" class="validate[required,maxSize[50]] text-long" value="${category.name}" id="name"/>
			</p>
			<p>
				<label><@s.message code="category.priority"/>:</label><input type="text" name="priority" class="validate[optional,custom[integer],maxSize[2]] text-long" value="${category.priority}" id="priority"/>
			</p>
			<#--
			<p>
				<label><@s.message code="category.secret"/>:</label>
				<input type="radio" id="secret1" name="secret" <#if category.secret == true>checked="checked"</#if> value="true"/><@s.message code="category.secret.public"/>&nbsp;&nbsp;
				<input type="radio" id="secret2" name="secret" <#if category.secret == false>checked="checked"</#if> value="false"/><@s.message code="category.secret.private"/>
			</p>
			-->
			<p>
				<label><@s.message code="category.description"/>:</label><textarea rows="1" cols="1" name="description" id="description" class="validate[optional,maxSize[255]]">${category.description}</textarea>
			</p>
			<input name="id" value="${category.id}" type="hidden"/>
			<input name="trash" value="${category.trash?string('true','false')}" type="hidden"/>
			<input name="type" value="${category.type}" type="hidden"/>
			<#if category.parentId??>
			<input name="parent.id" value="${(category.parentId)!""}" type="hidden"/>
			<input name="parentId" value="${(category.parentId)!""}" type="hidden"/>
			</#if>
			<input name="createTime" value="${category.createTime?string('yyyy-MM-dd HH:mm:ss')}" type="hidden"/>
			<input id="actionCategory" type="submit" value='<@s.message code="form.save"/>' />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="back" type="button" value='<@s.message code="form.back"/>' />
		</fieldset>
	</form>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>