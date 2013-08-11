<#compress>
<#import "admin/lib/common.ftl" as com/>
<#include "admin/lib/site-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.siteConfigManage.manage"/></#assign>

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

<script charset="utf-8" src="${rc.contextPath}/styles/kindeditor-4.0.3/kindeditor-min.js"></script>
<script>
	var editor;
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="description"]', {
			uploadJson : '${rc.contextPath}/kindEditorUpload',
			allowFileManager : false,
			afterChange : function() {
				jQuery('#content').validationEngine('hide');
			}
		});
	});
</script>
<div id="main">
		<h3 style="text-align:center;color:red;">
			<#if success??><@s.message code="action.siteConfig.update"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
		</h3>
		<form action="updateSiteConfig" class="jNice" method="POST" id="formID">
		<fieldset>
			<p>
				<label><@s.message code="siteConfig.name"/><font color="red">*</font>:</label><input type="text" name="name" class="validate[required,maxSize[50]] text-long" value="${siteConfig.name}"  id="name"/>
			</p>
			<p>
				<label><@s.message code="siteConfig.url"/><font color="red">*</font>(http://):</label><input id="website" name="url" class="text-long validate[required,maxSize[255],custom[url]]" value="${siteConfig.url}" />
			</p>
			<p>    
				<label><@s.message code="siteConfig.about"/>:</label><input id="about" name="about" class="text-long validate[optional,maxSize[255]]" value="${siteConfig.about}" />
			</p>
			<p>    
				<label><@s.message code="siteConfig.icp"/>:</label><input id="icp" name="icp" class="text-long validate[optional,maxSize[50]]" value="${siteConfig.icp}" />
			</p>
			<p>
				<label><@s.message code="siteConfig.contactDescription"/><font color="red">*</font>:</label><textarea name="contactDescription" id="description" style="width:655px;height:200px;">${siteConfig.contactDescription}</textarea>
			</p>
			<input type="hidden" value='${siteConfig.id}' name="id"/>
			<input type="submit" value='<@s.message code="form.save"/>' />
		</fieldset>
        </form>
        <br/>	
        <br/><br/>
</div>
<!-- // #main -->

</@com.page>

</#escape>
</#compress>