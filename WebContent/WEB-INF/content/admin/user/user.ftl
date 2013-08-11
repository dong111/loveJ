<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/user-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.userManage.updateBase"/></#assign>

<#escape x as x?html>
<@com.page title=title sideNav=sideNav sideNavUrl=sideNavUrl parentNav=parentNav parentNavUrl=parentNavUrl currentNav=currentNav>

<#-- form验证 -->
<link rel="stylesheet" href="${rc.contextPath}/styles/formValidator.2.2.1/css/validationEngine.jquery.css" type="text/css"/>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/languages/jquery.validationEngine-${(rc.locale)!'zh_CN'}.js" type="text/javascript" charset="utf-8"></script>
<script src="${rc.contextPath}/styles/formValidator.2.2.1/js/jquery.validationEngine.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
        // binds form submission and fields to the validation engine
        jQuery("#formID").validationEngine();
    });
</script>
<#-- 日期选择  -->
<link href="${rc.contextPath}/styles/wdDatePicker/css/dp.css" rel="stylesheet" type="text/css" />
<script src="${rc.contextPath}/styles/wdDatePicker/src/Plugins/datepicker_lang_zh.js" type="text/javascript"></script>
<script src="${rc.contextPath}/styles/wdDatePicker/src/Plugins/jquery.datepicker.js" type="text/javascript"></script>
<script type="text/javascript">
    jQuery(document).ready(function(){
		$("#birthday").datepicker({ picker: "<img class='picker' style='padding-top:7px;' vertical-align='middle' src='${rc.contextPath}/styles/wdDatePicker/css/cal.gif' alt=''/>" });
    });
</script>

<div id="main">
	<form action="updateBase" class="jNice" method="POST" id="formID">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.user.updateBase"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<fieldset>
			<p>
				<label><@s.message code="user.nickname"/><font color="red">*</font>:</label><input type="text" name="nickname" class="validate[required,maxSize[8]] text-long" value="${user.nickname}" id="nickname"/>
			</p>
			<p>
				<label><@s.message code="user.sex"/>:</label>
				<input type="radio" name="gender" value="true"  <#if user.gender == true>checked="checked"</#if> /><@s.message code="user.sex.male"/>&nbsp;&nbsp;&nbsp;&nbsp;
			    <input type="radio" name="gender" value="false" <#if user.gender == false>checked="checked"</#if> /><@s.message code="user.sex.female"/>
			</p>
			<p>
				<label><@s.message code="user.age"/>:</label><input type="text" name="age" class="validate[optional,custom[integer],maxSize[2]] text-long" value="${user.age}" id="age"/>
			</p>
			<p>
				<label><@s.message code="user.email"/>:</label><input type="text" name="email" class="validate[optional,custom[email]] text-long" value="${user.email}" id="email"/>
			</p>
			<p>
				<label><@s.message code="user.birthday"/>:</label><input type="text" name="birthday" id="birthday"  readOnly="true" class="text-medium" value="${user.birthday?string('yyyy-MM-dd')}"/>
			</p>
			<p>
				<label><@s.message code="user.qq"/>:</label><input type="text" name="qq" class="validate[optional,custom[integer],minSize[5],maxSize[11]] text-long" value="${user.qq}" id="qq"/>
			</p>
			<input type="submit" value='<@s.message code="form.update"/>' />
		</fieldset>
	</form>
</div>
<#-- // #main -->

</@com.page>

</#escape>
</#compress>