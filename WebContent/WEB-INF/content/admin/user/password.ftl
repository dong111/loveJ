<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/user-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.userManage.updatePassword"/></#assign>

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
	<form action="updatePassword" class="jNice" method="POST" id="formID">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.user.updatePassword"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<fieldset>
			<p>
				<label><@s.message code="user.password.old"/><font color="red">*</font>:
				</label><input type="password" name="oldPassword" class="validate[required,minSize[6],maxSize[12]] text-long" id="oldPassword"/><#if notsame??><span class="lovej-validate"><@s.message code="validate.password.notsame"/></span></#if>
			</p>
			<p>
				<label><@s.message code="user.password.new"/><font color="red">*</font>:
				</label><input type="password" name="newPassword" class="validate[required,minSize[6],maxSize[12]] text-long" id="newPassword"/>
			</p>
			<p>
				<label><@s.message code="user.password.confirm"/><font color="red">*</font>:
				</label><input type="password" name="confirmPassword" class="validate[required,minSize[6],maxSize[12],equals[newPassword]] text-long" id="confirmPassword"/>
			</p>
			<input type="submit" value='<@s.message code="form.update"/>' />
		</fieldset>
	</form>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>