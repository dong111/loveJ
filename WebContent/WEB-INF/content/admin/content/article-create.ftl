<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>

<#--currentNav定义-->
<#assign currentNav><@s.message code="site.articleManage.add"/></#assign>
</#compress>
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

<script charset="utf-8" src="${rc.contextPath}/styles/kindeditor-4.1/kindeditor-min.js"></script>
<script>
	jQuery(document).ready(function(){
        $("#actionArticle").click(function(){
       		if(editor.count('text')==0){
				jQuery('#content').validationEngine('showPrompt', '<@s.message code="form.notice.column.required"/>', 'error', true);
				return false;
			} 
			return true;
        });
    });
    
    // top
    jQuery(document).ready(function(){
        $("#top").click(function(){
        	var top;
        	if ($(this).attr("checked")) {
        		top = true;
        	} else {
				top = false;
        	}
        	
        	jQuery.ajax({
	            type: 'POST',
	            contentType: 'application/x-www-form-urlencoded;charset=UTF-8',
	            url: '<@s.url relativeUrl="/admin/content/topArticle?top="/>' + top,
	        });	
        });
    });

    var editor;
	
	KindEditor.ready(function(K) {
		editor = K.create('textarea[id="content"]', {
			langType : '<#if rc.locale?? && rc.locale != 'zh_CN'>en<#else>${(rc.locale)!'zh_CN'}</#if>',
			uploadJson : '${rc.contextPath}/kindEditorUpload',
			cssPath : ['${rc.contextPath}/styles/kindeditor-4.1/plugins/code/prettify.css'],
			allowFileManager : false,
			afterChange : function() {
				jQuery('#content').validationEngine('hide');
			}
		});
	});
	
	var editor = KindEditor.create('textarea.editor', {
    	cssPath : ['[kePath]/plugins/code/prettify.css']
	});
</script>
<div id="main">
	<form action="createArticle" class="jNice" method="POST" id="formID">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.article.add"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
			<div class="choise" style="float:right;color:red;"><input id="top" type="checkbox" name="top"/><@s.message code="article.top"/></div>
		</h3>
		<fieldset>
			<p>
				<label><@s.message code="article.title"/><font color="red">*</font>:</label><input type="text" name="title" class="validate[required,maxSize[50]] text-long"  id="title"/>
			</p>
			<p>
				<label><@s.message code="article.quote"/>:</label><textarea name="quote" id="quote" class="validate[optional,maxSize[255]]" style="width:640px;height:100px;"></textarea>
			</p>
			<p>
				<label><@s.message code="article.category"/><font color="red">*</font>:</label>
			</p>
			<div class="lovej-choise">
				<#if parents?size==0>
					<font color="red">--------<@s.message code="form.notice.article.category"/></font>
				<#else>
					<#list parents as p>
						<div class="choise-group">
						<div class="choise"><input id="p${p.id}" type="checkbox" name="categoryId" value='${p.id}' class='validate[required,maxCheckbox[1]]'/>${p.name}(${p.type})</div>
						<#list p.children as c>
							<#if (c_index+1)%4 == 0>
								<div class="choise"></div>
							</#if>
							<div class="choise"><input id="c${c.id}" type="checkbox" name="categoryId" value='${c.id}' class='validate[required,maxCheckbox[1]]'/>${c.name}(${c.type})</div>
						</#list>
						</div>
					</#list>	
				</#if>
			</div>
			<p>
				<label><@s.message code="article.content"/><font color="red">*</font>:</label>
				<textarea id="content" name="content" style="width:655px;height:400px;" ></textarea>
			</p>
			<p>
				<label><@s.message code="article.summary"/>:</label><textarea style="width:640px;height:200px;" name="summary" id="summary" class="validate[optional,maxSize[500]]"></textarea>
			</p>
			<p>
				<label><@s.message code="article.status"/><font color="red">*</font>:</label>
			</p>
			<div class="lovej-choise" >
				<div class="choise"><input id="status1" type="checkbox" checked="checked" name="status" value='publish' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.publish"/></div>
				<div class="choise"><input id="status2" type="checkbox" name="status" value='private' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.private"/></div>
				<div class="choise"><input id="status3" type="checkbox" name="status" value='draft' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.draft"/></div>
			</div>
			<input id="actionArticle" type="submit" value='<@s.message code="form.save"/>' />
		</fieldset>
	</form>
</div>
<!-- // #main -->
</@com.page>
</#escape>