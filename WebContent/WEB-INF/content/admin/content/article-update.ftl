<#compress>
<#import "admin/lib/common.ftl" as com>
<#include "admin/lib/content-nav.ftl"/>
<#--currentNav定义-->
<#assign currentNav><@s.message code="site.articleManage.update"/></#assign>
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
	
	jQuery(document).ready(function(){
        $("#actionArticle").click(function(){
       		if(editor.count('text')==0){
				jQuery('#content').validationEngine('showPrompt', '<@s.message code="form.notice.column.required"/>', 'error', true);
				return false;
			} 
			return true;
        });
        
        jQuery("#back").click(function(){
        	var url = "<@s.url relativeUrl="/admin/content/readArticle"/>";
        	url = url + "?categoryId=${categoryId!''}&pageIndex=${pageIndex}";
    		window.location.href=url;
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
	
</script>


<div id="main">
	<form id="fileuploadForm" action="attach/upload" class="jNice" method="POST" enctype="multipart/form-data">
		<h3 class="lovej-action">
			<#if success??><@s.message code="action.article.update"/></#if>
		</h3>
		<h3>
			<@s.message code="form.notice.required"/>
			<div class="choise" style="float:right;color:red;"><input id="top" type="checkbox" name="top" <#if article.topTime??>checked="checked"</#if>/><@s.message code="article.top"/></div>
		</h3>
		<fieldset>
			<input name="articleId" value="${article.id}" type="hidden"/>
			<input name="categoryId" value="${categoryId}" type="hidden"/>
			<input name="pageIndex" value="${pageIndex}" type="hidden"/>
			<p>
		    <label><@s.message code="attach.description"/>:<input type="file" name="file" ></label><input type="text" name="description" class="text-long">
		    <input type="submit" value='<@s.message code="form.upload"/>' style="float:left;"/>
		    <p>
		</fieldset>
	</form>
	<table cellpadding="0" cellspacing="0">
	<tr style="font-weight:bold;">
		<td><@s.message code="attach.url"/></td>
		<td><@s.message code="attach.description"/></td>
		<td><@s.message code="attach.download"/></td>
		<td align="center"><@s.message code="form.action"/></td>
	</tr>
	<#list attaches as a>
	<tr>
		<td>${a.url}</td>
		<td>${a.description}</td>
		<td>${a.download}</td>
		<td class="action">
			<a href="<@s.url relativeUrl="/blog/download/${a.id}"/>" class="view"><@s.message code="form.view"/></a>
			<a href="<@s.url relativeUrl="/admin/content/attach/delete"/>?id=${a.id}&articleId=${a.articleId}&categoryId=${categoryId}&pageIndex=${pageIndex}" class="delete"><@s.message code="form.delete"/></a>
		</td>
	</tr>
	</#list>
	</table>
	</br>
	<form action="updateArticle" class="jNice" method="POST" id="formID">
		<fieldset>
			<p>
				<label><@s.message code="article.title"/><font color="red">*</font>:</label><input type="text" name="title" class="validate[required,maxSize[50]] text-long"  id="title" value="${article.title}"/>
			</p>
			<p>
				<label><@s.message code="article.quote"/>:</label><textarea name="quote" id="quote" class="validate[optional,maxSize[255]]" style="width:640px;height:100px;">${article.quote}</textarea>
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
						<div class="choise"><input id="p${p.id}" <#if article.categoryId == p.id>checked="checked"</#if> type="checkbox" name="category.id" value='${p.id}' class='validate[required,maxCheckbox[1]]'/>${p.name}(${p.type})</div>
						<#list p.children as c>
							<#if (c_index+1)%4 == 0>
								<div class="choise"></div>
							</#if>
							<div class="choise"><input id="c${c.id}" <#if article.categoryId == c.id>checked="checked"</#if> type="checkbox" name="category.id" value='${c.id}' class='validate[required,maxCheckbox[1]]'/>${c.name}(${c.type})</div>
						</#list>
						</div>
					</#list>	
				</#if>
			</div>
			<p>
				<label><@s.message code="article.content"/><font color="red">*</font>:</label>
				<textarea id="content" name="content" style="width:655px;height:400px;" >${article.content}</textarea>
			</p>
			<p>
				<label><@s.message code="article.summary"/>:</label><textarea style="width:640px;height:200px;" name="summary" id="summary" class="validate[optional,maxSize[500]]">${article.summary}</textarea>
			</p>
			<p>
				<label><@s.message code="article.status"/><font color="red">*</font>:</label>
			</p>
			<div class="lovej-choise" >
				<div class="choise"><input id="status1" type="checkbox" <#if article.status == 'publish'>checked="checked"</#if> name="status" value='publish' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.publish"/></div>
				<div class="choise"><input id="status2" type="checkbox" <#if article.status == 'private'>checked="checked"</#if> name="status" value='private' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.private"/></div>
				<div class="choise"><input id="status3" type="checkbox" <#if article.status == 'draft'>checked="checked"</#if> name="status" value='draft' class='validate[required,maxCheckbox[1]]'/><@s.message code="article.status.draft"/></div>
			</div>
			<input name="categoryId" value="${categoryId}" type="hidden"/>
			<input name="pageIndex" value="${pageIndex}" type="hidden"/>
			
			<input name="id" value="${article.id}" type="hidden"/>
			<input name="view" value="${article.view}" type="hidden"/>
			<input name="trash" value="${article.trash?string('true','false')}" type="hidden"/>
			<input name="postTime" value="${article.postTime?string('yyyy-MM-dd HH:mm:ss')}" type="hidden"/>
			<#if article.topTime??>
			<input name="topTime" value="${article.topTime?string('yyyy-MM-dd HH:mm:ss')}" type="hidden"/>
			</#if>
			<input id="actionArticle" type="submit" value='<@s.message code="form.save"/>' />&nbsp;&nbsp;&nbsp;&nbsp;
			<input id="back" type="button" value='<@s.message code="form.back"/>' />

		</fieldset>
	</form>
</div>
<#-- // #main -->
</@com.page>
</#escape>