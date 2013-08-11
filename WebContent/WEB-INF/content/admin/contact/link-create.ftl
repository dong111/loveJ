<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/contact-link-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.linkManage.add"/></#assign>

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
    
</script>

<div id="main">
	<form action="createLink" class="jNice" method="POST" id="formID">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.link.add"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<fieldset>
			<p>
				<label><@s.message code="link.name"/><font color="red">*</font>:</label><input type="text" name="name" class="validate[required,maxSize[50]] text-long"  id="name"/>
			</p>
			<p>    
				<label><@s.message code="link.site"/><font color="red">*</font>(http://)</label><input id="website" name="site" class="text-long validate[required,maxSize[255],custom[url]]" />
			</p>
			<p>
				<label><@s.message code="link.description"/>:</label><textarea name="description" id="description" class="validate[optional,maxSize[255]]" style="width:640px;height:100px;"></textarea>
			</p>
			<p>
				<label><@s.message code="link.status"/><font color="red">*</font>:</label>
			</p>
			<div class="lovej-choise" >
				<div class="choise"><input id="status1" type="checkbox" checked="checked" name="status" value='show' class='validate[required,maxCheckbox[1]]'/><@s.message code="link.status.show"/></div>
				<div class="choise"><input id="status2" type="checkbox" name="status" value='hidden' class='validate[required,maxCheckbox[1]]'/><@s.message code="link.status.hidden"/></div>
			</div>
			<input id="actionLink" type="submit" value='<@s.message code="form.save"/>' />
		</fieldset>
	</form>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>