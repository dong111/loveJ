<#compress>
<#import "admin/lib/view.ftl" as view>

<#escape x as x?html>
<@view.page>

<style type="text/css">
#wrapper {width:300px;}
#main  {width:290px;float:left;}
#main label {font-size: 12px;font-weight: bold;}
#main span {font-size: 12px;color:#c66653;font-weight:normal; }
</style>

<div id="main">
	<h3 class="lovej-action">
		<@s.message code="action.category.view"/>
	</h3>
	<fieldset>
		<p>
			<label><@s.message code="category.name"/><font color="red">*</font>:<span>${category.name}</span></label>
		</p>
		<p>
			<label><@s.message code="category.priority"/>:<span>${category.priority}</span></label>
		</p>
		<p>
			<label><@s.message code="category.type"/>:<span>${category.type}</span></label>
		</p>
		<p>
			<label><@s.message code="category.secret"/>:<span><#if category.secret == true><@s.message code="category.secret.public"/><#else><@s.message code="category.secret.private"/></#if></span></label>
		</p>
		<p>
			<label><@s.message code="category.createTime"/>:<span>${category.createTime?string('yyyy-MM-dd HH:mm:ss')}</span></label>
		</p>
		<p>
			<label><@s.message code="category.trash"/>:<span><#if category.trash == true><@s.message code="category.trash.true"/><#else><@s.message code="category.trash.false"/></#if></span></label>
		</p>
		<p>
			<label><@s.message code="category.description"/>:</label><span>${category.description}</span>
		</p>
	</fieldset>
</div>
<!-- // #main -->

</@view.page>

</#escape>
</#compress>